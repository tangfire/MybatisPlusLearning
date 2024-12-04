package com.example.mybatispluslearning.test03;

import com.example.mybatispluslearning.entity.Emp;
import com.example.mybatispluslearning.mapper.EmpMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Demo01 {

    @Autowired
    private EmpMapper empMapper;

    @Test
    public void test2() throws Exception {

        // 只是逻辑删除(把id为1的记录的is_delete改为1)
        empMapper.deleteById(1L);
    }

    @Test
    public void test3() throws Exception {

        // 不会查询is_delete为1的记录
        List<Emp> empList = empMapper.selectList(null);
        for (Emp emp : empList) {
            System.out.println(emp);
        }
    }


}
