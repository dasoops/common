package com.dasoops.common.dict

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * 字典控制器
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/20
 * @see [DictionaryController]
 */
@Api(tags = ["dict"])
@ResponseBody
@RequestMapping("dict")
class DictionaryController(
    private val easyDictData: EasyDictData,
    private val dictData: DictData,
    private val onlyValueDictData: OnlyValueDictData,
) {

    @GetMapping("getEasyDict")
    @ApiOperation(value = "获取简单字典")
    fun getEasyDict(): Result<EasyDictData> {
        return Result.success(easyDictData)
    }

    @GetMapping("getOnlyValueDict")
    @ApiOperation(value = "获取仅值字典")
    fun getOnlyValueDict(): Result<OnlyValueDictData> {
        return Result.success(onlyValueDictData)
    }

    @GetMapping("getDict")
    @ApiOperation(value = "获取字典")
    fun getDict(): Result<DictData> {
        return Result.success(dictData)
    }
}