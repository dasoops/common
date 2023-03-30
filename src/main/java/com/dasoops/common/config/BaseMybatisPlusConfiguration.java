package com.dasoops.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.dasoops.common.entity.enums.database.DbBooleanEnum;
import lombok.Setter;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;

import java.util.Date;

/**
 * @author DasoopsNicole@Gmail.com
 * @version 1.0.0
 * @title MybatisPlusConfiguration
 * @classPath com.dasoops.dasq.conf.MybatisPlusConfiguration
 * @date 2022/10/07
 * @description mybatis plus 配置
 * @see MetaObjectHandler
 */

@Setter
public abstract class BaseMybatisPlusConfiguration implements MetaObjectHandler {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //分页拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> {
            //是否开启驼峰命名自动映射，即从经典数据库列名 A_COLUMN 映射到经典 Java 属性名 aColumn。 默认为false
            configuration.setMapUnderscoreToCamelCase(true);
        };
    }

    /**
     * 获取用户id供自动填充使用
     *
     * @return {@link long}
     */
    public abstract long getUserId();

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "isDelete", DbBooleanEnum.class, DbBooleanEnum.FALSE);
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "createUser", Long.class, getUserId());

        updateFill(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        this.strictUpdateFill(metaObject, "updateUser", Long.class, getUserId());
    }
}
