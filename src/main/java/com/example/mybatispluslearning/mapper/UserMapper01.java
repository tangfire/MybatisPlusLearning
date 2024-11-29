package com.example.mybatispluslearning.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatispluslearning.entity.User;

public interface UserMapper01 extends BaseMapper<User> {
    String getUserName(Long id);
}
