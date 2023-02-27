package com.dasoops.common.dao

import com.dasoops.common.entity.dbo.base.BaseMongoDo
import org.springframework.data.mongodb.core.MongoTemplate

/**
 * mongo 修改构建器 lambda
 * @title: LambdaMongoQueryWrapper
 * @classPath com.dasoops.common.dao.LambdaMongoQueryWrapper
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/27
 * @version 1.0.0
 * @see [LambdaMongoUpdateWrapper]
 */
open class LambdaMongoUpdateWrapper<T : BaseMongoDo>(
    private val template: MongoTemplate,
    private val entityClass: Class<T>
) : LambdaMongoQueryWrapper<T>(template, entityClass) {

    fun remove() {
        template.remove(builder.build(), entityClass)
    }

}