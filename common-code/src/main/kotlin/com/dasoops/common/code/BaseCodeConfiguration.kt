package com.dasoops.common.code

import com.dasoops.common.code.web.CodeWebApi
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
abstract class BaseCodeConfiguration