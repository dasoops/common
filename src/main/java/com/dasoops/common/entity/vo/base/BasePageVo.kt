package com.dasoops.common.entity.vo.base

import io.swagger.annotations.ApiModelProperty

abstract class BasePageVo<T> : BaseVo() {

    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录数", notes = "总记录数", required = true)
    var total: Int? = null

    /**
     * 数据集合
     *//*
    @ApiModelProperty(value = "数据集合", example = "[0,1,2,4]", required = false)
    var dataList: List<T>? = null*/
}