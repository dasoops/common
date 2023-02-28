package com.dasoops.common.entity.param.base

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.dasoops.common.entity.dbo.base.BaseDo
import io.swagger.annotations.ApiModelProperty

/**
 * @Title: BasePageParam
 * @ClassPath com.dasoops.common.entity.param.base.BasePageParam
 * @Author DasoopsNicole@Gmail.com
 * @Date 2022/12/28
 * @Version 1.0.0
 * @Description: 基本页面参数
 */
abstract class BasePageParam<T : BaseDo>(

    @ApiModelProperty(value = "当前页码", notes = "当前页码", example = "1", required = true)
    override val current: Int,

    @ApiModelProperty(value = "每页显示数量", notes = "每页显示数量", example = "10", required = true)
    override val size: Int,
) : BaseFastBuildParam<T>(), IBuildPage<T>, IPageParam {

    override fun buildPage(): IPage<T> {
        return Page(current.toLong(), size.toLong())
    }
}