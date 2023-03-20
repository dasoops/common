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
    val key: String,

    /**
     * 数据
     */
    @ApiModelProperty(value = "数据", required = true)
    val data: Map<String, String>?,
) : HashMap<String, Any>() {
    init {
        super.put("value", value)
        super.put("key", key)
        data?.entries?.forEach {
            super.put(it.key, it.value)
        }
    }
}

@Api("dict")
@ApiModel(description = "简单字典数据")
class EasyDictData : HashMap<String, EasyDictNode>()

@Api("dict")
@ApiModel(description = "简单字典树")
class EasyDictNode : HashMap<String, DictInner>()

@Api("dict")
@ApiModel(description = "标准字典数据")
class DictData : ArrayList<DictNode>()

@Api("dict")
@ApiModel(description = "标准字典树")
data class DictNode(
    /**
     * 树名称
     */
    @ApiModelProperty(value = "树名称", required = true)
    val nodeName: String,
    /**
     * 树数据
     */
    val nodeData: List<DictInner>
)


@Api("dict")
@ApiModel(description = "仅值字典数据")
class OnlyValueDictData : HashMap<String, OnlyValueDictNode>()

@Api("dict")
@ApiModel(description = "仅值字典树")
class OnlyValueDictNode : HashMap<String, Int>()