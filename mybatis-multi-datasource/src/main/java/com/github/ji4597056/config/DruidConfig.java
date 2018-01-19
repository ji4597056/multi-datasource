package com.github.ji4597056.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.github.ji4597056.support.common.druid.BaseDruidDataSourceFactory;
import com.github.ji4597056.support.common.druid.DefaultDruidProperties;
import com.github.ji4597056.support.common.druid.MultiDataSourceProperties;
import com.github.ji4597056.support.common.druid.MultiDataSourceProperties.BaseDataSource;
import com.github.ji4597056.support.common.druid.MultiDruidDataSourceRegistry;
import com.github.ji4597056.support.profile.DruidEnv;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

/**
 * druid datasource config(support multi-datasource)
 *
 * @author Jeffrey
 * @since 2017/1/9 15:44
 */
@Configuration
@DruidEnv
public class DruidConfig implements EnvironmentAware {

    @Autowired
    private Environment environment;

    @Bean
    public MultiDruidDataSourceRegistry multiDruidDataSourceRegistry() {
        MultiDataSourceProperties multiDataSourceProperties = MultiDataSourceProperties
            .buildProperties((ConfigurableEnvironment) environment);
        // single datasource
        if (StringUtils.hasText(multiDataSourceProperties.getUrl())) {
            String name = Optional.ofNullable(multiDataSourceProperties.getName())
                .orElse("defaultDb");
            BaseDataSource baseDataSource = new BaseDataSource();
            baseDataSource.setName(name);
            baseDataSource.setDriverClassName(multiDataSourceProperties.getDriverClassName());
            baseDataSource.setUrl(multiDataSourceProperties.getUrl());
            baseDataSource.setUsername(multiDataSourceProperties.getUsername());
            baseDataSource.setPassword(multiDataSourceProperties.getPassword());
            multiDataSourceProperties.addDataSource(name, baseDataSource);
        }
        return new MultiDruidDataSourceRegistry(druidDataSourceFactory(),
            multiDataSourceProperties);
    }

    @Bean
    public BaseDruidDataSourceFactory druidDataSourceFactory() {
        return new BaseDruidDataSourceFactory(DefaultDruidProperties.buildProperties(
            (ConfigurableEnvironment) environment));
    }

    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        ServletRegistrationBean servletRegistrationBean =
            new ServletRegistrationBean(new StatViewServlet(), "/admin/druid/*");
        servletRegistrationBean.addInitParameter("resetEnable", "true");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(
            new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter(
            "exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/admin/druid/*");
        return filterRegistrationBean;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
