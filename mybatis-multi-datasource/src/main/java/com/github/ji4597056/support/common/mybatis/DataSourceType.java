package com.github.ji4597056.support.common.mybatis;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * datasource type
 *
 * @author Jeffrey
 * @since 2018/01/15 10:08
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSourceType {

    /**
     * datasource type name
     *
     * @return name
     */
    String value();
}
