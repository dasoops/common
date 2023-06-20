package com.dasoops.common.db

import com.baomidou.mybatisplus.annotation.DbType
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer
import com.baomidou.mybatisplus.core.MybatisConfiguration
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor
import org.apache.ibatis.reflection.MetaObject
import org.springframework.context.annotation.Bean
import java.util.*
import javax.print.attribute.IntegerSyntax

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
abstract class BaseMybatisPlusConfiguration(private val dbType: DbType) : MetaObjectHandler {
    @Bean
    open fun mybatisPlusInterceptor(): MybatisPlusInterceptor {
        val interceptor = MybatisPlusInterceptor()
        //分页拦截器
        interceptor.addInnerInterceptor(PaginationInnerInterceptor(dbType))
        return interceptor
    }

    @Bean
    open fun configurationCustomizer(): ConfigurationCustomizer {
        return ConfigurationCustomizer { configuration: MybatisConfiguration ->
            //是否开启驼峰命名自动映射，即从经典数据库列名 A_COLUMN 映射到经典 Java 属性名 aColumn。 默认为false
            configuration.isMapUnderscoreToCamelCase = true
        }
    }

    /**
     * 获取用户id供自动填充使用
     *
     * @return [long]
     */
    abstract val userId: Long?
    override fun insertFill(metaObject: MetaObject) {
        this.strictInsertFill(metaObject, "isDelete", Integer::class.java, 0 as Integer)
        this.strictInsertFill(metaObject, "createTime", Date::class.java, Date())
        this.strictInsertFill(metaObject, "createUser", Long::class.java, userId)
        updateFill(metaObject)
    }

    override fun updateFill(metaObject: MetaObject) {
        this.strictUpdateFill(metaObject, "updateTime", Date::class.java, Date())
        this.strictUpdateFill(metaObject, "updateUser", Long::class.java, userId)
    }
}
