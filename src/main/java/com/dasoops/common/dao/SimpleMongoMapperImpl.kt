package com.dasoops.common.dao

import com.dasoops.common.entity.dbo.base.BaseMongoDo

abstract class SimpleMongoMapperImpl<T : BaseMongoDo>(
    override val entityClass: Class<T>
) : MongoMapper<T>(entityClass) {

    fun lambdaQuery(): LambdaMongoQueryWrapper<T> {
        return LambdaMongoQueryWrapper(mongoTemplate, entityClass)
    }
}