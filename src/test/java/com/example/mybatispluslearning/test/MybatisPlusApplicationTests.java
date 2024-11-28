package com.example.mybatispluslearning.test;

import com.example.mybatispluslearning.entity.User;
import com.example.mybatispluslearning.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {
    @Autowired
    private UserMapper userMapper;

    /**
     * 全部查询
     */
    @Test
    void test01() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);     
    }

    /**
     * 插入操作
     */
    @Test
    void insertTest() {
        User user = new User();
        user.setAge(12);
        user.setEmail("123456789@qq.com");
        user.setPassword("123456789");
        user.setRole("学生");
        user.setPhone("18390205641");
        user.setUserName("tangfire");
        userMapper.insert(user);
    }

    /**
     * 更新操作
     */
    @Test
    void updateTest() {
        User user = new User();
        user.setId(1380808280388952068L);
        user.setUserName("tangfire216");
        userMapper.updateById(user);
    }

    /**
     * 删除操作
     */
    @Test
    void deleteTest() {
        User user = new User();
        user.setId(1380808280388952067L);
        user.setUserName("tangfire:update version");
        userMapper.deleteById(user);
    }

    /**
     * 乐观锁测试
     * 乐观锁的操作理念是“乐观”，它假设并发操作中数据冲突的可能性较小，因此在进行数据操作时不会直接锁定数据。
     * 只是在执行更新操作时，会判断在此期间是否有其他操作修改了数据。
     * 如果有其他操作修改了数据，那么乐观锁会放弃操作，否则执行操作。
     */
    @Test
    void test02() {
        User user1 = userMapper.selectById(1380808280388952068L);
        user1.setUserName("tangfire1");
        user1.setAge(19);

        User user2 = userMapper.selectById(1380808280388952068L);
        user2.setAge(20);
        user2.setUserName("tangfire2");
        userMapper.updateById(user2);//当前插队操作会改变version的值本来version为1的，但是经过此次操作后version就会变成2

        userMapper.updateById(user1);//当前id中的version为2了，但是user1中保存的version还是1，所以这个地方就会操作失败
    }

    /**
     * 根据id查询用户
     */
    @Test
    void selectTest01() {
        User user = userMapper.selectById(1380808280388952068L);
        System.out.println(user);
    }

    /**
     * 批量查询
     */
    @Test
    void selectTest02() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1380808280388952068L,1380808280388952066L));
        System.out.println(users);
    }

    /**
     * 条件查询
     */
    @Test
    void selectTest03() {
        HashMap<String,Object> map = new HashMap<>();
        //自定义查询
        map.put("user_name","tangfire2");
        map.put("age",20);
        List<User> users = userMapper.selectByMap(map);
        System.out.println(users);
    }
 
}