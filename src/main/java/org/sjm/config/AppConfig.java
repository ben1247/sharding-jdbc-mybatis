package org.sjm.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import org.sjm.algorithm.StudentSingleKeyDatabaseShardingAlgorithm;
import org.sjm.algorithm.StudentSingleKeyTableShardingAlgorithm;
import org.sjm.algorithm.UserSingleKeyDatabaseShardingAlgorithm;
import org.sjm.algorithm.UserSingleKeyTableShardingAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Component:
 * Description:
 * Date: 16/10/1
 *
 * @author yue.zhang
 */
@Configuration
public class AppConfig {


    @Bean
    public DataSource shardingDataSource(){
        return ShardingDataSourceFactory.createDataSource(shardingRule());
    }

    /**
     * 规则配置
     * @return
     */
    private ShardingRule shardingRule(){

        // 数据源配置
        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put("ds_0",createDataSource("sharding_0"));
        dataSourceMap.put("ds_1",createDataSource("sharding_1"));
        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap);

        // 使用默认策略配置
        TableRule userTableRule = TableRule.builder("t_user")
                .actualTables(Arrays.asList("t_user_0", "t_user_1","t_user_2"))
                .dataSourceRule(dataSourceRule)
                .databaseShardingStrategy(new DatabaseShardingStrategy("user_id", new UserSingleKeyDatabaseShardingAlgorithm()))
                .tableShardingStrategy(new TableShardingStrategy("user_id",new UserSingleKeyTableShardingAlgorithm()))
                .build();

        TableRule studentTableRule = TableRule.builder("t_student")
                .actualTables(Arrays.asList("t_student_0","t_student_1"))
                .dataSourceRule(dataSourceRule)
                .databaseShardingStrategy(new DatabaseShardingStrategy("student_id", new StudentSingleKeyDatabaseShardingAlgorithm()))
                .tableShardingStrategy(new TableShardingStrategy("student_id", new StudentSingleKeyTableShardingAlgorithm()))
                .build();

        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(Arrays.asList(userTableRule, studentTableRule))
                .build();

        return shardingRule;
    }

    private DataSource createDataSource(final String dataSourceName){

        try {

            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/" + dataSourceName + "?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true");
            dataSource.setUsername("root");
            dataSource.setPassword("root");

            dataSource.setInitialSize(2); // 初始化连接大小
            dataSource.setMinIdle(2);     // 连接池最小空闲
            dataSource.setMaxActive(20);  // 连接池最大使用连接数量

            dataSource.setMaxWait(60000); // 获取连接最大等待时间
            dataSource.setValidationQuery("SELECT 1");
            dataSource.setTestOnBorrow(false);
            dataSource.setTestOnReturn(false);
            dataSource.setTestWhileIdle(true);

            dataSource.setTimeBetweenEvictionRunsMillis(60000); // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
            dataSource.setMinEvictableIdleTimeMillis(25200000); // 配置一个连接在池中最小生存的时间，单位是毫秒
            dataSource.setRemoveAbandoned(true);
            dataSource.setRemoveAbandonedTimeout(1800);
            dataSource.setLogAbandoned(true);
            dataSource.setFilters("stat");

            return dataSource;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
