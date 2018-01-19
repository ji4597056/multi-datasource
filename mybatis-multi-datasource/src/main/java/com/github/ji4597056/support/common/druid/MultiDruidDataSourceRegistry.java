package com.github.ji4597056.support.common.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.github.ji4597056.support.common.druid.MultiDataSourceProperties.BaseDataSource;
import java.util.Optional;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.context.annotation.ScopeMetadataResolver;

/**
 * multiple druid datasource registry
 *
 * @author Jeffrey
 * @since 2018/01/12 16:16
 */
public class MultiDruidDataSourceRegistry implements BeanDefinitionRegistryPostProcessor {

    private ScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver();

    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

    private BaseDruidDataSourceFactory druidDataSourceFactory;

    private MultiDataSourceProperties dataSourceProperties;

    public MultiDruidDataSourceRegistry(
        BaseDruidDataSourceFactory druidDataSourceFactory,
        MultiDataSourceProperties dataSourceProperties) {
        this.druidDataSourceFactory = druidDataSourceFactory;
        this.dataSourceProperties = dataSourceProperties;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
        throws BeansException {
        dataSourceProperties.getDataSources().forEach((name, baseDataSource) -> {
            BeanDefinition definition = beanFactory.getBeanDefinition(getBeanName(name));
            MutablePropertyValues propertyValues = definition.getPropertyValues();
            // inject druid property values
            druidDataSourceFactory.getConfigProperties().forEach(propertyValues::addPropertyValue);
            // inject base datasource property values
            removeBaseProperties(propertyValues);
            resetBaseProperties(propertyValues, baseDataSource, name);
        });
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
        throws BeansException {
        dataSourceProperties.getDataSources()
            .forEach((name, baseDataSource) -> registerBean(registry, getBeanName(name),
                DruidDataSource.class));
    }

    private void registerBean(BeanDefinitionRegistry registry, String name, Class<?> beanClass) {
        AnnotatedGenericBeanDefinition abd = new AnnotatedGenericBeanDefinition(beanClass);
        ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(abd);
        abd.setScope(scopeMetadata.getScopeName());
        String beanName = (name != null ? name
            : this.beanNameGenerator.generateBeanName(abd, registry));
        AnnotationConfigUtils.processCommonDefinitionAnnotations(abd);
        BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(abd, beanName);
        BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder, registry);
    }

    private void resetBaseProperties(MutablePropertyValues propertyValues,
        BaseDataSource baseDataSource, String name) {
        propertyValues.addPropertyValue(DruidDataSourceFactory.PROP_NAME,
            Optional.ofNullable(baseDataSource.getName()).orElse(name));
        propertyValues.addPropertyValue(DruidDataSourceFactory.PROP_DRIVERCLASSNAME,
            baseDataSource.getDriverClassName());
        propertyValues.addPropertyValue(DruidDataSourceFactory.PROP_URL, baseDataSource.getUrl());
        propertyValues
            .addPropertyValue(DruidDataSourceFactory.PROP_USERNAME, baseDataSource.getUsername());
        propertyValues
            .addPropertyValue(DruidDataSourceFactory.PROP_PASSWORD, baseDataSource.getPassword());
    }

    private void removeBaseProperties(MutablePropertyValues propertyValues) {
        propertyValues.removePropertyValue(DruidDataSourceFactory.PROP_NAME);
        propertyValues.removePropertyValue(DruidDataSourceFactory.PROP_DRIVERCLASSNAME);
        propertyValues.removePropertyValue(DruidDataSourceFactory.PROP_URL);
        propertyValues.removePropertyValue(DruidDataSourceFactory.PROP_USERNAME);
        propertyValues.removePropertyValue(DruidDataSourceFactory.PROP_PASSWORD);
    }

    private String getBeanName(String name) {
        return "DataSource" + name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
