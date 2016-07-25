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

package io.advant.orm.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
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
