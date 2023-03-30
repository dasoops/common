package com.dasoops.common.db.entity.param

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.dasoops.common.db.entity.dbo.base.BaseDo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * 分页参数
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/30
 * @see [PageParam]
 */
@Api("base")
@ApiModel(description = "分页参数")
data class PageParam<T : BaseDo>(
    @ApiModelProperty(value = "当前页码", notes = "当前页码", example = "1", required = true)
    val current: Int,

    @ApiModelProperty(value = "每页显示数量", notes = "每页显示数量", example = "10", required = true)
    val size: Int,
) : IBuildPage<T> {
    override fun buildPage(): IPage<T> {
        return Page(current.toLong(), size.toLong())
    }
}