package com.dasoops.common.config

import com.dasoops.common.util.json.Json
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean

/**
 * @author DasoopsNicole@Gmail.com
 * @version 1.0.0
 * @title BaseJsonConfiguration
 * @classPath com.dasoops.common.config.BaseJsonConfiguration
 * @date 2023/02/18
 * @description 全局序列化配置类
 */
abstract class BaseJsonSerializerConfiguration {

    @Bean
    open fun getJacksonObjectMapper(): ObjectMapper {
        return Json.serializer
    }

}