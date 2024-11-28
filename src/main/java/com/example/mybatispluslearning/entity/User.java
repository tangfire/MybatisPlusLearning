package com.example.mybatispluslearning.entity;
 
import com.baomidou.mybatisplus.annotation.*;
import lombok.*;
import lombok.experimental.Accessors;
 
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


@AllArgsConstructor //有参构造
@NoArgsConstructor  //无参构造
@Data      //get、set方法和重新toString方法
@Accessors(chain = true)
public class User implements Serializable {
 
    private static final long serialVersionUID = 1L;
 
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
 
    /**
     * 用户名
     */
    private String userName;
 
    /**
     * 密码
     */
    private String password;
 
    /**
     * 手机
     */
    private String phone;
 
    /**
     * 邮箱
     */
    private String email;
 
    /**
     * 年龄
     */
    private Integer age;
 
    /**
     * 角色
     */
    private String role;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)   //使用的是插入策略
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @Version //乐观锁注解
    private int version;
}