package com.dasoops.common.db

import com.baomidou.mybatisplus.annotation.DbType
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer
import com.baomidou.mybatisplus.core.MybatisConfiguration
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor
import com.dasoops.common.entity.dataenum.BooleanEnum
import org.apache.ibatis.reflection.MetaObject
import org.springframework.context.annotation.Bean
import java.util.*

/**
 * @author DasoopsNicole@Gmail.com
 * @version 1.0.0
 * @title MybatisPlusConfiguration
 * @classPath com.dasoops.dasq.conf.MybatisPlusConfiguration
 * @date 2022/10/07
 * @description mybatis plus 配置
 * @see MetaObjectHandler
 */
abstract class BaseMybatisPlusConfiguration : MetaObjectHandler {
    @Bean
    fun mybatisPlusInterceptor(): MybatisPlusInterceptor {
        val interceptor = MybatisPlusInterceptor()
        //分页拦截器
        interceptor.addInnerInterceptor(PaginationInnerInterceptor(DbType.MYSQL))
        return interceptor
    }

    @Bean
    fun configurationCustomizer(): ConfigurationCustomizer {
        return ConfigurationCustomizer { configuration: MybatisConfiguration ->
            //是否开启驼峰命名自动映射，即从经典数据库列名 A_COLUMN 映射到经典 Java 属性名 aColumn。 默认为false
            configuration.isMapUnderscoreToCamelCase = true
        }
    }

    /**
     * 获取用户id供自动填充使用
     *
     * @return [Long]
     */
    abstract val userId: Long

    override fun insertFill(metaObject: MetaObject) {
        this.strictInsertFill(metaObject, "isDelete", BooleanEnum::class.java, BooleanEnum.FALSE)
        this.strictInsertFill(metaObject, "createTime", Date::class.java, Date())
        this.strictInsertFill(metaObject, "createUser", Long::class.java, userId)
        updateFill(metaObject)
    }

    override fun updateFill(metaObject: MetaObject) {
        this.strictUpdateFill(metaObject, "updateTime", Date::class.java, Date())
        this.strictUpdateFill(metaObject, "updateUser", Long::class.java, userId)
    }
}