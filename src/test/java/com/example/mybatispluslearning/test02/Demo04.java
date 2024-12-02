package com.example.mybatispluslearning.test02;

import com.example.mybatispluslearning.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Demo04 {

    @Autowired
    private PersonMapper personMapper;
}
