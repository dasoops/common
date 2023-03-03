package com.dasoops.common.dao

import com.dasoops.common.entity.dbo.base.BaseMongoDo
import com.dasoops.common.util.SqlSelectBuilder
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import kotlin.reflect.KProperty1

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

    fun <R> eq(func: KProperty1<T, R>, value: R) {
        query.addCriteria(Criteria.where(SqlSelectBuilder.build(func)).`is`(value))
    }

    fun eq(column: String, value: Any) {
        query.addCriteria(Criteria.where(column).`is`(value))
    }

    fun <R> `in`(func: KProperty1<T, R>, vararg value: Any) {
        query.addCriteria(Criteria.where(SqlSelectBuilder.build(func)).`in`(value))
    }

    fun `in`(column: String, vararg value: Any) {
        query.addCriteria(Criteria.where(column).`in`(value))
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