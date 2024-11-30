package com.example.mybatispluslearning.test02;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.mybatispluslearning.entity.Person;
import com.example.mybatispluslearning.mapper.PersonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Demo02 {
    @Autowired
    PersonMapper personMapper;

    @Test
    public void test01(){
        // 创建QueryMapper,默认情况下查询所有数据
        QueryWrapper<Person> queryWrapper = new QueryWrapper<>();
        QueryWrapper<Person> queryWrapper1 = Wrappers.query();
        List<Person> list = personMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
        System.out.println("-----------------------------------");
        List<Person> list1 = personMapper.selectList(queryWrapper1);
        list1.forEach(System.out::println);
    }
}
