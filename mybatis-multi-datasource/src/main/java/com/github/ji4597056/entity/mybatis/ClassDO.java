package com.github.ji4597056.entity.mybatis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

/**
 * 用户表实体类
 *
 * @author zoufeng
 * @since 2017/10/23
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Alias("class")
public class ClassDO {

    private Integer id;

    private String className;
}
