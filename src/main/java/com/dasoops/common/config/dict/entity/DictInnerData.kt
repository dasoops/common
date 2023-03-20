package com.dasoops.common.config.dict.entity

import io.swagger.annotations.Api
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * 字典内部数据
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/20
 * @see [DictInnerData]
 */
@Api("dict")
@ApiModel(description = "字典内部数据")
data class DictInnerData(

    /**
     * 值
     */
    @ApiModelProperty(value = "值", required = true)
    val value: Int,

    /**
     * 含义
     */
    @ApiModelProperty(value = "含义", required = true)
    val key: String,
)
