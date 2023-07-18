package com.dasoops.common.dict

import com.dasoops.common.core.entity.result.Result
import com.dasoops.common.json.core.dataenum.DataEnum
import com.google.common.base.CaseFormat
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * 字典控制器
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/20
 * @see [DictionaryController]
 */
@Tag(name = "字典")
@ResponseBody
@RequestMapping("dict")
class DictionaryController(
    private val dictData: DictData,
) {
    private lateinit var easyDictData: EasyDictData
    private lateinit var valueDictData: ValueDictData
    private lateinit var reverseValueDictData: ReverseValueDictData
    private lateinit var arrayDictData: ArrayDictData
    private lateinit var allDict: AllDict

    init {
        init()
    }

    fun init() {
        easyDictData = dictData.associate { dictNode ->
            dictNode.nodeName to dictNode.nodeData.associate {
                it.key to DictInner(it.value, it.key, it.data)
            }.toMap(EasyDictNode())
        }.toMap(EasyDictData())

        valueDictData = dictData.associate { dictNode ->
            dictNode.nodeName to dictNode.nodeData.associate {
                it.key to it.value
            }.toMap(ValueDictNode())
        }.toMap(ValueDictData())

        reverseValueDictData = dictData.associate { dictNode ->
            dictNode.nodeName to dictNode.nodeData.associate {
                it.value to it.key
            }.toMap(ReverseValueDictNode())
        }.toMap(ReverseValueDictData())

        arrayDictData = dictData.associate { dictNode ->
            dictNode.nodeName to dictNode.nodeData.map {
                ArrayDictDataNode(
                    key = it.key,
                    value = it.value
                )
            }.toList()
        }.toMap(ArrayDictData())

        allDict = AllDict(
            dict = dictData,
            eDict = easyDictData,
            vDict = valueDictData,
            rvDict = reverseValueDictData,
            aDict = arrayDictData,
        )
    }

    @GetMapping("getAll")
    @Operation(summary = "获取所有字典")
    fun getAll(): Result<AllDict> {
        return Result.success(allDict)
    }

    @GetMapping("getEasyDict")
    @Operation(summary = "获取简单字典")
    fun getEasyDict(): Result<EasyDictData> {
        return Result.success(easyDictData)
    }

    @GetMapping("getValueDict")
    @Operation(summary = "获取仅值字典")
    fun getValueDict(): Result<ValueDictData> {
        return Result.success(valueDictData)
    }

    @GetMapping("getReverseValueDict")
    @Operation(summary = "获取键值反转仅值字典")
    fun getReverseValueDict(): Result<ReverseValueDictData> {
        return Result.success(reverseValueDictData)
    }

    @GetMapping("getDict")
    @Operation(summary = "获取字典")
    fun getDict(): Result<DictData> {
        return Result.success(dictData)
    }

    @GetMapping("getArrayDict")
    @Operation(summary = "获取数组字典")
    fun getArrayDict(): Result<ArrayDictData> {
        return Result.success(arrayDictData)
    }
}