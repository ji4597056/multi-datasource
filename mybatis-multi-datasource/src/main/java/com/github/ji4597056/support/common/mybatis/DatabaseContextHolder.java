package com.github.ji4597056.support.common.mybatis;

import org.springframework.util.Assert;

/**
 * database context holder
 *
 * @author Jeffrey
 * @since 2018/01/10 16:27
 */
public class DatabaseContextHolder {

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * set db type
     *
     * @param dbType DatabaseType
     */
    public static void setDbType(String dbType) {
        Assert.notNull(dbType, "database type requires not null!");
        CONTEXT_HOLDER.set(dbType);
    }

    /**
     * get db type
     *
     * @return DatabaseType
     */
    public static String getDbType() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * clear db type
     */
    public static void clearDbType() {
        CONTEXT_HOLDER.remove();
    }
}
