package com.dasoops.common.dao

import com.dasoops.common.entity.dbo.base.BaseMongoDo
import org.springframework.data.mongodb.core.MongoTemplate

/**
 * mongo lambda查询构建器
 * @title: LambdaMongoQueryWrapper
 * @classPath com.dasoops.common.dao.LambdaMongoQueryWrapper
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/27
 * @version 1.0.0
 * @see [LambdaMongoQueryWrapper]
 */
open class LambdaMongoQueryWrapper<T : BaseMongoDo>(
    private val template: MongoTemplate,
    private val entityClass: Class<T>
) : BaseLambdaWrapper<T, LambdaMongoQueryWrapper<T>>() {

    /**
     * orderBy 升序
     * @param [entityClass] 实体类
     * @param [func] 降序字段
     */
    fun <S> orderBy(entityClass: Class<T>, func: java.util.function.Function<T, S>): LambdaMongoQueryWrapper<T> {
        builder.orderBy(entityClass, func)
        return typethis
    }

    /**
     * orderBy 降序
     * @param [entityClass] 实体类
     * @param [func] 升序字段
     */
    fun <S> orderByDesc(entityClass: Class<T>, func: java.util.function.Function<T, S>): LambdaMongoQueryWrapper<T> {
        builder.orderByDesc(entityClass, func)
        return typethis
    }

    fun one(): T? {
        return template.findOne(builder.build(), entityClass)
    }

    fun list(): List<T>? {
        return template.find(builder.build(), entityClass)
    }

}