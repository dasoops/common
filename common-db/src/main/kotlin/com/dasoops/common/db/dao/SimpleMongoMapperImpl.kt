package com.dasoops.common.db.dao

import com.dasoops.common.db.entity.dbo.base.BaseMongoDo

/**
 * 简单mongoDb数据访问层实现
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/30
 * @see [SimpleMongoMapperImpl]
 */
abstract class SimpleMongoMapperImpl<T : BaseMongoDo>(
    override val entityClass: Class<T>
) : MongoMapper<T>(entityClass) {

    fun lambda(): LambdaMongoQueryWrapper<T> {
        return LambdaMongoQueryWrapper(mongoTemplate, entityClass)
    }
}