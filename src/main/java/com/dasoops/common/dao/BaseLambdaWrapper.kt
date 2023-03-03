package com.dasoops.common.dao

import com.dasoops.common.entity.dbo.base.BaseMongoDo
import kotlin.reflect.KProperty1

abstract class BaseLambdaWrapper<T : BaseMongoDo, Children : BaseLambdaWrapper<T, Children>> {

    val builder = MongoQueryBuilder<T>()
    protected val typethis = this as Children

    fun <R> eq(func: KProperty1<T, R>, value: R): Children {
        builder.eq(func, value)
        return typethis
    }

    fun eq(column: String, value: Any): Children {
        builder.eq(column, value)
        return typethis
    }

    fun <R> `in`(func: KProperty1<T, R>, value: Collection<Any>): Children {
        builder.`in`(func, *value.toTypedArray())
        return typethis
    }

    fun `in`(column: String, value: Collection<Any>): Children {
        builder.`in`(column, *value.toTypedArray())
        return typethis
    }
}