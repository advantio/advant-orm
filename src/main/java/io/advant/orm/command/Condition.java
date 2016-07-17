package io.advant.orm.command;

import io.advant.orm.type.LogicType;
import io.advant.orm.type.OperatorType;
import io.advant.orm.dao.EntityReflect;
import io.advant.orm.exception.TableParseException;
import io.advant.orm.dao.Entity;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Marco Romagnolo
 */
public class Condition {

    private static final Logger LOGGER = Logger.getLogger(Condition.class.getName());
    private String table;
    private String column;
    private Object value;
    private OperatorType op;
    private LogicType logic;

    public Condition(Class<? extends Entity> entityClass, String column, Object value) {
        this(entityClass, column, OperatorType.eq, value);
    }

    public Condition(Class<? extends Entity> entityClass, String column, OperatorType op, Object value) {
        this.value = value;
        this.column = column;
        this.op = op;
        try {
            EntityReflect<? extends Entity> reflect = EntityReflect.getInstance(entityClass);
            this.table = reflect.getTable();
        } catch (NoSuchFieldException | TableParseException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void setLogic(LogicType logic) {
        this.logic = logic;
    }

    public String getColumn() {
        return column;
    }

    public Object getValue() {
        return value;
    }

    public OperatorType getOp() {
        return op;
    }

    public LogicType getLogic() {
        return logic;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(table).append(".").append(column).append(op).append(value);
        return sb.toString();
    }
}
