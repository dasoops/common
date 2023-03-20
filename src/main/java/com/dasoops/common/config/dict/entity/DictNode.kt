package com.dasoops.common.config.dict.entity

import io.swagger.annotations.Api
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * dict数据
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/20
 * @see [DictNode]
 */
@Api("dict")
@ApiModel(description = "字典数据")
class DictNode(
    /**
     * 数据集合
     */
    @ApiModelProperty(value = "数据集合", required = true)
    val dataList: List<DictInnerData>
)