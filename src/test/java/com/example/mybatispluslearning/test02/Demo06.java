package com.example.mybatispluslearning.test02;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatispluslearning.entity.Person;
import com.example.mybatispluslearning.mapper.PersonMapper;
import com.example.mybatispluslearning.service.IPersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class Demo06 {

    @Autowired
    private IPersonService personService;

    /**
     * 新增
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        Person user = new Person(null, "xiaohui", "0", 20);
        personService.save(user);
    }

    /**
     * 如果id存在则修改,不存在则新增
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        Person user = new Person(1L, "xiaohui", "0", 20);
        personService.saveOrUpdate(user);
    }

    /**
     * 根据id删除
     *
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        personService.removeById(1L);
    }

    /**
     * 根据id修改
     *
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {

        Person user = new Person(1L, "xiaolan", "1", 18);
        personService.updateById(user);
    }

    /**
     * 根据id查询
     *
     * @throws Exception
     */
    @Test
    public void test5() throws Exception {
        Person user = personService.getById(1L);
        System.out.println(user);
    }

    /**
     * 查询列表
     *
     * @throws Exception
     */
    @Test
    public void test6() throws Exception {

        QueryWrapper<Person> wrapper = Wrappers.query();
        wrapper.in("id", "1", "2");

        // 查询所有
//        List<User> userList = userService.list();

        // 通过wrapper查询
        List<Person> userList = personService.list(wrapper);

        for (Person user : userList) {
            System.out.println(user);
        }
    }


    /**
     * 查询总记录数
     *
     * @throws Exception
     */
    @Test
    public void test7() throws Exception {

        QueryWrapper<Person> wrapper = Wrappers.query();
        wrapper.like("name", "a");

        // 查询总记录数
//        int count = userService.count();

        // 根据条件查询总记录数
        long count = personService.count(wrapper);

        System.out.println(count);
    }

    /**
     * 分页查询(当前页类型为指定类型)
     *
     * @throws Exception
     */
    @Test
    public void test8() throws Exception {

        Page<Person> page = new Page<>(1, 3);

        personService.page(page);

        // 当前页数据
        List<Person> pageData = page.getRecords();
        for (Person user : pageData) {
            System.out.println(user);
        }

        System.out.println("------------");

        System.out.println("当前页：" + page.getCurrent());
        System.out.println("每页显示的条数：" + page.getSize());
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("总页数：" + page.getPages());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("是否有下一页：" + page.hasNext());
    }


    /**
     * 分页查询(当前页结果为HashMap类型)
     *
     * @throws Exception
     */
    @Test
    public void test9() throws Exception {

        Page page = new Page<>(1, 3);

        personService.pageMaps(page);

        // 当前页数据
        List<HashMap<String, Object>> pageData = page.getRecords();
        for (HashMap userMap : pageData) {
            System.out.println(userMap);
        }

        System.out.println("------------");

        System.out.println("当前页：" + page.getCurrent());
        System.out.println("每页显示的条数：" + page.getSize());
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("总页数：" + page.getPages());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("是否有下一页：" + page.hasNext());
    }


    /**
     * 链式查询
     *
     * @throws Exception
     */
    @Test
    public void test10() throws Exception {

        QueryChainWrapper<Person> chainWrapper = personService.query();

        // SQL: SELECT id,name,age FROM user WHERE (id IN (?,?,?) AND name LIKE ?)
        List<Person> userList = chainWrapper.select("id", "name", "age")
                .in("id", "1", "2", "3")
                .like("name", "a")
                .list();

        for (Person user : userList) {
            System.out.println(user);
        }
    }


    /**
     * 链式修改
     *
     * @throws Exception
     */
    @Test
    public void test11() throws Exception {

        UpdateChainWrapper<Person> chainWrapper = personService.update();

        // SQL: UPDATE user SET age=? WHERE (id IN (?,?) OR sex = ?)
        chainWrapper.in("id","1","2")
                .or()
                .eq("sex","0")
                .set("age",20).
                update();
    }



}
