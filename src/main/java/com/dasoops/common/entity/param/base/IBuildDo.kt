package com.dasoops.common.entity.param.base

import cn.hutool.core.bean.BeanUtil
import com.dasoops.common.entity.dbo.base.BaseDo
import com.dasoops.common.util.ReflectUtil

/**
 * @Title: IBuildDo
 * @ClassPath com.dasoops.common.entity.param.base.IBuildDo
 * @Author DasoopsNicole@Gmail.com
 * @Date 2023/01/13
 * @Version 1.0.0
 * @Description: 构建do
 */
interface IBuildDo<T : BaseDo> {
    /**
     * 构建do
     *
     * @return [T]
     */
    fun buildDo(): T {
        val t = ReflectUtil.getGenericInstance<T>(javaClass)
        BeanUtil.copyProperties(this, t)
        return t
    }
}