package com.dasoops.common.config.dict

import com.dasoops.common.entity.result.Result
import com.github.xiaoymin.knife4j.annotations.ApiSupport
import io.swagger.annotations.Api
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
@ApiSupport(author = "DasoopsNicole@gmail.com")
@ResponseBody
@RequestMapping("dict")
class DictionaryController(
    private val dictData: DictData
) {

    @GetMapping("getData")
    fun getDict(): Result<DictData> {
        return Result.success(dictData)
    }
}