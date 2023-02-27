package com.dasoops.common.dao

import cn.hutool.core.lang.func.Func1
import cn.hutool.core.lang.func.LambdaUtil
import com.dasoops.common.entity.dbo.base.BaseMongoDo
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

/**
 * mongo查询构建器
 * @title: MongoQueryBuilder
 * @classPath com.dasoops.common.dao.MongoQueryBuilder
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/27
 * @version 1.0.0
 * @see [MongoQueryBuilder]
 */
open class MongoQueryBuilder<T : BaseMongoDo> {

    val query: Query = Query()

    fun <T : BaseMongoDo> eq(func: Func1<T, *>, value: Any) {
        query.addCriteria(Criteria.where(LambdaUtil.getFieldName(func)).`is`(value))
    }

    fun <T : BaseMongoDo> `in`(func: Func1<T, *>, value: List<Any>) {
        query.addCriteria(Criteria.where(LambdaUtil.getFieldName(func)).`in`(value))
    }

    /**
     * orderBy 升序
     * @param [entityClass] 实体类
     * @param [func] 降序字段
     */
    fun <T : BaseMongoDo, S> orderBy(entityClass: Class<T>, func: java.util.function.Function<T, S>) {
        query.with(Sort.sort(entityClass).by(func))
    }

    /**
     * orderBy 降序
     * @param [entityClass] 实体类
     * @param [func] 升序字段
     */
    fun <T : BaseMongoDo, S> orderByDesc(entityClass: Class<T>, func: java.util.function.Function<T, S>) {
        query.with(Sort.sort(entityClass).by(func).descending())
    }

    fun build(): Query {
        return query
    }

}