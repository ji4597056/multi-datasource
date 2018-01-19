package com.github.ji4597056.entity;

import com.github.ji4597056.support.enums.RespCodeEnum;
import com.github.ji4597056.support.util.SystemUtils;

/**
 * 前端响应实体类型
 *
 * @author Jeffrey
 * @since 2017/7/5 17:47
 */
public class WebResponse {

    /**
     * response code
     */
    private String code;

    /**
     * response message
     */
    private String message;

    /**
     * success(操作逻辑是否成功)
     */
    private boolean success;

    /**
     * response data
     */
    private Object data;

    private WebResponse() {

    }

    private WebResponse(String code, String message, boolean success, Object data) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = data;
    }

    private static WebResponse create(String code, String message, boolean success, Object data) {
        return new WebResponse(code, message, success, data);
    }

    /**
     * 创建响应
     *
     * @param respCodeEnum RespCodeEnum
     * @param success is success
     * @param data response data
     * @return WebResponse
     */
    public static WebResponse createResp(RespCodeEnum respCodeEnum, boolean success, Object data) {
        return createResp(respCodeEnum.code(), respCodeEnum.value(), success, data);
    }

    /**
     * 创建响应
     *
     * @param code code
     * @param message message
     * @param success is success
     * @param data response data
     * @return WebResponse
     */
    public static WebResponse createResp(int code, String message, boolean success, Object data) {
        // 需要将服务id和响应码拼接
        return create(SystemUtils.getServiceId() + code, message, success, data);
    }

    /**
     * 创建成功响应
     *
     * @param data response data
     * @return WebResponse
     */
    public static WebResponse createSuccessResp(Object data) {
        return createResp(RespCodeEnum.SUCCESS, true, data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WebResponse{");
        sb.append("code='").append(code).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", success=").append(success);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
