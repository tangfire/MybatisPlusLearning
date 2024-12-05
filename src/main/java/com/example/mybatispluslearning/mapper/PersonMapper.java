package com.example.mybatispluslearning.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatispluslearning.entity.Person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PersonMapper extends BaseMapper<Person> {
    List<Person> findAll();
}
