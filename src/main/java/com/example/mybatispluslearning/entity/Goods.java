package com.example.mybatispluslearning.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
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
public class Goods {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer count;

    @Version            // 乐观锁字段
    private Integer version;
}
