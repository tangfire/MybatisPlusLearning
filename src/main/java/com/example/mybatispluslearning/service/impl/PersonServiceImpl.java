package com.example.mybatispluslearning.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatispluslearning.entity.Person;
import com.example.mybatispluslearning.mapper.PersonMapper;
import com.example.mybatispluslearning.service.IPersonService;
import org.springframework.stereotype.Service;

@Service("personService")
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements IPersonService {
}
