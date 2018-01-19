package com.github.ji4597056.controller;

import com.github.ji4597056.entity.WebResponse;
import com.github.ji4597056.service.TestService;
import com.github.ji4597056.support.constant.SysConstant;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * test controller
 *
 * @author Jeffrey
 * @since 2018/01/10 18:26
 */
@RestController
@RequestMapping(SysConstant.SERVICE_V3_VERSION + "/test")
public class TestController {

    @Autowired
    private TestService testService;

    /**
     * 测试查询
     *
     * @param studentId student id
     * @return WebResponse
     */
    @ApiOperation("测试查询")
    @GetMapping("/students/{student_id}")
    public WebResponse testFindUserAndApp(@PathVariable("student_id") Integer studentId) {
        return WebResponse.createSuccessResp(testService.testFindStudent(studentId));
    }
}
