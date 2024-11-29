package com.example.mybatispluslearning.test01;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatispluslearning.entity.User;
import com.example.mybatispluslearning.mapper.UserMapper;
import com.example.mybatispluslearning.mapper.UserMapper01;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class MybatisPlusApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserMapper01 userMapper01;

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
    void deleteTest01() {
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

    /**
     *分页查询
     */
    @Test
    void selectByPageTest() {
        // 两个参数：current的值默认是1，从1开始，不是0,指的是当前的页。size是每一页的条数。
        Page<User> page = new Page<>(2,3);
        Page<User> userPage = userMapper.selectPage(page, null);
        System.out.println("Records = "+userPage.getRecords());
        System.out.println("Pages = "+userPage.getPages());
        //page的其他方法
        System.out.println("当前页：" + page.getCurrent());
        System.out.println("总页数：" + page.getPages());
        System.out.println("记录数：" + page.getTotal());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("是否有下一页：" + page.hasNext());
    }

    @Test
    void deleteTest02() {
        userMapper.deleteById(4L);
        //批量删除
        userMapper.deleteBatchIds(Arrays.asList(1L,2L));
        //通过map删除
        Map<String, Object> map = new HashMap<>();
        map.put("name","tangfire");
        userMapper.deleteByMap(map);
    }

    /**
     * 逻辑删除
     * 查看日志输出可以看到，delete的语句变成了update语句，实质上就是update（修改）语句，将deleted字段从0修改为1以yml的配置作为参照
     */
    @Test
    void logicDeleteTest03() {
        userMapper.deleteById(1380808280388952066L);
    }

    /**
     * 模糊查询
     */
    @Test
    void fuzzyQueryTest() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();    //条件构造器
        wrapper.likeRight("user_name","zh%");   //也就是 name  like 'zh%'
        List<Object> users = userMapper.selectObjs(wrapper);
        System.out.println(users);
    }

    /**
     * 测试xml文件
     */
    @Test
    void xmlTest() {
        System.out.println(userMapper01.getUserName(1380808280388952068L));
    }
 
}