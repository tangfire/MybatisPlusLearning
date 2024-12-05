package com.example.mybatispluslearning.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatispluslearning.entity.Client;
import org.apache.ibatis.annotations.Mapper;

@DS("slave02")
@Mapper
public interface ClientMapperSlave02 extends BaseMapper<Client> {

}
