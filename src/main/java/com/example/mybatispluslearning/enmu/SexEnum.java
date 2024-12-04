package com.example.mybatispluslearning.enmu;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author lscl
 * @version 1.0
 * @intro:
 */
public enum SexEnum {
    MAN(0, "男"),
    WOMAN(1, "女");

    // 这一列的值和数据库表映射
    @EnumValue      
    private Integer sexValue;
    
    // 这个字段就是这个枚举项的字符串含义
    private String sexName;

    private SexEnum(Integer sexValue, String sexName) {
        this.sexValue = sexValue;
        this.sexName = sexName;
    }
}
