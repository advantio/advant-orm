/**
 * Copyright 2016 Advant I/O
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.advant.orm;

import io.advant.orm.internal.Condition;
import io.advant.orm.internal.Where;
import io.advant.orm.exception.OrmException;
import io.advant.orm.exception.TableParseException;
import io.advant.orm.internal.*;

import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @param <T>
 */
public abstract class AbstractDAO<T extends Entity> implements DAO<T> {

	private static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pstmt;
	private Class<T> entityClass;
	private EntityReflect<? extends Entity> reflect;

	protected AbstractDAO(Connection connection) {
		this.connection = connection;
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		loadEntities();
	}

	AbstractDAO(Class<T> entityClass, Connection connection) {
		this.connection = connection;
		this.entityClass = entityClass;
		loadEntities();
	}

	private void loadEntities() {
		try {
			reflect = EntityReflect.getInstance(entityClass);
		} catch (TableParseException | NoSuchFieldException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	protected Connection getConnection() {
		return connection;
	}

	@Override
	public void close() throws OrmException {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new OrmException(e);
		}
	}

    @Override
    public void clearTable() throws OrmException {
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate("TRUNCATE TABLE " + reflect.getTable());
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new OrmException(e);
        }
    }

    protected ResultSet select() throws OrmException {
		return select(null);
	}

	protected ResultSet select(Where where) throws OrmException {
		return select(where, null);
	}

	protected ResultSet select(Where whereCond, Integer limitVal) throws OrmException {
		try {
			stmt = connection.createStatement();
			String select = "SELECT";
			String from = "FROM " + reflect.getTable();
			String join = "";
			String where = whereCond == null ? "" : whereCond.toString();
			String limit = limitVal != null ? " LIMIT " + limitVal : "";
			Set<ColumnData> columns = new HashSet<>();
			columns.addAll(reflect.getColumns());
			getTableColumnsForSelect(columns, reflect);
			List<JoinData> joins = new ArrayList<>();
			getJoinsForSelect(reflect, joins, new JoinData(null, null, reflect.getTable(), null));
			Iterator<ColumnData> iterator = columns.iterator();
			while (iterator.hasNext()) {
				ColumnData column = iterator.next();
				boolean isLastColumn = !iterator.hasNext() && !iterator.hasNext();
				select += " " + column.getTable() + "." + column.getColumn() + " AS " + column.getTable() + "_"
						+ column.getColumn() + (isLastColumn ? " " : ",");
			}
			for (JoinData joinData : joins) {
				join += " LEFT JOIN " + joinData.getJoinTable() + " AS " + joinData.getJoinTable()
						+ " ON " + joinData.getJoinTable() + "." + joinData.getJoinColumn()
						+ "=" + joinData.getTable() + "." + joinData.getColumn();
			}
			String sql = select + from + join + where + limit;
			LOGGER.info(sql);
			return stmt.executeQuery(sql);
		} catch (TableParseException | NoSuchFieldException | SQLException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new OrmException(e);
		}
	}

	@Override
	public void insert(T entity) throws OrmException {
		String query = null;
		try {
			List<ColumnData> columnsData = fromEntity(entity);
			query = "INSERT INTO " + reflect.getTable();
			String columns = " (";
			String values = " (";
			Iterator<ColumnData> iterator = columnsData.iterator();
			boolean idIsNull = false;
			while(iterator.hasNext()){
				ColumnData columnData = iterator.next();
				String column = columnData.getColumn();
				Object value;
				if (column.equals("version")) {
					value = "1";
				} else if (column.equals("id")) {
					value = columnData.getValue();
					if (value == null) {
						idIsNull = true;
						continue;
					}
				} else {
					value = Parser.parse(columnData.getValue());
					value = value == null ? "NULL" : "'" + value + "'";
				}
				columns += column + ",";
				values += value + ",";
			}
			columns = columns.substring(0, columns.length()-1) + ")";
			values = values.substring(0, values.length()-1) + ")";
			query += columns + " VALUES " + values;
			stmt = connection.createStatement();
			stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			if (idIsNull) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					entity.setId(rs.getLong(1));
				}
				rs.close();
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.getMessage() + " Query: " + query , e);
			throw new OrmException(e);
		} finally {
			close();
		}
	}

    @Override
	public int update(T entity) throws OrmException {
		try {
			List<ColumnData> columns = fromEntity(entity);
			String query = "UPDATE " + reflect.getTable() + " SET ";
			Iterator<ColumnData> iterator = columns.iterator();
			Long id = null;
			Long version = null;
			while(iterator.hasNext()){
				ColumnData columnData = iterator.next();
				if(columnData.getColumn().equals("id")){
					id = (Long) columnData.getValue();
				} else if (columnData.getColumn().equals("version")) {
					version = (Long) columnData.getValue();
					version = version==null ? 1L : ++version;
				} else {
					query += columnData.getColumn() + " = '" + Parser.parse(columnData.getValue()) + "',";
				}
			}
			query += " version = " + version;
			query += " WHERE id = " + id;
			stmt = connection.createStatement();
			return stmt.executeUpdate(query);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new OrmException(e);
		} finally {
			close();
		}
	}

	@Override
	public T save(T entity) throws OrmException {
		try {
			Where where = new Where(new Condition(entityClass, "id", (entity).getId()));
			ResultSet resultSet = select(where);
			Integer id = null;
			while (resultSet.next()) {
				id = resultSet.getInt("id");
			}
			if (id==null) {
				insert(entity);
			} else {
				update(entity);
			}
			return entity;
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new OrmException(e);
		} finally {
			close();
		}
	}

	@Override
	public void delete(T entity) throws OrmException {
		try {
			pstmt = connection.prepareStatement("DELETE FROM " + reflect.getTable() + " WHERE id=?" );
			pstmt.setObject(1, (entity).getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new OrmException(e);
		} finally {
			close();
		}
	}

	@Override
	public List<T> findAll() throws OrmException {
		try {
            ResultSet rs = select();
            return toEntities(rs);
		} finally {
			close();
		}
	}

    @Override
	public T find(Long id) throws OrmException {
		try {
			Where where = new Where(new Condition(entityClass, "id", id));
			ResultSet rs = select(where);
            return toEntity(rs);
		} finally {
			close();
		}
	}

	private void getTableColumnsForSelect(Set<ColumnData> columns, EntityReflect<? extends Entity> entityReflect) throws TableParseException, NoSuchFieldException {
        for (Class joinEntitiyClass : entityReflect.getJoinEntities().values()) {
            EntityReflect joinEntityReflect = EntityReflect.getInstance(joinEntitiyClass);
            columns.addAll(joinEntityReflect.getColumns());
            getTableColumnsForSelect(columns, joinEntityReflect);
        }
	}
	
	private void getJoinsForSelect(EntityReflect<? extends Entity> reflect, List<JoinData> joins, JoinData doNotJoin) throws TableParseException, NoSuchFieldException {
        for (JoinData join : reflect.getJoins()) {
            if (!joins.contains(join) && !join.equals(doNotJoin)) {
                joins.add(join);
            }
        }
        for (Class joinEntityClass : reflect.getJoinEntities().values()) {
            EntityReflect joinEntityReflect = EntityReflect.getInstance(joinEntityClass);
            getJoinsForSelect(joinEntityReflect, joins, doNotJoin);
        }
	}

    protected T toEntity(ResultSet rs) throws OrmException {
		try {
			EntityConverter<T> converter = new EntityConverter<>(entityClass);
			return converter.toEntity(rs);
		} catch (IllegalAccessException | TableParseException | SQLException | InstantiationException | NoSuchFieldException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new OrmException(e);
		}
	}

    protected List<T> toEntities(ResultSet rs) throws OrmException {
		try {
			EntityConverter<T> converter = new EntityConverter<>(entityClass);
			return converter.toEntities(select());
		} catch (IllegalAccessException | NoSuchFieldException | SQLException | InstantiationException | TableParseException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new OrmException(e);
		}
	}

    protected List<ColumnData> fromEntity(T entity) throws OrmException {
		try {
			EntityConverter<T> converter = new EntityConverter<>(entityClass);
			return converter.fromEntity(entity);
		} catch (IllegalAccessException | NoSuchFieldException | TableParseException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			throw new OrmException(e);
		}
	}

    protected EntityReflect<? extends Entity> getReflect() {
        return reflect;
    }

}
