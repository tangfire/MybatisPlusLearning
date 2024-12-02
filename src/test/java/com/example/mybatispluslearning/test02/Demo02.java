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


    /**
     * QueryMapper的创建
     * select id,name,age,email from user
     */
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


    /**
     * 基本方法的使用
     */
    @Test
    public void test02(){
        QueryWrapper<Person> wrapper = new QueryWrapper<>();
//        QueryWrapper<Person> queryWrapper1 = Wrappers.query();

        String name = "Fire";
        wrapper.eq("name",name);
        List<Person> list = personMapper.selectList(wrapper);
        list.forEach(System.out::println);

        // name != 'Jack'
//        wrapper.ne("name","Jack");

        // age > 20
//        wrapper.gt("age",20);

        // age < 20
//        wrapper.lt("age",20);

        // age=20
//        wrapper.eq("age",20);

        // age between 20 and 24
//        wrapper.between("age",20,24);

        // age not between 20 and 24
//        wrapper.notBetween("age",20,24);

        // name like "%J%"          自动拼接左右的%
//        wrapper.like("name","J");

        // name not like "%J%"
//        wrapper.notLike("name","J");

        // name like "%J"
//        wrapper.likeLeft("name","J");

        // name like 'J%'
//        wrapper.likeRight("name","J");

        // name is null
//        wrapper.isNull("name");

        // name is not null
//        wrapper.isNotNull("name");

        // name in ('Jack','Tom','Jone')
//        wrapper.in("name","Jack","Tom","Jone");

        // name not in ('Jack','Tom','Jone')
//        wrapper.notIn("name","Jack","Tom","Jone");

    }


    /**
     * 子查询
     * name in (select name from user where age > 21)
     * name not in (select name from user where age > 21)
     */
    @Test
    public void test03(){
        QueryWrapper<Person> wrapper = new QueryWrapper<>();
        wrapper.inSql("name","select name from person where age > 21");

//        wrapper.notInSql("name","select name from person where age > 21");
        List<Person> list = personMapper.selectList(wrapper);
        list.forEach(System.out::println);
    }


    /**
     * 分组
     */
    @Test
    public void test04(){
        QueryWrapper<Person> wrapper = Wrappers.query();

        wrapper.groupBy("sex");
        List<Person> list = personMapper.selectList(wrapper);
        list.forEach(System.out::println);
    }

    /**
     * having操作
     */
    @Test
    public void test05() {

        //  创建wrapper对象
        QueryWrapper<Person> wrapper = Wrappers.query();

        // group by sex having sex = 0
        wrapper.groupBy("sex");
        wrapper.having("sex", "0");

        List<Person> users = personMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    /**
     * 排序
     */
    @Test
    public void test06() {
        //  创建wrapper对象
        QueryWrapper<Person> wrapper = Wrappers.query();

        /**
         * 参数1: 是否是Asc排序(升序), true : asc排序, false: desc排序
         * 参数2: 排序的字段
         */
        wrapper.orderByAsc("age");		// order by age asc

//        wrapper.orderByDesc("age");		// order by age desc

        List<Person> users = personMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    /**
     * and拼接条件
     */
    @Test
    public void test07(){
        QueryWrapper<Person> wrapper = Wrappers.query();
        wrapper.like("name","%i%")
                .lt("age",20)
                .eq("sex",0);

        List<Person> users = personMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }


    /**
     * or拼接条件
     */
    @Test
    public void test08(){
        QueryWrapper<Person> wrapper = Wrappers.query();

        wrapper.like("name","%i%")
                .or()
                .lt("age",20)
                .eq("sex",0);

        List<Person> users = personMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }



}
