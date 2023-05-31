package com.dasoops.common.db;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.dasoops.common.json.core.dataenum.BooleanEnum;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;

import java.util.Date;

/**
 * mybatisPlus配置
 * 该类仅能java编写,使用kotlin会导致mp自动填充方法equal方法判断错误
 * 即java.base.long::class.java != java.lang.Long::class.java
 * 暂未找到其他解决原因
 *
 * @author DasoopsNicole@Gmail.com
 * @date 2022/10/07
 * @see MetaObjectHandler
 */
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
    public abstract Long getUserId();

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "isDelete", BooleanEnum.class, BooleanEnum.FALSE);
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
