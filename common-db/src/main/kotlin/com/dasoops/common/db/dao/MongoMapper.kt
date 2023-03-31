package com.dasoops.common.db.dao

import cn.hutool.core.lang.func.Func1
import cn.hutool.core.lang.func.LambdaUtil
import com.dasoops.common.db.entity.dbo.base.BaseMongoDo
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

/**
 * mongoMapper抽象实现类
 * @title: AbstractMongoMapperImpl
 * @classPath com.herdsman.wgts.dao.AbstractMongoMapperImpl
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/27
 * @version 1.0.0
 * @see [MongoMapper]
 */
abstract class MongoMapper<T : BaseMongoDo>(
    open val entityClass: Class<T>
) : IMongoMapper<T> {

    @Autowired
    protected lateinit var mongoTemplate: MongoTemplate

    fun list(): List<T> {
        return mongoTemplate.findAll(entityClass)
    }

    fun saveBatchTo(doList: Collection<T>, collectionName: String) {
        mongoTemplate.insert(doList, collectionName)
    }

    fun saveBatch(doList: Collection<T>) {
        mongoTemplate.insertAll(doList)
    }

    fun saveBatch(vararg doList: T) {
        this.saveBatch(doList.toList())
    }

    fun removeById(id: ObjectId) {
        mongoTemplate.remove(Query.query(Criteria.where(field(BaseMongoDo::rowId)).`is`(id)))
    }

    fun removeBatchById(idList: Collection<ObjectId>) {
        mongoTemplate.remove(Query.query(Criteria.where(field(BaseMongoDo::rowId)).`in`(idList)))
    }

    fun field(func: Func1<T, *>): String {
        return LambdaUtil.getFieldName(func)
    }
}
