package com.github.ji4597056.support.common.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

/**
 * druid datasource factory
 *
 * @author Jeffrey
 * @since 2017/01/20 9:29
 */
public class BaseDruidDataSourceFactory {

    private DefaultDruidProperties properties;

    private Map<String, Object> configProperties = new HashMap<>();

    public BaseDruidDataSourceFactory(DefaultDruidProperties properties) {
        this.properties = properties;
        init();
    }

    public DefaultDruidProperties getProperties() {
        return properties;
    }

    public void setProperties(DefaultDruidProperties properties) {
        this.properties = properties;
    }

    private void init() {
        configProperties.put(DruidDataSourceFactory.PROP_NAME, properties.getName());
        configProperties
            .put(DruidDataSourceFactory.PROP_DRIVERCLASSNAME, properties.getDriverClassName());
        configProperties.put(DruidDataSourceFactory.PROP_URL, properties.getUrl());
        configProperties.put(DruidDataSourceFactory.PROP_USERNAME, properties.getUsername());
        configProperties.put(DruidDataSourceFactory.PROP_PASSWORD, properties.getPassword());
        configProperties.put(DruidDataSourceFactory.PROP_INITIALSIZE, properties.getInitialSize());
        configProperties.put(DruidDataSourceFactory.PROP_MINIDLE, properties.getMinIdle());
        configProperties.put(DruidDataSourceFactory.PROP_MAXACTIVE, properties.getMaxActive());
        configProperties.put(DruidDataSourceFactory.PROP_MAXWAIT, properties.getMaxWait());
        configProperties.put(DruidDataSourceFactory.PROP_TIMEBETWEENEVICTIONRUNSMILLIS,
            properties.getTimeBetweenEvictionRunsMillis());
        configProperties.put(
            DruidDataSourceFactory.PROP_MINEVICTABLEIDLETIMEMILLIS,
            properties.getMinEvictableIdleTimeMillis());
        configProperties
            .put(DruidDataSourceFactory.PROP_VALIDATIONQUERY, properties.getValidationQuery());
        configProperties
            .put(DruidDataSourceFactory.PROP_TESTWHILEIDLE, properties.getTestWhileIdle());
        configProperties
            .put(DruidDataSourceFactory.PROP_TESTONBORROW, properties.getTestOnBorrow());
        configProperties
            .put(DruidDataSourceFactory.PROP_TESTONRETURN, properties.getTestOnReturn());
        configProperties.put(
            DruidDataSourceFactory.PROP_POOLPREPAREDSTATEMENTS,
            properties.getPoolPreparedStatements());
        configProperties.put(DruidDataSourceFactory.PROP_FILTERS, properties.getFilters());
        configProperties.put(DruidDataSourceFactory.PROP_CONNECTIONPROPERTIES,
            properties.getConnectionProperties());
        configProperties
            .put(DruidDataSourceFactory.PROP_REMOVEABANDONED, properties.getRemoveAbandoned());
        configProperties.put(DruidDataSourceFactory.PROP_REMOVEABANDONEDTIMEOUT,
            properties.getRemoveAbandonedTimeout());
        configProperties
            .put(DruidDataSourceFactory.PROP_LOGABANDONED, properties.getLogAbandoned());
    }

    public DataSource createDataSource() throws Exception {
        try {
            return DruidDataSourceFactory.createDataSource(configProperties);
        } catch (Exception e) {
            throw new Exception("create druid datasource error", e);
        }
    }

    public Map<String, Object> getConfigProperties() {
        return Collections.unmodifiableMap(configProperties);
    }

    public void refresh() {
        init();
    }
}
