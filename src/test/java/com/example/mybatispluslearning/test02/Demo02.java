package com.example.mybatispluslearning.test02;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.mybatispluslearning.entity.Person;
import com.example.mybatispluslearning.mapper.PersonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.function.Consumer;

@SpringBootTest
public class Demo02 {
    @Autowired
    PersonMapper personMapper;


    /**
     * QueryMapper的创建
     * select id,name,age,email from user
     */
    @Test
    public void test01(){
        // 创建QueryMapper,默认情况下查询所有数据
        QueryWrapper<Person> queryWrapper = new QueryWrapper<>();
        QueryWrapper<Person> queryWrapper1 = Wrappers.query();
        List<Person> list = personMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
        System.out.println("-----------------------------------");
        List<Person> list1 = personMapper.selectList(queryWrapper1);
        list1.forEach(System.out::println);
    }


    /**
     * 基本方法的使用
     */
    @Test
    public void test02(){
        QueryWrapper<Person> wrapper = new QueryWrapper<>();
//        QueryWrapper<Person> queryWrapper1 = Wrappers.query();

        String name = "Fire";
        wrapper.eq("name",name);
        List<Person> list = personMapper.selectList(wrapper);
        list.forEach(System.out::println);

        // name != 'Jack'
//        wrapper.ne("name","Jack");

        // age > 20
//        wrapper.gt("age",20);

        // age < 20
//        wrapper.lt("age",20);

        // age=20
//        wrapper.eq("age",20);

        // age between 20 and 24
//        wrapper.between("age",20,24);

        // age not between 20 and 24
//        wrapper.notBetween("age",20,24);

        // name like "%J%"          自动拼接左右的%
//        wrapper.like("name","J");

        // name not like "%J%"
//        wrapper.notLike("name","J");

        // name like "%J"
//        wrapper.likeLeft("name","J");

        // name like 'J%'
//        wrapper.likeRight("name","J");

        // name is null
//        wrapper.isNull("name");

        // name is not null
//        wrapper.isNotNull("name");

        // name in ('Jack','Tom','Jone')
//        wrapper.in("name","Jack","Tom","Jone");

        // name not in ('Jack','Tom','Jone')
//        wrapper.notIn("name","Jack","Tom","Jone");

    }


    /**
     * 子查询
     * name in (select name from user where age > 21)
     * name not in (select name from user where age > 21)
     */
    @Test
    public void test03(){
        QueryWrapper<Person> wrapper = new QueryWrapper<>();
        wrapper.inSql("name","select name from person where age > 21");

//        wrapper.notInSql("name","select name from person where age > 21");
        List<Person> list = personMapper.selectList(wrapper);
        list.forEach(System.out::println);
    }


    /**
     * 分组
     */
    @Test
    public void test04(){
        QueryWrapper<Person> wrapper = Wrappers.query();

        wrapper.groupBy("sex");
        List<Person> list = personMapper.selectList(wrapper);
        list.forEach(System.out::println);
    }

    /**
     * having操作
     */
    @Test
    public void test05() {

        //  创建wrapper对象
        QueryWrapper<Person> wrapper = Wrappers.query();

        // group by sex having sex = 0
        wrapper.groupBy("sex");
        wrapper.having("sex", "0");

        List<Person> users = personMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    /**
     * 排序
     */
    @Test
    public void test06() {
        //  创建wrapper对象
        QueryWrapper<Person> wrapper = Wrappers.query();

        /**
         * 参数1: 是否是Asc排序(升序), true : asc排序, false: desc排序
         * 参数2: 排序的字段
         */
        wrapper.orderByAsc("age");		// order by age asc

//        wrapper.orderByDesc("age");		// order by age desc

        List<Person> users = personMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    /**
     * and拼接条件
     */
    @Test
    public void test07(){
        QueryWrapper<Person> wrapper = Wrappers.query();
        wrapper.like("name","%i%")
                .lt("age",20)
                .eq("sex",0);

        List<Person> users = personMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }


    /**
     * or拼接条件
     */
    @Test
    public void test08(){
        QueryWrapper<Person> wrapper = Wrappers.query();

        wrapper.like("name","%i%")
                .or()
                .lt("age",20)
                .eq("sex",0);

        List<Person> users = personMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }


    /**
     * and方法
     * 用于拼接一个其他的整体条件
     * 生成的SQL为: age < ? and (sex = ? or name like ?)
     */
    @Test
    public void test09(){
        QueryWrapper<Person> wrapper = Wrappers.query();
        wrapper.lt("age",20);
        wrapper.and(new Consumer<QueryWrapper<Person>>() {
            @Override
            public void accept(QueryWrapper<Person> personQueryWrapper) {
                personQueryWrapper.eq("sex",0)
                        .or()
                        .like("name","%i%");
            }
        });

        List<Person> users = personMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }


    /**
     * func方法: 用于多条件的拼接
     * 生成的SQL语句: age < ? and name like ? and sex = ?
     */
    @Test
    public void test10(){
        QueryWrapper<Person> wrapper = Wrappers.query();
        wrapper.lt("age",21);
        wrapper.func(new Consumer<QueryWrapper<Person>>() {
            @Override
            public void accept(QueryWrapper<Person> personQueryWrapper) {
                personQueryWrapper.like("name","%i");
                personQueryWrapper.eq("sex",0);
            }
        });

        List<Person> users = personMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    /**
     * nested方法
     * 等价于and方法
     * 生成的SQL语句: id = ? and (name like ? or age > ?)
     */
    @Test
    public void test11(){
        QueryWrapper<Person> wrapper = Wrappers.query();
        // nested()等价于and方法()
        wrapper.eq("id",1);
        wrapper.nested(new Consumer<QueryWrapper<Person>>() {
            @Override
            public void accept(QueryWrapper<Person> personQueryWrapper) {
                personQueryWrapper.like("name","%i")
                        .or()
                        .gt("age",20);

            }
        });

        List<Person> users = personMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }


    /**
     * apply方法
     * 可以使用占位符进行参数传参
     * sql: name like ? and age > ? AND date_format(birthday, '%Y-%m-%d') = ?
     */
    @Test
    public void test12() {
        QueryWrapper<Person> wrapper = Wrappers.query();

        // SQL: (name like ? and age > ?)
        wrapper.apply("name like {0} and age > {1}", "%J%", 18);

        // SQL: (date_format(birthday, '%Y-%m-%d') = ?)
        wrapper.apply("date_format(birthday, '%Y-%m-%d') = {0}", "2001-10-04");

        List<Person> users = personMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }


    /**
     * last方法
     * 无视优化规则直接拼接到 sql 的最后,只能调用一次,多次调用以最后一次为准 有sql注入的风险
     * apply方法可以防止SQL注入，但last方法不能
     */
    @Test
    public void test13() {
        QueryWrapper<Person> wrapper = Wrappers.query();

        // 无视优化规则直接拼接到 sql 的最后,只能调用一次,多次调用以最后一次为准 有sql注入的风险

        // SQL: name = 'Jone'
//        String name = "Jone";
//        wrapper.last("where name = '" + name + "'");

        // SQL: SELECT id,name,sex,age FROM user where name ='' or 1=1; -- '
        String name = "' or 1=1; -- ";
        wrapper.last("where name ='" + name + "'");         // 出现SQL注入问题

        List<Person> users = personMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }


    /**
     * exists方法
     * 用于exist语句
     */
    @Test
    public void test14() {
        QueryWrapper<Person> wrapper = Wrappers.query();

        // SQL: (EXISTS (select 1))
        wrapper.exists("select 1");

        List<Person> users = personMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    /**
     * notExists方法
     */
    @Test
    public void test15() {
        QueryWrapper<Person> wrapper = Wrappers.query();

        // SQL: (NOT EXISTS (select 1))
        wrapper.notExists("select 1");

        List<Person> users = personMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }




}
