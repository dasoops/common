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
open class MongoQueryBuilder {

    val query: Query = Query()

    /**
     * 相等
     */
    fun <R : Any> eq(func: KProperty1<*, R>, value: R) {
        this.eq(SqlSelectBuilder.build(func), value)
    }

    /**
     * 相等
     */
    fun eq(column: String, value: Any) {
        query.addCriteria(Criteria.where(column).`is`(value))
    }

    /**
     * 不相等
     */
    fun ne(column: String, value: Any) {
        query.addCriteria(Criteria.where(column).ne(value))
    }

    /**
     * 不相等
     */
    fun <R : Any> ne(func: KProperty1<*, R>, value: Any) {
        this.eq(SqlSelectBuilder.build(func), value)
    }

    /**
     * 大于
     */
    fun gt(column: String, value: Any) {
        query.addCriteria(Criteria.where(column).gt(value))
    }

    /**
     * 大于
     */
    fun <R : Any> gt(func: KProperty1<*, R>, value: Any) {
        this.eq(SqlSelectBuilder.build(func), value)
    }

    /**
     * 大于等于
     */
    fun ge(column: String, value: Any) {
        query.addCriteria(Criteria.where(column).gte(value))
    }

    /**
     * 大于等于
     */
    fun <R : Any> ge(func: KProperty1<*, R>, value: Any) {
        this.eq(SqlSelectBuilder.build(func), value)
    }

    /**
     * 小于
     */
    fun lt(column: String, value: Any) {
        query.addCriteria(Criteria.where(column).lt(value))
    }

    /**
     * 小于
     */
    fun <R : Any> lt(func: KProperty1<*, R>, value: Any) {
        this.eq(SqlSelectBuilder.build(func), value)
    }

    /**
     * 小于等于
     */
    fun le(column: String, value: Any) {
        query.addCriteria(Criteria.where(column).lte(value))
    }

    /**
     * 小于等于
     */
    fun <R : Any> le(func: KProperty1<*, R>, value: Any) {
        this.eq(SqlSelectBuilder.build(func), value)
    }

    /**
     * 包含
     */
    fun <R : Any> `in`(func: KProperty1<*, R>, vararg value: Any) {
        query.addCriteria(Criteria.where(SqlSelectBuilder.build(func)).`in`(*value))
    }

    /**
     * 包含
     */
    fun <R : Any> `in`(func: KProperty1<*, R>, value: Collection<Any>) {
        query.addCriteria(Criteria.where(SqlSelectBuilder.build(func)).`in`(*value.toTypedArray()))
    }

    /**
     * 包含
     */
    fun `in`(column: String, vararg value: Any) {
        query.addCriteria(Criteria.where(column).`in`(*value))
    }

    /**
     * 包含
     */
    fun `in`(column: String, value: Collection<Any>) {
        query.addCriteria(Criteria.where(column).`in`(*value.toTypedArray()))
    }

    /**
     * 之间
     */
    fun between(column: String, minValue: Any, maxValue: Any) {
        query.addCriteria(Criteria.where(column).gte(minValue).lte(maxValue))
    }

    /**
     * 之间
     */
    fun <R : Any> between(func: KProperty1<*, R>, minValue: Any, maxValue: Any) {
        this.between(SqlSelectBuilder.build(func), minValue, maxValue)
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