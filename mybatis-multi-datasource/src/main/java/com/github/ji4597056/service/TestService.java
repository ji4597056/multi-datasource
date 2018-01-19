package com.github.ji4597056.service;

import com.github.ji4597056.dao.app.StudentMapper;
import com.github.ji4597056.dao.user.ClassMapper;
import com.github.ji4597056.entity.vo.StudentVO;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * test service
 *
 * @author Jeffrey
 * @since 2018/01/10 18:01
 */
@Service
public class TestService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClassMapper classMapper;

    /**
     * 测试查询
     *
     * @param studentId student id
     * @return TestVO
     */
    public StudentVO testFindStudent(Integer studentId) {
        return Optional.ofNullable(studentMapper.findById(studentId)).map(
            student -> StudentVO.builder().id(student.getId()).age(student.getAge())
                .name(student.getName())
                .className(classMapper.findById(student.getClassId()).getClassName()).build())
            .orElse(null);
    }
}
