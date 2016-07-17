package io.advant.orm.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Marco Romagnolo
 */
public class Where {

    private List<ConditionPool> pools = new ArrayList<>();

    public Where(Condition condition) {
        pools.add(new ConditionPool(condition));
    }

    public Where and(Condition condition) {
        ConditionPool pool = new ConditionPool(condition);
        pools.add(pool);
        return this;
    }

    public Where or(Condition condition) {
        ConditionPool pool = new ConditionPool(condition);
        pools.add(pool);
        return this;
    }

    public Where and(ConditionPool pool) {
        pools.add(pool);
        return this;
    }

    public Where or(ConditionPool pool) {
        pools.add(pool);
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" WHERE ");
        Iterator<ConditionPool> iterator = pools.iterator();
        boolean isFirst = true;
        while (iterator.hasNext()) {
            ConditionPool pool = iterator.next();
            recursivePool(sb, isFirst, pool);
            isFirst = false;
        }
        return sb.toString();
    }

    private void recursivePool(StringBuilder sb, boolean isFirst, ConditionPool pool) {
        if (pool.getPool()!=null) {
            recursivePool(sb, true, pool.getPool());
        } else {
            if (isFirst) {
                iterateConditions(sb, pool.getConditions());
            } else {
                sb.append(pool.getLogic()).append(" (");
                iterateConditions(sb, pool.getConditions());
                sb.append(") ");
            }
        }
    }

    private void iterateConditions(StringBuilder sb, List<Condition> conditions) {
        Iterator<Condition> iterator = conditions.iterator();
        boolean isFirst = true;
        while (iterator.hasNext()) {
            Condition condition = iterator.next();
            if (isFirst) {
                sb.append(condition);
            } else {
                sb.append(condition.getLogic()).append(" ").append(condition).append(" ");
            }
            isFirst = false;
        }
    }

}
