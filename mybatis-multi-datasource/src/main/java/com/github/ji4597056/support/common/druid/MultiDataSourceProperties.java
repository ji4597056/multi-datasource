package com.github.ji4597056.support.common.druid;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.bind.PropertySourcesPropertyValues;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;

/**
 * multiple datasource setting
 *
 * @author Jeffrey
 * @since 2018/01/15 0:56
 */
@ConfigurationProperties(prefix = MultiDataSourceProperties.DATASOURCE_PREFIX)
public class MultiDataSourceProperties {

    public static final String DATASOURCE_PREFIX = "spring.datasource";

    private Map<String, BaseDataSource> dataSources;
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private String name;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, BaseDataSource> getDataSources() {
        return dataSources;
    }

    public void setDataSources(
        Map<String, BaseDataSource> dataSources) {
        this.dataSources = dataSources;
    }

    public void addDataSource(String name, BaseDataSource baseDataSource) {
        if (dataSources == null) {
            dataSources = new HashMap<>();
        }
        dataSources.put(name, baseDataSource);
    }

    public static MultiDataSourceProperties buildProperties(ConfigurableEnvironment environment) {
        MultiDataSourceProperties properties = new MultiDataSourceProperties();
        if (environment != null) {
            MutablePropertySources propertySources = environment.getPropertySources();
            new RelaxedDataBinder(properties, MultiDataSourceProperties.DATASOURCE_PREFIX)
                .bind(new PropertySourcesPropertyValues(propertySources));
        }
        return properties;
    }

    public static class BaseDataSource {

        private String driverClassName;
        private String url;
        private String username;
        private String password;
        private String name;

        public String getDriverClassName() {
            return driverClassName;
        }

        public void setDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
