package com.dasoops.common.config.dict

import io.swagger.annotations.Api
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * 字典内部数据
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/20
 * @see [DictInner]
 */
@Api("dict")
@ApiModel(description = "字典树内部数据")
data class DictInner(

    /**
     * 值
     */
    @ApiModelProperty(value = "值", required = true)
    val value: Int,

    /**
     * 键
     */
    @ApiModelProperty(value = "键", required = true)
    val key:String,

    /**
     * 数据
     */
    @ApiModelProperty(value = "数据", required = true)
    val data: Map<String, String>,
) : HashMap<String, Any>() {
    init {
        super.put("value", value)
        super.put("key", key)
        data.entries.forEach {
            super.put(it.key, it.value)
        }
    }
}

@Api("dict")
@ApiModel(description = "字典数据")
class DictData : HashMap<String, DictNode>()

@Api("dict")
@ApiModel(description = "字典树")
class DictNode : ArrayList<DictInner>()