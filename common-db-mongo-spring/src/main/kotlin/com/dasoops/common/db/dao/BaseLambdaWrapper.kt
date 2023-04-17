package com.dasoops.common.db.dao

import com.dasoops.common.db.entity.dbo.BaseMongoDo
import kotlin.reflect.KProperty1

abstract class BaseLambdaWrapper<T : BaseMongoDo, Children : BaseLambdaWrapper<T, Children>> {

    val builder = MongoQueryBuilder()
    protected val typethis = this as Children

    /**
     * 相等
     */
    fun eq(column: String, value: Any): Children {
        builder.eq(column, value)
        return typethis
    }

    /**
     * 相等
     */
    fun <R : Any> eq(func: KProperty1<T, R>, value: R): Children {
        builder.eq(func, value)
        return typethis
    }

    /**
     * 不相等
     */
    fun ne(column: String, value: Any): Children {
        builder.ne(column, value)
        return typethis
    }

    /**
     * 不相等
     */
    fun <R : Any> ne(func: KProperty1<T, R>, value: R): Children {
        builder.ne(func, value)
        return typethis
    }

    /**
     * 大于
     */
    fun gt(column: String, value: Any): Children {
        builder.gt(column, value)
        return typethis
    }

    /**
     * 大于
     */
    fun <R : Any> gt(func: KProperty1<T, R>, value: R): Children {
        builder.gt(func, value)
        return typethis
    }

    /**
     * 大于等于
     */
    fun ge(column: String, value: Any): Children {
        builder.ge(column, value)
        return typethis
    }

    /**
     * 大于等于
     */
    fun <R : Any> ge(func: KProperty1<T, R>, value: R): Children {
        builder.ge(func, value)
        return typethis
    }

    /**
     * 小于
     */
    fun lt(column: String, value: Any): Children {
        builder.lt(column, value)
        return typethis
    }

    /**
     * 小于
     */
    fun <R : Any> lt(func: KProperty1<T, R>, value: R): Children {
        builder.lt(func, value)
        return typethis
    }

    /**
     * 小于等于
     */
    fun le(column: String, value: Any): Children {
        builder.le(column, value)
        return typethis
    }

    /**
     * 小于等于
     */
    fun <R : Any> le(func: KProperty1<T, R>, value: R): Children {
        builder.le(func, value)
        return typethis
    }

    /**
     * 包含
     */
    fun <R : Any> `in`(func: KProperty1<T, R>, value: Collection<Any>): Children {
        builder.`in`(func, *value.toTypedArray())
        return typethis
    }

    /**
     * 包含
     */
    fun `in`(column: String, value: Collection<Any>): Children {
        builder.`in`(column, *value.toTypedArray())
        return typethis
    }

    /**
     * 包含
     */
    fun <R : Any> between(func: KProperty1<*, R>, minValue: Any, maxValue: Any): Children {
        builder.between(func, minValue, maxValue)
        return typethis
    }

    /**
     * 包含
     */
    fun between(column: String, minValue: Any, maxValue: Any): Children {
        builder.between(column, minValue, maxValue)
        return typethis
    }

    /**
     * orderByAsc 升序
     * @param [column] 字段
     */
    fun orderBy(column: String): Children {
        builder.orderByAsc(column)
        return typethis
    }

    /**
     * orderByAsc 升序
     * @param [func] 字段
     */
    fun <R : Any> orderBy(func: KProperty1<T, R>): Children {
        builder.orderByAsc(func)
        return typethis
    }

    /**
     * orderByDesc 降序
     */
    fun <R : Any> orderByDesc(func: KProperty1<T, R>): Children {
        builder.orderByDesc(func)
        return typethis
    }

    /**
     * orderByDesc 降序
     * @param [column] 列
     */
    fun <S> orderByDesc(column: String): Children {
        builder.orderByDesc(column)
        return typethis
    }

    fun limit(limit: Int): Children {
        builder.limit(limit)
        return typethis
    }

    fun skip(skip: Long): Children {
        builder.skip(skip)
        return typethis
    }

}