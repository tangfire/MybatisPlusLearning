package com.example.mybatispluslearning.test02;

import com.example.mybatispluslearning.entity.Person;
import com.example.mybatispluslearning.mapper.PersonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
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

    /**
     * 根据id查询
     */
    @Test
    public void selectById(){
        Person person = personMapper.selectById(100L);
        System.out.println(person);
    }


    /**
     * 根据一批id查询
     */
    @Test
    public void selectBatchIds(){
        List<Person> personList = personMapper.selectBatchIds(Arrays.asList(1,2,3));
        for (Person user : personList) {
            System.out.println(user);
        }
    }

    /**
     * 条件查询
     */
    @Test
    public void selectByMap(){
        // 封装条件
        HashMap<String, Object> param = new HashMap<>();
        param.put("id",100L);
        param.put("name","Fire");

        List<Person> personList = personMapper.selectByMap(param);
        for (Person user : personList) {
            System.out.println(user);
        }
    }

    /**
     * 根据id删除
     */
    @Test
    public void deleteById(){
        personMapper.deleteById(100L);
    }

    /**
     * 根据id删除一批
     */
    @Test
    public void deleteBatchIds(){
        personMapper.deleteBatchIds(Arrays.asList(1,2,3));
    }


    /**
     * 根据条件删除数据
     */
    @Test
    public void deleteByMap(){
        // 封装条件
        HashMap<String, Object> param = new HashMap<>();
        param.put("id",100L);
        param.put("name","Fire");

        personMapper.deleteByMap(param);


    }









}
