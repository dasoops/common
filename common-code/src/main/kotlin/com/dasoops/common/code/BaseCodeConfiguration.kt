package com.dasoops.common.code

import com.dasoops.common.code.converter.OpenApi2CodeFileConverter
import com.dasoops.common.code.converter.Scheme2EntityConverter
import com.dasoops.common.code.converter.SwaggerPath2RequestConverter
import com.dasoops.common.code.web.CodeWebApi
import com.dasoops.common.util.ConversionService
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import

/**
 * 基本代码生成器自动配置
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/06
 * @see [BaseCodeConfiguration]
 */
@Import(CodeWebApi::class)
@ComponentScan("com.dasoops.common.code")
abstract class BaseCodeConfiguration {
    init {
        ConversionService.addConverter(OpenApi2CodeFileConverter())
        ConversionService.addConverter(Scheme2EntityConverter())
        ConversionService.addConverter(SwaggerPath2RequestConverter())
    }
}