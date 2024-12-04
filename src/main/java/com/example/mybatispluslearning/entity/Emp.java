package com.example.mybatispluslearning.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
public class Emp {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;

    /*
        表示该列为逻辑删除字段
        0: 表示未删除
        1: 表示已删除
     */
    @TableLogic
    private Integer isDelete;
}
