package com.github.ji4597056.support.common.mybatis;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * dynamic datasource
 *
 * @author Jeffrey
 * @since 2018/01/10 16:58
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DatabaseContextHolder.getDbType();
    }
}
