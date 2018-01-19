package com.github.ji4597056.dao.app;

import com.github.ji4597056.dao.Test1Db;
import com.github.ji4597056.entity.mybatis.StudentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * student mapper
 *
 * @author Jeffrey
 * @since 2018/01/10 17:59
 */
@Test1Db
@Mapper
public interface StudentMapper {

    StudentDO findById(@Param("id") Integer id);
}
