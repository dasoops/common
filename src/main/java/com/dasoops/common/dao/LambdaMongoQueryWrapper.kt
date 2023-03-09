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

    fun one(): T? {
        return template.findOne(builder.build(), entityClass)
    }

    fun one(collectionName: String): T? {
        return template.findOne(builder.build(), entityClass, collectionName)
    }

    fun list(): List<T>? {
        return template.find(builder.build(), entityClass).ifEmpty { null }
    }

    fun list(collectionName: String): List<T>? {
        return template.find(builder.build(), entityClass, collectionName).ifEmpty { null }
    }

    /**
     * 查询并合并 历史表
     */
    fun listMerge(vararg collectionNameArray: String): List<T>? {
        val otherCollectionRecords = collectionNameArray.mapNotNull {
            this.list(it)
        }.flatten()
        //合并 主表有记录就主表添加副表,不然就只返回副表
        return this.list()?.apply {
            toMutableList().addAll(otherCollectionRecords)
        } ?: otherCollectionRecords
    }

}