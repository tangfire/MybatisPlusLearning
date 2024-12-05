package com.example.mybatispluslearning.test03;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.example.mybatispluslearning.entity.Person;
import com.example.mybatispluslearning.mapper.PersonMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author lscl
 * @version 1.0
 * @intro:
 */
@SpringBootTest()
public class Demo12_ActiveRecord {

    @Autowired
    private PersonMapper personMapper;

    /**
     * 新增
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        Person person = new Person(100L,"xiaoming","0",20);
        person.insert();
    }

    /**
     * 删除
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        Person person = new Person();
        person.setId(100L);
        person.deleteById();
    }

    /**
     * 修改
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        Person person = new Person(1L,"xiaohui","1",22);
        person.updateById();
    }

    /**
     * 根据id查询
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        Person person = new Person();
        person.setId(100L);
        Person dbPerson = person.selectById();
        System.out.println(dbPerson);
    }


    /**
     * 查询全部
     * @throws Exception
     */
    @Test
    public void test5() throws Exception {
        Person person = new Person();
        List<Person> personList = person.selectAll();
        for (Person dbPerson : personList) {
            System.out.println(dbPerson);
        }
    }

    /**
     * 根据Wrapper条件查询
     * @throws Exception
     */
    @Test
    public void test6() throws Exception {

        QueryWrapper<Person> wrapper = Wrappers.query();
        wrapper.like("name","a");

        Person person = new Person();
        
        // 根据条件查询
        List<Person> personList = person.selectList(wrapper);
        for (Person dbPerson : personList) {
            System.out.println(dbPerson);
        }
    }

    /**
     * 分页查询
     * @throws Exception
     */
    @Test
    public void test7() throws Exception {

        QueryWrapper<Person> wrapper = Wrappers.query();
        wrapper.like("name","a");

        Page<Person> page=new Page<>(1,3);
        Person person = new Person();
       
        // 分页查询
        person.selectPage(page,wrapper);

        List<Person> records = page.getRecords();
        for (Person record : records) {
            System.out.println(record);
        }
    }
}
