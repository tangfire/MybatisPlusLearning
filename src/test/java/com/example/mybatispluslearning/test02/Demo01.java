package com.example.mybatispluslearning.test02;

import com.example.mybatispluslearning.entity.Person;
import com.example.mybatispluslearning.mapper.PersonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Demo01 {

    @Autowired
    private PersonMapper personMapper;

    /**
     * 查询所有
     */
    @Test
    public void selectTest(){
        // 传递null代表查询全部
        List<Person> personList = personMapper.selectList(null);
        for (Person user : personList) {
            System.out.println(user);
        }
    }

    /**
     * 插入
     */
    @Test
    public void insertTest(){
        Person person = new Person(100L,"Fire","0",20);
        personMapper.insert(person);
    }

    /**
     * 修改
     */
    @Test
    public void updateTest(){
        Person person = new Person(100L,"Fire","0",25);
        personMapper.updateById(person);
    }








}
