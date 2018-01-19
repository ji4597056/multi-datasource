package com.github.ji4597056.dao;

import com.github.ji4597056.support.common.mybatis.DataSourceType;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * test1 datasource type
 *
 * @author Jeffrey
 * @since 2018/01/15 23:58
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@DataSourceType("test1Db")
public @interface Test1Db {

}
