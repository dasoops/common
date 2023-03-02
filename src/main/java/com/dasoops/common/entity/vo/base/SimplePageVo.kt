package com.dasoops.common.entity.vo.base

import io.swagger.annotations.ApiModelProperty

/**
 * 简单页面vo
 * @title: SimplePageVo
 * @classPath com.dasoops.common.entity.vo.base.SimplePageVo
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/28
 * @version 1.0.0
 * @see [SimplePageVo]
 */
open class SimplePageVo<T : BaseInnerVo>(
    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录数", notes = "总记录数", required = true)
    override val total: Long,

    /**
     * 数据集合
     */
    @ApiModelProperty(value = "数据集合", example = "[]", required = false)
    open val dataList: List<T>
) : BasePageVo(total) {
    constructor(dataList: List<T>) : this(dataList.size.toLong(), dataList)
}