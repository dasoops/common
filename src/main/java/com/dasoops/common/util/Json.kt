package com.dasoops.common.util

import cn.hutool.core.date.DatePattern
import com.dasoops.common.entity.enums.database.IDbColumnEnum
import com.dasoops.common.util.entity.IDbColumnEnumDeserializer
import com.dasoops.common.util.entity.dto.JsonObj
import com.dasoops.common.util.entity.other.ListTypeReference
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.databind.module.SimpleDeserializers
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import com.fasterxml.jackson.databind.type.ClassKey
import com.fasterxml.jackson.module.kotlin.kotlinModule
import java.text.SimpleDateFormat


/**
 * @title: Json
 * @classPath com.dasoops.common.util.Json
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/18
 * @version 1.0.0
 * @description: json序列化
 * @see [Json]
 */
object Json {

    /**
     * json序列器
     */
    val serializer: ObjectMapper

    init {
        serializer = JsonMapper.builder().run {
            addModule(SimpleModule().apply {
                // long转string 精度丢失
                addSerializer(Long::class.java, ToStringSerializer.instance)
                addSerializer(java.lang.Long.TYPE, ToStringSerializer.instance)
                // 数据库字段枚举序列化
                addSerializer(IDbColumnEnum::class.java, object : JsonSerializer<IDbColumnEnum>() {
                    override fun serialize(value: IDbColumnEnum, gen: JsonGenerator, serializers: SerializerProvider) {
                        gen.writeString(value.dbValue.toString())
                    }
                })
                // 数据库字段枚举反序列化
                // 重写SimpleDeserializers.findEnumDeserializer()方法,不然不生效
                setDeserializers(object : SimpleDeserializers() {
                    override fun findEnumDeserializer(type: Class<*>?, config: DeserializationConfig?, beanDesc: BeanDescription?): JsonDeserializer<*>? {
                        return super.findEnumDeserializer(type, config, beanDesc) ?: type?.interfaces?.firstNotNullOf { _classMappings[ClassKey(it)] }
                    }
                }.apply {
                    addDeserializer(IDbColumnEnum::class.java, IDbColumnEnumDeserializer())
                })
            })

            // 美化输出
            configure(SerializationFeature.INDENT_OUTPUT, true)

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

            // kotlin模块
            addModule(kotlinModule())

            // 自动查找并注册模块
            findAndAddModules()

            // build
            build()
        }
    }

    fun toJsonStr(obj: Any): String {
        return serializer.writeValueAsString(obj)
    }

    fun parse(jsonStr: String): JsonObj {
        return serializer.readValue(jsonStr, JsonObj::class.java)
    }

    fun <T> parse(jsonStr: String, clazz: Class<T>): T {
        return serializer.readValue(jsonStr, clazz)
    }

    fun <T> parse(jsonStr: String, typeReference: TypeReference<T>): T {
        return serializer.readValue(jsonStr, typeReference)
    }

    fun <T> parseList(jsonStr: String, clazz: Class<T>): List<T> {
        return serializer.readValue(jsonStr, ListTypeReference())
    }

    fun parseNode(jsonStr: String): JsonNode {
        return serializer.readTree(jsonStr)
    }

    fun getNode(jsonStr: String, name: String): String {
        return serializer.readTree(jsonStr).get(name).asText()
    }
}