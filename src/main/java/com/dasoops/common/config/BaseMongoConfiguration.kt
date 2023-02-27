package com.dasoops.common.config

import com.dasoops.common.config.serializer.IDbColumnEnumToIntConvertFactory
import com.dasoops.common.config.serializer.IntegerToIDbColumnEnumConvertFactory
import com.dasoops.common.config.serializer.StringToIDbColumnEnumConvertFactory
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.core.convert.MongoCustomConversions

/**
 * 基本mongo配置
 * @title: BaseMongoConfiguration
 * @classPath com.dasoops.common.config.BaseMongoConfiguration
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/27
 * @version 1.0.0
 * @see [BaseMongoConfiguration]
 */
open class BaseMongoConfiguration {

    /**
     * 自定义序列化
     * @return [MongoCustomConversions]
     */
    @Bean
    open fun mongoCustomConversions(): MongoCustomConversions {
        return MongoCustomConversions.create {
            it.registerConverterFactory(IntegerToIDbColumnEnumConvertFactory())
            it.registerConverterFactory(StringToIDbColumnEnumConvertFactory())
            it.registerConverterFactory(IDbColumnEnumToIntConvertFactory())
        }
    }
}