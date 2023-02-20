package com.dasoops.common.entity.vo.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @title MapModel
 * @classPath com.dasoops.common.entity.vo.base.MapModel
 * @author DasoopsNicole@Gmail.com
 * @date 2022/12/15
 * @version 1.0.0
 * @description Map对象, 为解决传递给前端Map结构数据时乱序问题
 */
@Data
public class MapModel<K, V> {

    /**
     * 键
     */
    @ApiModelProperty(value = "键", notes = "键", required = false)
    private K key;

    /**
     * 值
     */
    @ApiModelProperty(value = "值", notes = "值", required = false)
    private V value;
}
