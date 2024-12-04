package com.example.mybatispluslearning.test02;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.mybatispluslearning.entity.Person;
import com.example.mybatispluslearning.mapper.PersonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Demo04 {

    @Autowired
    private PersonMapper personMapper;

    /**
     * 使用QueryWrapper
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {

        QueryWrapper<Person> wrapper = Wrappers.query();
        wrapper.eq("id","2");

        List<Person> userList = personMapper.selectList(wrapper);
        for (Person user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 使用LambdaQueryWrapper
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {

        LambdaQueryWrapper<Person> wrapper = Wrappers.lambdaQuery();

        // id=1
//        wrapper.eq(Person::getId,1);

        // select id,name,age from person where id in (5,2,6) and name like "%a%"
        wrapper.in(Person::getId,"5","2","6")
                .like(Person::getName,"a")
                .select(Person::getId,Person::getName,Person::getAge);

        List<Person> userList = personMapper.selectList(wrapper);
        for (Person user : userList) {
            System.out.println(user);
        }
    }


    /**
     * 使用UpdateWrapper
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        UpdateWrapper<Person> wrapper = Wrappers.update();
        wrapper.eq("id","2");
        wrapper.set("name","abb");
        wrapper.set("age",18);

        personMapper.update(wrapper);
    }


    /**
     * 使用LambdaUpdateWrapper
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        LambdaUpdateWrapper<Person> wrapper = Wrappers.lambdaUpdate();

        wrapper.eq(Person::getId,2);
        wrapper.set(Person::getName,"xiaolan");
        wrapper.set(Person::getAge,18);

        personMapper.update(wrapper);
    }


}
