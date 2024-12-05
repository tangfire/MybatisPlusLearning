package com.example.mybatispluslearning.test03;

import com.example.mybatispluslearning.entity.Client;
import com.example.mybatispluslearning.mapper.ClientMapperSlave01;
import com.example.mybatispluslearning.mapper.ClientMapperSlave02;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Demo06 {

    @Autowired
    private ClientMapperSlave01 clientMapperSlave01;

    @Autowired
    private ClientMapperSlave02 clientMapperSlave02;


    @Test
    public void test1() {

        // 使用的是master数据源
        List<Client> clientList = clientMapperSlave01.selectList(null);
        for (Client client : clientList) {
            System.out.println(client);
        }
    }


    @Test
    public void test2() {

        // 使用的是master数据源
        List<Client> clientList = clientMapperSlave02.selectList(null);
        for (Client client : clientList) {
            System.out.println(client);
        }
    }

}
