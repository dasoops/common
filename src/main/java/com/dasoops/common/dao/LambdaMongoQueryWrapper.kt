package com.dasoops.common.dao

import com.dasoops.common.entity.dbo.base.BaseMongoDo
import org.springframework.data.mongodb.core.MongoTemplate
import kotlin.reflect.KProperty1

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

    fun one(): T? {
        return template.findOne(builder.build(), entityClass)
    }

    fun list(): List<T>? {
        return template.find(builder.build(), entityClass)
    }

}