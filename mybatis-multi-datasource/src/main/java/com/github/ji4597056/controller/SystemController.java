package com.github.ji4597056.controller;

import com.github.ji4597056.entity.WebResponse;
import com.github.ji4597056.support.enums.ExceptionGenerator;
import com.github.ji4597056.support.enums.RespCodeEnum;
import com.github.ji4597056.support.util.SystemUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * system Controller
 *
 * @author Jeffrey
 * @since 2017/10/13 14:16
 */
@RestController
@RequestMapping("/system")
public class SystemController {

    /**
     * 获取是否开启自定义debug模式
     *
     * @return WebResponse
     */
    @GetMapping("debug")
    public WebResponse isDebug() {
        return WebResponse.createSuccessResp(SystemUtils.isDebug());
    }

    /**
     * 获取service id
     *
     * @return WebResponse
     */
    @GetMapping("serviceId")
    public WebResponse getServiceId() {
        return WebResponse.createSuccessResp(SystemUtils.getServiceId());
    }

    /**
     * 获取异常枚举
     *
     * @return WebResponse
     */
    @GetMapping("exceptions")
    public WebResponse getExceptionEnums() {
        return WebResponse.createSuccessResp(ExceptionGenerator.getEnumValues());
    }

    /**
     * 获取请求异常响应码枚举
     *
     * @return WebResponse
     */
    @GetMapping("responseCodes")
    public WebResponse getRespCodeEnums() {
        return WebResponse.createSuccessResp(RespCodeEnum.getEnumValues());
    }
}
