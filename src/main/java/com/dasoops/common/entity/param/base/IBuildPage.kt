package com.dasoops.common.entity.param.base

import com.baomidou.mybatisplus.core.metadata.IPage
import com.dasoops.common.entity.dbo.base.BaseDo

/**
 * 构建分页器
 * @title: IBuildPage
 * @classPath com.dasoops.common.entity.param.base.IBuildPage
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/28
 * @version 1.0.0
 * @see [IBuildPage]
 */
interface IBuildPage<T : BaseDo> {
    /**
     * 构建分页器
     * @return [IPage<T>?]
     */
    fun buildPage(): IPage<T>
}