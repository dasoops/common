package com.dasoops.common.db.dao

import com.baomidou.mybatisplus.core.metadata.IPage
import com.dasoops.common.db.entity.dbo.BaseMongoDo
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

    fun count(): Long {
        return template.count(builder.build(), entityClass)
    }

    fun exists(): Boolean {
        return template.exists(builder.build(), entityClass)
    }

    fun listAndRemove(): List<T>? {
        return template.findAllAndRemove(builder.build(), entityClass).ifEmpty { null }
    }

    fun listAndRemove(collectionName: String): List<T>? {
        return template.findAllAndRemove(builder.build(), entityClass, collectionName).ifEmpty { null }
    }

    fun remove() {
        template.remove(builder.build(), entityClass)
    }

    fun remove(collectionName: String) {
        template.remove(builder.build(), entityClass)
    }

    fun remove(vararg otherCollectionName: String) {
        this.remove()
        otherCollectionName.forEach {
            this.remove(it);
        }
    }

    /**
     * 查询并合并 历史表
     */
    fun listMerge(vararg collectionNameArray: String): List<T>? {
        val otherCollectionRecords = collectionNameArray.mapNotNull {
            this.list(it)
        }.flatten()
        //合并 主表有记录就主表添加副表,不然就只返回副表
        val dataList = this.list()?.apply {
            toMutableList().addAll(otherCollectionRecords)
        } ?: otherCollectionRecords

        return dataList.ifEmpty { null }
    }

    /**
     * @see listMerge
     */
    fun listMerge(useMerge: Boolean, vararg collectionNameArray: String): List<T>? {
        return if (useMerge) {
            this.listMerge(*collectionNameArray)
        } else {
            list()
        }
    }

    /**
     * 分页查询
     * @param [page] 分页查询
     * @return [IPage<T>]
     */
    fun page(page: IPage<T>): IPage<T> {
        builder.skip(page.offset())
        builder.limit(page.size.toInt())
        page.records = this.list()
        return page
    }

}