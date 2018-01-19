package com.github.ji4597056.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.ji4597056.support.common.mybatis.DynamicDataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis config
 *
 * @author Jeffrey
 * @since 2018/01/10 12:57
 */
@Configuration
@AutoConfigureAfter({DruidConfig.class})
@MapperScan(basePackages = "com.github.ji4597056.dao", sqlSessionFactoryRef = "sqlSessionFactory")
@EnableTransactionManagement
public class MybatisConfig extends MybatisAutoConfiguration {

    /**
     * SqlSessionFactory
     *
     * @param dataSource dynamic DataSource
     * @return SqlSessionFactory
     * @throws Exception Exception
     */
    @Bean
    @Override
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(
            resolver.getResources("classpath*:mybatis/mapper/**/*Mapper.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.github.ji4597056.entity.mybatis");
        sqlSessionFactoryBean
            .setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * DynamicDataSource
     *
     * @return DynamicDataSource
     */
    @Bean
    @Primary
    public DataSource dynamicDataSource(List<DruidDataSource> dataSources) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        dataSources.forEach(source -> targetDataSources.put(source.getName(), source));
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        // default datasource
        dataSource.setDefaultTargetDataSource(dataSources.get(0));
        return dataSource;
    }

    /**
     * DataSourceTransactionManager
     *
     * @param dataSource DynamicDataSource
     * @return DataSourceTransactionManager
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}

