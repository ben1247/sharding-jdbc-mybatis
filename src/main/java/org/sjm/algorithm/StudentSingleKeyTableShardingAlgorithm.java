package org.sjm.algorithm;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Component: 因为t_student实际表在每个库中只有2个，所以 %2
 * Description:
 * Date: 16/9/29
 *
 * @author yue.zhang
 */
public class StudentSingleKeyTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Integer> {

    /**
     * sql 中 = 操作时，table的映射
     */
    public String doEqualSharding(Collection<String> tableNames, ShardingValue<Integer> shardingValue) {
        for(String tableName : tableNames){
            if (tableName.endsWith(shardingValue.getValue() % 2 + "")){
                return tableName;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * sql 中 in 操作时，table的映射
     */
    public Collection<String> doInSharding(Collection<String> tableNames, ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<String>();
        for(Integer value : shardingValue.getValues()){
            for(String tableName : tableNames){
                if(tableName.endsWith(value % 2 + "")){
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
        Collection<String> result = new LinkedHashSet<String>();
        Range<Integer> range = shardingValue.getValueRange();
        for(Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++){
            for(String tableName : tableNames){
                if(tableName.endsWith(i % 2 + "")){
                    result.add(tableName);
                }
            }
        }
        return result;
    }
}
