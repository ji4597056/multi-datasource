package com.github.ji4597056.support.constant;

/**
 * 响应体key常量
 *
 * @author Jeffrey
 * @since 2017/11/10 10:13
 */
public class WebResponseConstant {

    private WebResponseConstant() {
        throw new AssertionError("No WebResponseConstant instances for you!");
    }

    /**
     * code
     */
    public static final String WEB_RESPONSE_CODE = "code";

    /**
     * message
     */
    public static final String WEB_RESPONSE_MESSAGE = "message";

    /**
     * success
     */
    public static final String WEB_RESPONSE_SUCCESS = "success";

    /**
     * data
     */
    public static final String WEB_RESPONSE_DATA = "data";

    /**
     * resource id
     */
    public static final String WEB_RESPONSE_RESOURCE_ID = "resource_id";

    /**
     * resource name
     */
    public static final String WEB_RESPONSE_RESOURCE_NAME = "resource_name";

    /**
     * resource log
     */
    public static final String WEB_RESPONSE_RESOURCE_LOG = "resource_log";
}
