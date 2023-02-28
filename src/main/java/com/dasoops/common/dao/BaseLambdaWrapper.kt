package com.dasoops.common.dao

import cn.hutool.core.lang.func.Func1
import com.dasoops.common.entity.dbo.base.BaseMongoDo

abstract class BaseLambdaWrapper<T : BaseMongoDo, Children : BaseLambdaWrapper<T, Children>> {

    val builder = MongoQueryBuilder<T>()
    protected val typethis = this as Children

    fun eq(func: Func1<T, *>, value: Any): Children {
        builder.eq(func, value)
        return typethis
    }

    fun `in`(func: Func1<T, *>, value: Collection<Any>): Children {
        builder.`in`(func, value)
        return typethis
    }

}