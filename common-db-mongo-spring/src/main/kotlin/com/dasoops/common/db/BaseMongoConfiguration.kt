package com.dasoops.common.db

import com.dasoops.common.core.serializer.DataEnum2IntConvertFactory
import com.dasoops.common.core.serializer.DataEnum2StringConvertFactory
import com.dasoops.common.core.serializer.Integer2DataEnumConvertFactory
import com.dasoops.common.core.serializer.String2DataEnumConvertFactory
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.core.convert.MongoCustomConversions

/**
 * 基础mongo配置
 * @author DasoopsNicole@Gmail.com
 * @date 2023-04-17
 */
abstract class BaseMongoConfiguration {

    /**
     * 自定义序列化
     * @return [MongoCustomConversions]
     */
    @Bean
    open fun mongoCustomConversions(): MongoCustomConversions {
        return MongoCustomConversions.create {
            // Int -> DbColumnEnum 解析器
            it.registerConverterFactory(Integer2DataEnumConvertFactory())
            // String -> DbColumnEnum 解析器
            it.registerConverterFactory(String2DataEnumConvertFactory())
            // DbColumnEnum -> Int 解析器
            it.registerConverterFactory(DataEnum2IntConvertFactory())
            // DbColumnEnum -> string解析器
            it.registerConverterFactory(DataEnum2StringConvertFactory())
        }
    }
}