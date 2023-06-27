package com.dasoops.common.dict

import com.dasoops.common.core.entity.result.Result
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
    private val easyDictData: EasyDictData,
    private val valueDictData: ValueDictData,
    private val reverseValueDictData: ReverseValueDictData,
    private val arrayDictData: ArrayDictData,
) {

    private val allDict = AllDict(
        dict = dictData,
        eDict = easyDictData,
        vDict = valueDictData,
        rvDict = reverseValueDictData,
        aDict = arrayDictData,
    )

    @GetMapping("getAll")
    @Operation(summary = "获取所有字典")
    fun getAll(): Result<AllDict> {
        return Result.success(allDict);
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