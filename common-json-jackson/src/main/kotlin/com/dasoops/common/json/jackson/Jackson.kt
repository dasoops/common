package com.dasoops.common.json.jackson

import cn.hutool.core.date.DatePattern
import com.dasoops.common.json.core.IJson
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import com.fasterxml.jackson.module.kotlin.kotlinModule
import java.text.SimpleDateFormat


/**
 * @title: Json
 * @classPath com.dasoops.common.core.util.json.Json
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/18
 * @version 1.0.0
 * @description: json序列化
 * @see [Json]
 */
open class Jackson : IJson {

    init {
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: Jackson
    }

    /**
     * json序列器
     */
    val serializer: ObjectMapper = JsonMapper.builder().run {
        addModule(SimpleModule().apply {
            // long转string 精度丢失
            addSerializer(Long::class.java, ToStringSerializer.instance)
            //addSerializer(Int::class.java, ToStringSerializer.instance)
            addSerializer(java.lang.Long.TYPE, ToStringSerializer.instance)
            //addSerializer(Integer.TYPE, ToStringSerializer.instance)
        })

        // 美化输出
        configure(SerializationFeature.INDENT_OUTPUT, false)

        // 反序列化的时候如果多了其他属性,不抛出异常
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        // 日期
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        defaultDateFormat(SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN))

        // 设置序列化反序列化采用直接处理字段的方式， 不依赖setter 和 getter
        visibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
        visibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)

        // 大小写宽容
        configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)

        // 默认序列化时忽略null字段
        serializationInclusion(JsonInclude.Include.NON_NULL)

        // kotlin模块
        addModule(kotlinModule())

        // 自动查找并注册模块
        findAndAddModules()

        // build
        build()
    }

    override fun toJsonStr(obj: Any): String {
        return serializer.writeValueAsString(obj)
    }

    override fun <T> parse(jsonStr: String, clazz: Class<T>): T {
        return serializer.readValue(jsonStr, clazz)
    }

    fun <T> parseList(jsonStr: String): List<T> {
        return serializer.readValue(jsonStr, object : TypeReference<List<T>>() {})
    }

    fun <T> parse(jsonStr: String, typeReference: TypeReference<T>): T {
        return serializer.readValue(jsonStr, typeReference)
    }

    fun parseNode(jsonStr: String): JsonNode {
        return serializer.readTree(jsonStr)
    }
}