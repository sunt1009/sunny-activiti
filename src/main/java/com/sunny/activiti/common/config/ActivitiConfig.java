package com.sunny.activiti.common.config;

import com.zaxxer.hikari.HikariDataSource;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @ClassName: ActivitiConfig
 * @Description: activiti工作流引擎配置
 * @Author: sunt
 * @Date: 2019/8/2 10:58
 * @Version 1.0
 **/
@Configuration
public class ActivitiConfig {

    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private HikariDataSource hikariDataSource;

    @Autowired
    private ActivitiIdGenerate activitiIdGenerate;

    @Bean
    public ProcessEngineConfigurationImpl getProcessEngineConfiguration(ProcessEngineConfigurationImpl processEngineConfiguration) {

        //id生成策略
       processEngineConfiguration.setIdGenerator(activitiIdGenerate);
        //设置DbSqlSessionFactory的uuidGenerator，否则流程id，任务id，实例id依然是用DbIdGenerator生成
        processEngineConfiguration.getDbSqlSessionFactory().setIdGenerator(activitiIdGenerate);
        //设置流程图片中文乱码
        processEngineConfiguration.setActivityFontName("宋体");
        processEngineConfiguration.setLabelFontName("宋体");
        processEngineConfiguration.setAnnotationFontName("宋体");
      //  processEngineConfiguration.setEnableDatabaseEventLogging(true);
        return processEngineConfiguration;
    }

    // 记录流程日志
    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration(DataSource dataSource, PlatformTransactionManager manager){
        SpringProcessEngineConfiguration configuration =  new SpringProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setTransactionManager(manager);
        configuration.setAsyncExecutorActivate(true);
        configuration.setHistoryLevel(HistoryLevel.FULL);
        configuration.setDatabaseSchema("activity");
        configuration.setDbHistoryUsed(true);
        configuration.setDatabaseSchemaUpdate("true");
        configuration.setEnableDatabaseEventLogging(true); //是否开启dblog
        return configuration;
    }
}
