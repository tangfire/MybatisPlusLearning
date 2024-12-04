package com.example.mybatispluslearning.test03;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.mybatispluslearning.entity.Student;
import com.example.mybatispluslearning.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Demo02 {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void test1() throws Exception {
        System.out.println(studentMapper.selectById(1L));
        System.out.println(studentMapper.selectById(2L));
    }


    @Test
    public void test2() throws Exception {

        QueryWrapper<Student> wrapper = Wrappers.query();
        // 搜索条件为0依旧可以搜索出来
        wrapper.eq("sex","0");

        List<Student> stuList = studentMapper.selectList(wrapper);
        for (Student student : stuList) {
            System.out.println(student);
        }
    }


}
