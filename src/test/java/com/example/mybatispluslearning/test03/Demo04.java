package com.example.mybatispluslearning.test03;

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

    @Test
    public void test01() {
        List<Person> persons =   personMapper.findAll();

        for (Person person : persons) {
            System.out.println(person);
        }

    }

}
