package com.github.ji4597056.dao.user;

import com.github.ji4597056.dao.Test2Db;
import com.github.ji4597056.entity.mybatis.ClassDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * user mapper
 *
 * @author Jeffrey
 * @since 2017/1/9 15:46
 */
@Test2Db
@Mapper
public interface ClassMapper {

    ClassDO findById(@Param("id") Integer id);
}
