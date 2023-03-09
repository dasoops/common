package com.dasoops.common.entity.param.base

import cn.hutool.core.date.DateUtil
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.dasoops.common.entity.dbo.base.BaseDo
import io.swagger.annotations.ApiModelProperty

/**
 * @author DasoopsNicole@Gmail.com
 * @version 1.0.0
 * @title BasePageParam
 * @classPath com.dasoops.common.entity.param.base.BasePageParam
 * @date 2022/12/28
 * @description 基本页面参数
 */
abstract class BaseTimePageParam<T : BaseDo>(current: Int, size: Int) : BasePageParam<T>(current, size), IBuildWrapper<T> {
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间", notes = "开始时间", required = true)
    val beginTime: String? = null

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间", notes = "结束时间", required = false)
    val endTime: String? = null

    fun buildWrapper(column: String): QueryWrapper<T> {
        return super<BasePageParam>.buildQueryWrapper().apply {
            beginTime?.run { this@apply.ge("" != this, column, DateUtil.parse(this)) }
            endTime?.run { this@apply.le("" != this, column, DateUtil.parse(this)) }
        }
    }

    override fun buildQueryWrapper(): QueryWrapper<T> {
        return buildWrapper("CREATE_TIME")
    }
}