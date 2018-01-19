package com.github.ji4597056.support.constant;

/**
 * system constant
 *
 * @author Jeffrey
 * @since 2017/06/30 13:48
 */
public final class SysConstant {

    private SysConstant() {
        throw new AssertionError("No SysConstant instances for you!");
    }

    /**
     * service v3 version
     */
    public static final String SERVICE_V3_VERSION = "v1";

    /**
     * debug prop key
     */
    public static final String APPLICATION_DEBUG_KEY = "spring.application.custom.debug";

    /**
     * service id prop key
     */
    public static final String APPLICATION_ID_KEY = "spring.application.custom.id";

    /**
     * application name prop key
     */
    public static final String APPLICATION_NAME_KEY = "spring.application.name";
}
