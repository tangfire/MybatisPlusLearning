package com.example.mybatispluslearning.test02;

import com.example.mybatispluslearning.entity.Person;
import com.example.mybatispluslearning.service.IPersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Demo07 {

    @Autowired
    private IPersonService personService;

    @Test
    public void test1() throws Exception {
        Person user = new Person(111L, "xiaohui", "0", 20);
        personService.save(user);
    }

}
