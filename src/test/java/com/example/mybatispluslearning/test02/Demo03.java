package com.example.mybatispluslearning.test02;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.mybatispluslearning.entity.Person;
import com.example.mybatispluslearning.mapper.PersonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Demo03 {

    @Autowired
    private PersonMapper personMapper;


    @Test
    public void test01(){
        QueryWrapper<Person> wrapper = new QueryWrapper<>();
        wrapper.select("id","name","sex");
        wrapper.in("id","5","6");
        List<Person> list = personMapper.selectList(wrapper);

        list.forEach(System.out::println);

    }

    @Test
    public void test02(){
        Person person = new Person();
        person.setId(5L);

//        QueryWrapper<Person> wrapper = new QueryWrapper<>(person);
        QueryWrapper<Person> wrapper = Wrappers.query(person);
        wrapper.select("id","name","sex");
        List<Person> list = personMapper.selectList(wrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test03() throws Exception {
        UpdateWrapper<Person> wrapper = Wrappers.update();

        // UpdateWrapper也是AbstractWrapper的子类,因此也具备一些基本的查询方法
        wrapper.like("name", "J");

        List<Person> userList = personMapper.selectList(wrapper);

        for (Person user : userList) {
            System.out.println(user);
        }
    }


    /**
     * 第一种方法: 使用wrapper来修改,并且指定查询条件
     *
     * @throws Exception
     */
    @Test
    public void test04() throws Exception {
        UpdateWrapper<Person> wrapper = Wrappers.update();
        wrapper.set("name", "Jackson");
        wrapper.set("age", "16");
        wrapper.set("sex", "1");
        wrapper.eq("id", 2L);

        // SQL: UPDATE person SET name=?, sex=?, age=? WHERE (id = ?)
        personMapper.update(null, wrapper);
    }


    /**
     * 第二种方法: 使用wrapper来封装条件,使用entity来封装修改的数据
     *
     * @throws Exception
     */
    @Test
    public void test05() throws Exception {
        UpdateWrapper<Person> wrapper = Wrappers.update();
        wrapper.eq("id", 2L);

        Person user = new Person(null, "Jack", "0", 28);

        // SQL: UPDATE person SET name=?, sex=?, age=? WHERE (id = ?)
        personMapper.update(user, wrapper);

    }

    /**
     * 第三种方法: Wrappers.update(user)传递查询的条件,使用wrapper来修改
     *
     * @throws Exception
     */
    @Test
    public void test06() throws Exception {
        Person user = new Person();
        user.setId(1L);

        // user当做查询条件
        UpdateWrapper<Person> wrapper = Wrappers.update(user);
        wrapper.set("name", "xiaohui");
        wrapper.set("sex", "0");
        wrapper.set("age", "22");

        // SQL : UPDATE person SET name=?,sex=?,age=? WHERE id=?
        personMapper.update(null, wrapper);
    }

    /**
     * setSql方法
     *
     * @throws Exception
     */
    @Test
    public void test07() throws Exception {
        UpdateWrapper<Person> wrapper = Wrappers.update();
        wrapper.setSql("name='abc',sex='0',age=18 where id=1");

        // SQL: UPDATE person SET name='abc',sex='0',age=18 where id=1
        personMapper.update(null, wrapper);
    }

}
