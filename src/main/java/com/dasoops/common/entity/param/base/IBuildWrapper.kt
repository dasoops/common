package com.dasoops.common.entity.param.base

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.dasoops.common.entity.dbo.base.BaseDo

/**
 * @Title: IBuildWrapper
 * @ClassPath com.dasoops.common.entity.param.base.IBuildWrapper
 * @Author DasoopsNicole@Gmail.com
 * @Date 2023/01/13
 * @Version 1.0.0
 * @Description: buildWrapper
 */
interface IBuildWrapper<T : BaseDo> {
    /**
     * 构建条件构造器
     *
     * @return [QueryWrapper]<[T]>
     */
    fun buildQueryWrapper(): QueryWrapper<T> {
        return QueryWrapper()
    }
}