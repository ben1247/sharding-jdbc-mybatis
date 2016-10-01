package org.sjm.algorithm;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Component:
 * Description:
 * Date: 16/9/30
 *
 * @author yue.zhang
 */
public class UserSingleKeyTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Integer> {

    public String doEqualSharding(Collection<String> tableNames, ShardingValue<Integer> shardingValue) {
        for (String each : tableNames) {
            if (each.endsWith(shardingValue.getValue() % 3 + "")) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }

    public Collection<String> doInSharding(Collection<String> tableNames, ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<String>(tableNames.size());
        for (Integer value : shardingValue.getValues()) {
            for (String tableName : tableNames) {
                if (tableName.endsWith(value % 3 + "")) {
                    result.add(tableName);
                }
            }
        }
        return result;

    }

    /**
     * sql 中 between 操作时，table的映射
     */
    public Collection<String> doBetweenSharding(Collection<String> tableNames, ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<String>(tableNames.size());
        Range<Integer> range = shardingValue.getValueRange();
        for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : tableNames) {
                if (each.endsWith(i % 3 + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }
}
