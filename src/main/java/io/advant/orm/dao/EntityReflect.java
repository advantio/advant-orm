package io.advant.orm.dao;

import io.advant.orm.annotation.Column;
import io.advant.orm.annotation.Relation;
import io.advant.orm.annotation.Table;
import io.advant.orm.exception.TableParseException;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * @author Marco Romagnolo
 * EntityReflect
 * @param <T>
 */
public class EntityReflect<T> {

    private static Map<Class<?>, EntityReflect> instance = new HashMap<>();
    private final Class<T> entityClass;
    private final Class<? super T> tableClass;
    private final String table;
    private Field columnId;
    private Field columnVersion;
    private Set<JoinData> joins = new HashSet<>();
    private Set<ColumnData> columns = new HashSet<>();
    private Map<Field, Class> joinEntities = new HashMap<>();

    private EntityReflect(Class<T> entityClass) throws TableParseException, NoSuchFieldException {
        this.entityClass = entityClass;
        this.tableClass = getTableFromEntity(entityClass);
        this.table = tableClass.getAnnotation(Table.class).name();
        parseTable();
        parseEntity();
    }

    public static <T> EntityReflect<T> getInstance(Class<T> clazz) throws TableParseException, NoSuchFieldException {
        EntityReflect<T> current = instance.get(clazz);
        if (current==null) {
            current = new EntityReflect<>(clazz);
            instance.put(clazz, current) ;
        }
        return current;
    }

    private void parseEntity() throws NoSuchFieldException, TableParseException {
        for (Field field : entityClass.getDeclaredFields()) {
            field.setAccessible(true);
            Relation annotRelation = field.getAnnotation(Relation.class);
            if (annotRelation!=null) {
                Class joinEntityClass;
                if (field.getType().equals(List.class) || field.getType().equals(Set.class)) {
                    ParameterizedType genericType = (ParameterizedType)  field.getGenericType();
                    joinEntityClass = (Class<?>) genericType.getActualTypeArguments()[0];
                } else {
                    joinEntityClass = field.getType();
                }
                Class joinTableClass = getTableFromEntity(joinEntityClass);
                Table annotJoinTable = (Table) joinTableClass.getAnnotation(Table.class);
                String joinTable = annotJoinTable.name();
                String column = getColumnFromTable(tableClass, annotRelation.from());
                String joinColumn = getColumnFromTable(joinTableClass, annotRelation.to());
                JoinData joinData = new JoinData(table, column, joinTable, joinColumn);
                joins.add(joinData);
                joinEntities.put(field, joinEntityClass);
            }
        }
    }

    private String getColumnFromTable(Class tableClass, String property) throws NoSuchFieldException {
        Field field = tableClass.getDeclaredField(property);
        field.setAccessible(true);
        return field.getAnnotation(Column.class).name();
    }

    private void parseTable() {
        for (Field field : tableClass.getDeclaredFields()) {
            field.setAccessible(true);
            Column annotColumn = field.getAnnotation(Column.class);
            String column = annotColumn.name();
            boolean isId = field.getName().equals("id");
            boolean isVersion = field.getName().equals("version");
            if (isId) columnId = field;
            if (isVersion) columnVersion = field;
            ColumnData columnData = new ColumnData(isId, isVersion, column, table, field);
            columns.add(columnData);
        }
    }

    private <E> Class<? super E> getTableFromEntity(Class<E> entityClass) throws TableParseException {
        Table tableAnnot;
        Class<? super E> tableClass;
        do {
            tableClass = entityClass.getSuperclass();
            tableAnnot = tableClass.getAnnotation(Table.class);
            if (tableClass.equals(Object.class)) {
                throw new TableParseException("Error parsing Table Annotations");
            }
        } while (tableAnnot==null);
        return tableClass;
    }

    public Field getIdField() {
        return columnId;
    }

    public Field getVersionField() {
        return columnVersion;
    }

    public String getTable() {
        return table;
    }

    public Set<JoinData> getJoins() {
        return joins;
    }

    public Set<ColumnData> getColumns() {
        return columns;
    }

    public Map<Field, Class> getJoinEntities() {
        return joinEntities;
    }

}
