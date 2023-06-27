package com.dasoops.common.dict

import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import java.io.Serializable

/**
 * 字典内部数据
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/20
 * @see [DictInner]
 */
@Tag(name = "dict")
@Schema(description = "字典树内部数据")
data class DictInner(

    /**
     * 值
     */
    @field:Schema(description = "值", required = true)
    val value: Serializable,

    /**
     * 键
     */
    @field:Schema(description = "键", required = true)
    val key: String,

    /**
     * 数据
     */
    @field:Schema(description = "数据", required = true)
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

@Tag(name = "dict")
@Schema(description = "简单字典数据")
class EasyDictData : HashMap<String, EasyDictNode>()

@Tag(name = "dict")
@Schema(description = "简单字典树")
class EasyDictNode : HashMap<String, DictInner>()

@Tag(name = "dict")
@Schema(description = "标准字典数据")
class DictData : ArrayList<DictNode>()

@Tag(name = "dict")
@Schema(description = "标准字典树")
data class DictNode(
    /**
     * 树名称
     */
    @field:Schema(description = "树名称", required = true)
    val nodeName: String,
    /**
     * 树数据
     */
    val nodeData: List<DictInner>
)


@Tag(name = "dict")
@Schema(description = "仅值字典数据")
class ValueDictData : HashMap<String, OnlyValueDictNode>()

@Tag(name = "dict")
@Schema(description = "仅值字典树")
class OnlyValueDictNode : HashMap<String, Serializable>()

class ArrayDictData : HashMap<String, List<ArrayDictDataNode>>()

data class ArrayDictDataNode(
    val key: String,
    val value: Serializable
)