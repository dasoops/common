package com.dasoops.common.dict

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import com.dasoops.common.entity.result.Result

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
    private val easyDictData: EasyDictData,
    private val dictData: DictData,
    private val onlyValueDictData: OnlyValueDictData,
) {

    @GetMapping("getEasyDict")
    @Operation(summary ="获取简单字典")
    fun getEasyDict(): Result<EasyDictData> {
        return Result.success(easyDictData)
    }

    @GetMapping("getOnlyValueDict")
    @Operation(summary ="获取仅值字典")
    fun getOnlyValueDict(): Result<OnlyValueDictData> {
        return Result.success(onlyValueDictData)
    }

    @GetMapping("getDict")
    @Operation(summary ="获取字典")
    fun getDict(): Result<DictData> {
        return Result.success(dictData)
    }
}