package com.dasoops.common.json

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean

/**
 * 全局json序列化配置类
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/30
 * @see [BaseJsonSerializerConfiguration]
 */
abstract class BaseJsonSerializerConfiguration {
    @Bean
    open fun getJacksonObjectMapper(): ObjectMapper {
        return Json.serializer
    }
}