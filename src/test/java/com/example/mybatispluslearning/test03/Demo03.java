package com.example.mybatispluslearning.test03;

import com.example.mybatispluslearning.entity.Goods;
import com.example.mybatispluslearning.mapper.GoodsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Demo03 {

    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    public void test01(){
        Goods goods = goodsMapper.selectById(2L);
        goods.setCount(goods.getCount()-1);
        goodsMapper.updateById(goods); // 修改完毕后,version在本次version的基础上+1
    }
}
