
# 参考文章

[MybatisPlus详细讲解（适合新手和复习）](https://blog.csdn.net/qq_62325622/article/details/134562480)

[SpringBoot3 + MyBatisPlus 快速整合](https://blog.csdn.net/qq_34709175/article/details/142982502)

[超详细超全面的MyBatisPlus的所有使用方法](https://blog.csdn.net/Bb15070047748/article/details/129212543)


# 测试样例

/src/test/java/com/example/mybatispluslearning/test/MybatisPlusApplicationTests


# maven依赖

```xml
<!--   mybatis-plus 相关依赖   -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-spring-boot3-starter</artifactId>
            <version>3.5.7</version>
        </dependency>
        <!-- 代码生成器 -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-generator</artifactId>
            <version>3.5.7</version>
        </dependency>
        <!--  mysql 依赖 版本由继承的 spring-boot-starter-parent 控制，也可以自己指定  -->
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
        </dependency>

        <!-- 模板引擎 -->
        <dependency>
            <groupId>org.freemarker</groupId>
            <artifactId>freemarker</artifactId>
        </dependency>

        <!--lombok-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
```

## 配置数据库连接

```yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/votedb?autoReconnect=true&useUnicode=true&characterEncoding=utf8&useSSL=false&rewriteBatchedStatements=true
    username: root
    password: 8888.216
```

## 要进行mapper的扫描在启动类上加注解

```java
@MapperScan("com.example.mybatispluslearning.mapper")
```

```java
package com.example.mybatispluslearning;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.mybatispluslearning.mapper")
@SpringBootApplication
public class MybatisPlusLearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusLearningApplication.class, args);
    }

}

```

## 数据库

创建数据库votedb,然后创建下表

```sql
create table user
(
    id          bigint auto_increment comment '主键'
        primary key,
    user_name   varchar(255)  null comment '用户名',
    password    varchar(255)  null comment '密码',
    phone       varchar(255)  null comment '手机',
    email       varchar(255)  null comment '邮箱',
    age         int           null comment '年龄',
    role        varchar(2)    null comment '角色',
    create_time datetime      null comment '创建时间',
    update_time datetime      null comment '修改时间',
    version     int default 1 null comment '乐观锁',
    deleted     int default 1 null comment '逻辑删除'
)
    charset = utf8mb3;
 
create table vote_theme
(
    id                bigint           not null comment '主题主键'
        primary key,
    theme_name        varchar(255)     null comment '主题名',
    theme_explain     text             null comment '相关说明',
    expectation_sum   bigint default 0 null comment '总期望值',
    expectation_times bigint default 0 null comment '期望人次',
    imgurl            varchar(2550)    null comment '主题图片',
    end_date          datetime         null,
    start_date        datetime         null,
    gmt_create        datetime         null comment '创建时间',
    gmt_modified      datetime         null comment '修改时间'
)
    charset = utf8mb3;
 

```

```sql
INSERT INTO votedb.user (id, user_name, password, phone, email, age, role, create_time, update_time, version, deleted) VALUES (1358687078200619010, 'wzp', '674f6e6d9b25a084110e992042ad0cdb', '17753657132', '1231@163.com', 22, 'a', '2022-11-22 20:53:35', '2023-11-22 20:53:35', 1, 0);
INSERT INTO votedb.user (id, user_name, password, phone, email, age, role, create_time, update_time, version, deleted) VALUES (1358687341250588673, 'admin', '674f6e6d9b25a084110e992042ad0cdb', '17753657132', '124@163.com', 28, 'a', '2022-11-22 20:53:40', '2023-11-22 20:53:40', 1, 0);
INSERT INTO votedb.user (id, user_name, password, phone, email, age, role, create_time, update_time, version, deleted) VALUES (1358687744079933441, 'wahahaa', '674f6e6d9b25a084110e992042ad0cdb', '18553777133', '123@126.com', 38, 'u', '2022-07-22 20:53:35', '2023-07-22 20:53:35', 1, 0);
INSERT INTO votedb.user (id, user_name, password, phone, email, age, role, create_time, update_time, version, deleted) VALUES (1358693663190769666, 'hanjia', '674f6e6d9b25a084110e992042ad0cdb', '18577657134', '129@163.com', 18, 'u', '2022-05-22 20:53:35', '2023-05-22 20:53:35', 1, 0);
INSERT INTO votedb.user (id, user_name, password, phone, email, age, role, create_time, update_time, version, deleted) VALUES (1362299967155175426, 'wanghia', '123', '123132', '2222', 18, 'u', '2022-02-22 20:53:35', '2023-02-22 20:53:35', 1, 0);
INSERT INTO votedb.user (id, user_name, password, phone, email, age, role, create_time, update_time, version, deleted) VALUES (1362959351606640641, 'zhangsan', '674f6e6d9b25a084110e992042ad0cdb', '18453657775', '2222@qq.com', 40, 'a', '2022-08-22 20:53:35', '2023-08-22 20:53:35', 1, 0);
INSERT INTO votedb.user (id, user_name, password, phone, email, age, role, create_time, update_time, version, deleted) VALUES (1380808280388952066, '123123123', '674f6e6d9b25a084110e992042ad0cdb', '18553657776', '24423@qq.com', 18, 'u', '2022-01-22 20:53:35', '2023-01-22 20:53:35', 1, 0);

```

## 具体的sql语句

如果你想要查看到具体的sql语句的话你可以使用yml配置开启日志

yml配置文件配置日志

```yml
#配置日志
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

## 主键策略

```java
/**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
```


### 1.AUTO(0):

- 策略：自动增长。这意味着主键的值会自动递增，通常适用于整数型的主键。
- 使用场景：当数据库中的主键设置为自增长时，可以使用此策略。


### 2.NONE(1):

- 策略：没有主键。这表示实体类没有主键值，或者说不使用主键。
- 使用场景：在某些情况下，可能不需要主键，或者在其他字段中定义复合主键时可以使用。


### 3.INPUT(2):

- 策略：手动输入。这意味着在插入数据时需要手动指定主键的值。
- 使用场景：适用于非自增、非UUID的主键类型，例如字符串或其他数据类型的主键。


### 4.ASSIGN_ID(3):

- 策略：分配ID。这通常是由程序分配的一个长整型ID。
- 使用场景：当需要使用程序中自定义的ID生成策略时，可以使用此选项。


### 5.ASSIGN_UUID(4):

- 策略：分配UUID。这意味着主键的值将是一个生成的UUID。
- 使用场景：当主键需要是UUID类型时，可以使用此策略。UUID可以确保全局唯一性，适用于分布式系统等场景。

## 自动填充

我们现在设置user字段多添加两个字段一个是创建时间、一个是修改时间

对应在实体类User.java中也要加入字段

```java
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)   //使用的是插入策略
    private Date createTime;
 
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
```

MetaObjectHandler我们需要自定义一个Handler实现此接口从而实现操作填充策略

```java
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    //插入时候的策略
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.setFieldValByName("createTime",new Date(),metaObject);
    }
    //修改时候的策略
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}

```


## 乐观锁和悲观锁

### 什么是乐观锁

乐观锁的操作理念是“乐观”，它假设并发操作中数据冲突的可能性较小，因此在进行数据操作时不会直接锁定数据。只是在执行更新操作时，会判断在此期间是否有其他操作修改了数据。如果有其他操作修改了数据，那么乐观锁会放弃操作，否则执行操作。

### 什么是悲观锁
悲观锁的操作理念则相对“悲观”，它假设并发操作中数据冲突的可能性较大，因此在进行数据操作时会直接锁定数据，直到操作完成后才会释放锁。在锁定期间，其他操作不能修改数据，只能等待锁被释放。

### 配置乐观锁

本文主要讲解乐观锁机制

乐观锁实现方式：

1. 取出记录时，获取当前version
2. 更新时，带上这个version
3. 执行更新时，set version = newVersion where version = oldVersion
4. 如果version不对，就更新失败

第一步：在数据库中user表添加一个version字段

第二步：在实体类中添加version并且在version字段上加@Version注解

```java
@Version //乐观锁注解
    private int version;
```

第三步：编写mybatis-plus的配置类

```java
package com.ltx.mybatis_plus.config;
 
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
@Configuration
@MapperScan("com.ltx.mybatis_plus.mapper")
public class MybatisPlusConfig {
    /**
     * 新版
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return mybatisPlusInterceptor;
    }
}

```

测试:

```java
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

```

## 分页查询

首先要使用分页查询就要先去配置分页查询的插件，所以我们需要在mybatisplus的配置类中先注册

```java
/**
     * 添加分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));//如果配置多个插件,切记分页最后添加
        //interceptor.addInnerInterceptor(new PaginationInnerInterceptor()); 如果有多数据源可以不配具体类型 否则都建议配上具体的DbType
        return interceptor;
    }

```

```java
    <P extends IPage<T>> P selectPage(P page, @Param("ew") Wrapper<T> queryWrapper);
```

首先我们需要从BaseMapper里面找到关于分页查询的方法，在此方法中我们看见了一个关于P继承了IPage的操作，我们需要查看这个IPage里面封装的是一些什么数据

```java
当前页：+ page.getCurrent()
总页数：+ page.getPages()
记录数：+ page.getTotal()
是否有上一页： page.hasPrevious()
是否有下一页： page.hasNext()
```

对于第二个参数Wrapper<T> queryWrapper可以直接写null

```java
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
```

通过查看结果我们可以知道其实就是调用了一个分页查询，只是在内部封装帮你计算好是从那一个元素开始


## 逻辑删除

```java
物理删除：从数据库中直接删除

逻辑删除：在数据库中没有被删除，而是通过一个变量来让它失效。 deleted=0 --->deleted=1
```

1、在数据表中增加一个deleted字段

2、同步实体类，在实体类上加上@TableLogic 注解

```java
@TableLogic //逻辑删除
private Integer deleted;
```

3、配置application.yml文件

```yml
mybatis-plus:
  global-config:
    db-config:
      logic-delete-field: deleted # 全局逻辑删除的实体字段名(配置后可以忽略不配置 @TableLogic)
      logic-delete-value: 1 # 逻辑已删除值(默认为 1)
      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)
```

4、测试

```java
 /**
     * 逻辑删除
     * 查看日志输出可以看到，delete的语句变成了update语句，实质上就是update（修改）语句，将deleted字段从0修改为1以yml的配置作为参照
     */
    @Test
    void logicDeleteTest03() {
        userMapper.deleteById(1380808280388952066L);
    }
```

查看日志输出可以看到，delete的语句变成了update语句，实质上就是update（修改）语句，将deleted字段从0修改为1以yml的配置作为参照

5、对刚刚逻辑删除的id字段用户进行查询

```java
    @Test
    void selectTest01() {
        User user = userMapper.selectById(1380808280388952066L);
        System.out.println(user);
    }
```

查看日志输出可以看到，seletc的语句以经发生了更改

增加了deleted的判断语句，判断deleted是否为0，为0则能搜索，1则不能。

## 执行sql分析打印

`p6spy依赖引入`

p6spy 组件的引入可以完美的输出打印 SQL 及执行时长用来解决新版的日志输出没有执行时间和完整的sql语句。

### maven配置

```xml
<!-- https://mvnrepository.com/artifact/p6spy/p6spy -->
        <dependency>
            <groupId>p6spy</groupId>
            <artifactId>p6spy</artifactId>
            <version>3.9.1</version>
        </dependency>
```

### 配置

```yml
#mysql配置
spring:
  datasource:
    password: 8888.216
    username: root
    driver-class-name: com.p6spy.engine.spy.P6SpyDriver
    url: jdbc:p6spy:mysql:///votedb?userUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
```

### spy.properties 配置

```properties
#3.2.1以上使用
modulelist=com.baomidou.mybatisplus.extension.p6spy.MybatisPlusLogFactory,com.p6spy.engine.outage.P6OutageFactory
#3.2.1以下使用或者不配置
#modulelist=com.p6spy.engine.logging.P6LogFactory,com.p6spy.engine.outage.P6OutageFactory
# 自定义日志打印
logMessageFormat=com.baomidou.mybatisplus.extension.p6spy.P6SpyLogger
#日志输出到控制台
appender=com.baomidou.mybatisplus.extension.p6spy.StdoutLogger
# 使用日志系统记录 sql
#appender=com.p6spy.engine.spy.appender.Slf4JLogger
# 设置 p6spy driver 代理
deregisterdrivers=true
# 取消JDBC URL前缀
useprefix=true
# 配置记录 Log 例外,可去掉的结果集有error,info,batch,debug,statement,commit,rollback,result,resultset.
excludecategories=info,debug,result,commit,resultset
# 日期格式
dateformat=yyyy-MM-dd HH:mm:ss
# 实际驱动可多个
#driverlist=org.h2.Driver
# 是否开启慢SQL记录
outagedetection=true
# 慢SQL记录标准 2 秒
outagedetectioninterval=2

```


## 在Mybatis-plus中是使用xml文件

实际上在Mybatis-plus中使用xml文件也和在mybatis中使用xml文件一样的

```yml
mybatis-plus:
  # 配置mapper的扫描，找到所有的mapper.xml映射文件 如果和默认路径一样可以不配置
  mapper-locations: classpath*:/mapper/**/*.xml
```

在 MyBatis-Plus 配置文件中的 `mapper-locations: classpath*:/mapper/**/*.xml` 配置项指定了 MyBatis 映射文件（Mapper XML 文件）的位置。

### 解释：
- **`mapper-locations`**：这个配置项用于指定 MyBatis 映射文件的路径，映射文件通常是用于存放 SQL 映射的 XML 文件，通常与 MyBatis 的 `Mapper` 接口进行映射。
- **`classpath*:/mapper/**/*.xml`**：这个路径是一个类路径匹配模式，表示：
    - `classpath*:`：从类路径中加载资源。`classpath*:` 会匹配所有类路径下的 `mapper` 文件夹（包括 JAR 包中的资源）。
    - `/mapper/`：指示在类路径中查找 `mapper` 文件夹（这个文件夹通常包含你的 Mapper XML 配置文件）。
    - `/**/*.xml`：表示递归匹配 `mapper` 文件夹下及其子文件夹中的所有 `.xml` 文件。

### 具体位置：
1. **`mapper` 文件夹**：你的项目中应该有一个名为 `mapper` 的文件夹，存放所有的 MyBatis 映射文件（`*.xml` 文件）。这个文件夹的位置应该是项目的 **资源文件夹** 下的一个目录（通常是 `src/main/resources`）。
    - 例如：`src/main/resources/mapper`，这个路径下存放所有的 Mapper XML 文件。

2. **类路径匹配**：配置中使用的 `classpath*:` 表示无论这些 XML 文件是直接在项目的资源文件夹中，还是在打包后的 JAR 包中，都可以找到并加载它们。

### 示例项目结构：
假设你的项目结构如下：
```
src
├── main
│   ├── java
│   ├── resources
│   │   ├── mapper
│   │   │   ├── UserMapper.xml
│   │   │   ├── ProductMapper.xml
```

在这种情况下，`mapper-locations: classpath*:/mapper/**/*.xml` 会加载 `src/main/resources/mapper` 目录下的所有 XML 文件。

### 总结：
`mapper-locations: classpath*:/mapper/**/*.xml` 说明：
- 在类路径下查找所有位于 `mapper` 目录及其子目录下的 XML 文件。
- 这些文件通常是 MyBatis 映射文件，包含 SQL 语句和映射规则。
- 通常它们会位于 `src/main/resources/mapper` 目录，或打包后的 JAR 文件中的 `mapper` 目录下。



------------------------

`classpath` 和 `classpath*` 都是用来指定类路径（ClassPath）资源的位置，但是它们之间有一个重要的区别，特别是在资源加载方面。

### 1. **`classpath:`**

- **定义**：`classpath:` 用于从类路径中加载单个资源文件或目录。
- **作用**：它仅从 **当前的类路径** 中查找指定的资源（通常指的是应用程序或 JAR 包中的类路径）。当你使用 `classpath:` 时，它只会匹配当前类路径下的资源，而不会递归地搜索 JAR 包中的资源。

  例如：
  ```yaml
  mapper-locations: classpath:/mapper/**/*.xml
  ```
  这表示只会在 **当前类路径下** 查找 `mapper` 文件夹及其子目录中的所有 `.xml` 文件。假设在 `src/main/resources/mapper` 文件夹下有一些 `.xml` 文件，它们会被加载。

- **使用场景**：适用于你知道资源文件或目录确切位置，并且只需要从本地文件系统或者当前的类路径中加载资源时。

### 2. **`classpath*:`**

- **定义**：`classpath*:` 是一个增强的路径匹配符，它会 **从类路径和所有 JAR 包中** 查找资源。
- **作用**：与 `classpath:` 的不同之处在于，`classpath*:` 会 **递归地** 查找所有 JAR 文件和类路径下的资源。如果资源在 JAR 包内部的某个路径中，也会被加载。

  例如：
  ```yaml
  mapper-locations: classpath*:/mapper/**/*.xml
  ```
  这表示会查找类路径中所有地方的 `mapper` 文件夹及其子目录中的 `.xml` 文件。不仅仅是应用程序的文件系统中的 `mapper` 目录，还会搜索所有依赖的 JAR 包中的 `mapper` 目录。

- **使用场景**：适用于当你的资源文件可能存在于多个 JAR 包中，或者你希望能够加载项目依赖中的资源时。通常在多模块或包含多个 JAR 包的项目中，`classpath*:` 非常有用。

### 主要区别：

| **特性**           | **`classpath:`**                               | **`classpath*:`**                                 |
|--------------------|-----------------------------------------------|-------------------------------------------------|
| **资源查找范围**   | 仅限于当前类路径（包括本地文件和 JAR 包中的类路径） | 当前类路径 + 所有 JAR 包中的类路径             |
| **递归搜索**       | 不会递归搜索 JAR 包中的资源                  | 会递归搜索所有 JAR 包中的资源                   |
| **应用场景**       | 当资源仅位于类路径中时使用                    | 当资源可能位于类路径或依赖的 JAR 包中时使用     |

### 示例：

假设你有以下项目结构：

```
/my-app
  /src
    /main
      /resources
        /mapper
          UserMapper.xml
  /lib
    /dependency.jar
      /mapper
        ProductMapper.xml
```

- **`classpath:/mapper/**/*.xml`**：只会加载 `src/main/resources/mapper/UserMapper.xml`。
- **`classpath*:/mapper/**/*.xml`**：会加载 `src/main/resources/mapper/UserMapper.xml` 以及 `lib/dependency.jar` 内的 `mapper/ProductMapper.xml`，因为它会搜索所有 JAR 包和类路径中的 `mapper` 目录。

### 总结：
- `classpath:` 只会从当前类路径加载资源。
- `classpath*:` 会从当前类路径和所有 JAR 包中递归加载资源。



-----------------------


首先我们需要创建mapper.xml文件与mapper文件进行绑定


### UserMapper01.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.mybatispluslearning.mapper.UserMapper01">  <!--不同的mapper文件需要改的地方就是这里-->
    <select id="getUserName" parameterType="Long" resultType="String">
        select user_name from user where id = #{id}
    </select>

</mapper>
```

### 我们现在UserMapper01中创建函数

```java
package com.example.mybatispluslearning.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatispluslearning.entity.User;

public interface UserMapper01 extends BaseMapper<User> {
    String getUserName(Long id);
}

```

### 在测试类中测试编写的sql语句是否可用

```java
/**
     * 测试xml文件
     */
    @Test
    void xmlTest() {
        System.out.println(userMapper01.getUserName(1380808280388952068L));
    }
```


------------------------------------

# MybatisPlus的使用

## 创建测试表

```sql
DROP TABLE IF EXISTS person;

CREATE TABLE `person` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `sex` char(1) DEFAULT NULL COMMENT '性别 0:男 1:女',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `birthday` date DEFAULT NULL COMMENT '生日',
  PRIMARY KEY (`id`)
);


INSERT INTO `person` VALUES (1, 'Jone', '1', 27, '2001-10-04');
INSERT INTO `person` VALUES (2, 'Jack', '0', 20, '1999-10-04');
INSERT INTO `person` VALUES (3, 'Tom', '1', 28, '1996-08-12');
INSERT INTO `person` VALUES (4, 'Sandy', '1', 21, '2001-10-04');
INSERT INTO `person` VALUES (5, 'Billie', '0', 24, '1992-09-07');
INSERT INTO `person` VALUES (6, 'Jackson', '0', 18, '1996-08-12');
INSERT INTO `person` VALUES (7, 'Hardy', '1', 25, '1992-09-07');
INSERT INTO `person` VALUES (8, 'Rose', '1', 21, '1992-09-07');
INSERT INTO `person` VALUES (9, 'June', '0', 28, '1992-09-07');
INSERT INTO `person` VALUES (10, 'Aidan', '0', 17, '2001-10-04');

```

## BaseMapper的基本CRUD

在MyBatis Plus中，我们编写的Mapper接口都继承与MyBatis Plus提供的BaseMapper接口，BaseMapper接口包含了MyBatis Plus帮我们提供操作数据库的一系列方法；


### 示例

```java
package com.example.mybatispluslearning.test02;

import com.example.mybatispluslearning.entity.Person;
import com.example.mybatispluslearning.mapper.PersonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class Demo01 {

    @Autowired
    private PersonMapper personMapper;

    /**
     * 查询所有
     */
    @Test
    public void selectTest(){
        // 传递null代表查询全部
        List<Person> personList = personMapper.selectList(null);
        for (Person user : personList) {
            System.out.println(user);
        }
    }

    /**
     * 插入
     */
    @Test
    public void insertTest(){
        Person person = new Person(100L,"Fire","0",20);
        personMapper.insert(person);
    }

    /**
     * 修改
     */
    @Test
    public void updateTest(){
        Person person = new Person(100L,"Fire","0",25);
        personMapper.updateById(person);
    }

    /**
     * 根据id查询
     */
    @Test
    public void selectById(){
        Person person = personMapper.selectById(100L);
        System.out.println(person);
    }


    /**
     * 根据一批id查询
     */
    @Test
    public void selectBatchIds(){
        List<Person> personList = personMapper.selectBatchIds(Arrays.asList(1,2,3));
        for (Person user : personList) {
            System.out.println(user);
        }
    }

    /**
     * 条件查询
     */
    @Test
    public void selectByMap(){
        // 封装条件
        HashMap<String, Object> param = new HashMap<>();
        param.put("id",100L);
        param.put("name","Fire");

        List<Person> personList = personMapper.selectByMap(param);
        for (Person user : personList) {
            System.out.println(user);
        }
    }

    /**
     * 根据id删除
     */
    @Test
    public void deleteById(){
        personMapper.deleteById(100L);
    }

    /**
     * 根据id删除一批
     */
    @Test
    public void deleteBatchIds(){
        personMapper.deleteBatchIds(Arrays.asList(1,2,3));
    }


    /**
     * 根据条件删除数据
     */
    @Test
    public void deleteByMap(){
        // 封装条件
        HashMap<String, Object> param = new HashMap<>();
        param.put("id",100L);
        param.put("name","Fire");

        personMapper.deleteByMap(param);


    }









}


```


## Wrapper查询









