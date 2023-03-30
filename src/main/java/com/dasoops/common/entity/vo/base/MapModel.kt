package com.dasoops.common.entity.vo.base

import io.swagger.annotations.ApiModelProperty
import org.checkerframework.checker.units.qual.K

/**
 * @author DasoopsNicole@Gmail.com
 * @version 1.0.0
 * @title MapModel
 * @classPath com.dasoops.common.entity.vo.base.MapModel
 * @date 2022/12/15
 * @description Map对象, 为解决传递给前端Map结构数据时乱序问题
 */
data class MapModel<K, V>(
    /**
     * 键
     */
    @ApiModelProperty(value = "键", notes = "键", required = false)
    val key: K,

    /**
     * 值
     */
    @ApiModelProperty(value = "值", notes = "值", required = false)
    val value: V
)