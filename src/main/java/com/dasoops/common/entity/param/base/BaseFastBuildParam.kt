package com.dasoops.common.entity.param.base

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.dasoops.common.entity.dbo.base.BaseDo

/**
 * @Title: BaseBuildParam
 * @ClassPath com.dasoops.common.entity.param.base.BaseBuildParam
 * @Author DasoopsNicole@Gmail.com
 * @Date 2023/01/23
 * @Version 1.0.0
 * @Description: 快速构建param
 * @see Serializable
 *
 * @see IBuildWrapper
 *
 * @see IBuildDo
 */
abstract class BaseFastBuildParam<T : BaseDo> : BaseParam(), IBuildWrapper<T>, IBuildDo<T> {
    override fun buildQueryWrapper(): QueryWrapper<T> {
        return super.buildQueryWrapper().apply{
            this@apply.entity = super.buildDo()
        }
    }
}