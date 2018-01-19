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
 * student entity
 *
 * @author Jeffrey
 * @since 2018/1/10 18:02
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Alias("student")
public class StudentDO {

    private Integer id;

    private String name;

    private Integer age;

    private Integer classId;
}