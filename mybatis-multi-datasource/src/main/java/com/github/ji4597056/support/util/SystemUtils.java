package com.github.ji4597056.support.util;

import com.github.ji4597056.support.constant.SysConstant;

/**
 * system util
 *
 * @author Jeffrey
 * @since 2017/10/17 10:57
 */
public class SystemUtils {

    private SystemUtils() {
        throw new AssertionError("No SystemUtils instances for you!");
    }

    /**
     * 获取服务id
     *
     * @return service id
     */
    public static String getServiceId() {
        return PropertyUtils.getValue(SysConstant.APPLICATION_ID_KEY);
    }

    /**
     * 获取应用名
     *
     * @return application name
     */
    public static String getApplicationName() {
        return PropertyUtils.getValue(SysConstant.APPLICATION_NAME_KEY);
    }

    /**
     * 是否开启自定义debug模式
     *
     * @return is debug
     */
    public static boolean isDebug() {
        return Boolean.parseBoolean(PropertyUtils.getValue(SysConstant.APPLICATION_DEBUG_KEY));
    }
}
