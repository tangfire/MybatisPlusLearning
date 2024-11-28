package com.example.mybatispluslearning.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * 配置了 MyBatis-Plus 的拦截器，使用了 MybatisPlusInterceptor，并向其中添加了 OptimisticLockerInnerInterceptor 这个内置拦截器。
 * 启用了 MyBatis-Plus 的乐观锁功能，以便在更新数据时防止并发冲突。
 * 通过 @MapperScan 注解扫描指定的包（com.ltx.mybatis_plus.mapper）中的 Mapper 接口，自动注册到 Spring 容器中。
 *
 * 乐观锁通常依赖于数据库中的版本字段（如 version）。
 * 每次更新数据时，OptimisticLockerInnerInterceptor 会检查这个字段的值。如果在操作期间，数据的版本没有变化，更新就会成功；否则，操作会失败，从而避免覆盖其他人更新的内容。
 *
 * 这个配置类的目的是确保 MyBatis-Plus 可以正确地支持乐观锁，同时保持项目的可维护性和高效性。
 */
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