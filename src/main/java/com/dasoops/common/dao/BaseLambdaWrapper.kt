package com.dasoops.common.dao

import cn.hutool.core.lang.func.Func1
import com.dasoops.common.entity.dbo.base.BaseMongoDo

abstract class BaseLambdaWrapper<T : BaseMongoDo> {

    val builder = MongoQueryBuilder<T>()

    fun eq(func: Func1<T, *>, value: Any): BaseLambdaWrapper<T> {
        builder.eq(func, value)
        return this
    }

    fun `in`(func: Func1<T, *>, value: List<Any>): BaseLambdaWrapper<T> {
        builder.`in`(func, value)
        return this
    }

}