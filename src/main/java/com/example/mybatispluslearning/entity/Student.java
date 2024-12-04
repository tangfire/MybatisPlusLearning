package com.example.mybatispluslearning.entity;


import com.example.mybatispluslearning.enmu.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lscl
 * @version 1.0
 * @intro:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer id;
    private String name;
    
    /*
        使用枚举类型
            当从数据库中查询到了0则赋值SexEnum.MAN给sex变量
            当从数据库中查询到了1则赋值SexEnum.WOMAN给sex变量
     */
    private SexEnum sex;
}
