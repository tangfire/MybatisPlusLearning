package com.example.mybatispluslearning.test02;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatispluslearning.entity.Person;
import com.example.mybatispluslearning.mapper.PersonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class Demo05 {

    @Autowired
    private PersonMapper personMapper;

    /**
     * 无条件分页查询
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {

        // 封装分页信息
        Page<Person> page = new Page<>(1,3);

        /*
         执行分页查询,并将结果封装到page中
            参数1: 分页配置
            参数2: 查询条件
         */
        personMapper.selectPage(page, null);

        // 当前页数据
        List<Person> pageData = page.getRecords();
        for (Person user : pageData) {
            System.out.println(user);
        }

        System.out.println("------------");

        System.out.println("当前页：" + page.getCurrent());
        System.out.println("每页显示的条数：" + page.getSize());
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("总页数：" + page.getPages());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("是否有下一页：" + page.hasNext());
    }


    /**
     * 带条件分页查询
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {

        QueryWrapper<Person> wrapper = Wrappers.query();
        wrapper.like("name", "a");

        // 封装分页信息
        Page<Person> page = new Page<>(1, 3);

    /*
     执行分页查询,并将结果封装到page中
        参数1: 分页配置
        参数2: 查询条件
     */
        personMapper.selectPage(page, wrapper);

        // 当前页数据
        List<Person> pageData = page.getRecords();
        for (Person user : pageData) {
            System.out.println(user);
        }

        System.out.println("------------");

        System.out.println("当前页：" + page.getCurrent());
        System.out.println("每页显示的条数：" + page.getSize());
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("总页数：" + page.getPages());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("是否有下一页：" + page.hasNext());
    }

    /**
     * 查询结果以Map返回
     *
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {

        // 封装分页信息
        Page page = new Page<>(1, 3);

        personMapper.selectMapsPage(page, null);

        // 每一条记录都是一个HashMap
        List<HashMap<String,Object>> pageData = page.getRecords();
        for (HashMap userMap : pageData) {
            System.out.println(userMap);
        }

        System.out.println("------------");

        System.out.println("当前页：" + page.getCurrent());
        System.out.println("每页显示的条数：" + page.getSize());
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("总页数：" + page.getPages());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("是否有下一页：" + page.hasNext());
    }






}
