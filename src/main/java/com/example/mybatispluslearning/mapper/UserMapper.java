package com.example.mybatispluslearning.mapper;
 
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.example.mybatispluslearning.entity.User;
import org.apache.ibatis.annotations.Mapper;
 
@Mapper
public interface UserMapper extends BaseMapper<User> {
}