package com.dasoops.common.dao

import cn.hutool.core.lang.func.LambdaUtil
import com.dasoops.common.entity.dbo.base.BaseMongoDo
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import javax.annotation.Resource

/**
 * mongoMapper抽象实现类
 * @title: AbstractMongoMapperImpl
 * @classPath com.herdsman.wgts.dao.AbstractMongoMapperImpl
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/27
 * @version 1.0.0
 * @see [BaseMongoMapperImpl]
 */
abstract class BaseMongoMapperImpl<T : BaseMongoDo>(
    val entityClass: Class<T>
) : IMongoMapper<T> {

    @Resource
    protected lateinit var mongoTemplate: MongoTemplate

    fun lambdaQuery(): LambdaMongoQueryWrapper<T> {
        return LambdaMongoQueryWrapper(mongoTemplate, entityClass)
    }

    fun lambdaUpdate(): LambdaMongoUpdateWrapper<T> {
        return LambdaMongoUpdateWrapper(mongoTemplate, entityClass)
    }

    fun saveBatch(doList: Collection<T>) {
        mongoTemplate.insertAll(doList)
    }

    fun saveBatch(vararg doList: T) {
        this.saveBatch(doList.toList())
    }

    fun removeById(id: ObjectId) {
        mongoTemplate.remove(Query.query(Criteria.where(LambdaUtil.getFieldName(BaseMongoDo::rowId)).`is`(id)))
    }

    fun removeBatchById(idList: List<ObjectId>) {
        mongoTemplate.remove(Query.query(Criteria.where(LambdaUtil.getFieldName(BaseMongoDo::rowId)).`in`(idList)))
    }
}
