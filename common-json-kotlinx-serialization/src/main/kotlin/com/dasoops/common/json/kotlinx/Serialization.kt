package com.dasoops.common.json.kotlinx

import com.dasoops.common.core.util.resources.Resources
import com.dasoops.common.json.core.IJson
import com.dasoops.common.json.core.dataenum.DataEnum
import com.dasoops.common.json.core.dataenum.IntDataEnum
import com.dasoops.common.json.core.dataenum.StrDataEnum
import kotlinx.serialization.KSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

/**
 * @title: Json
 * @classPath com.dasoops.common.core.util.json.Json
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/18
 * @version 1.0.0
 * @description: json序列化
 * @see [Json]
 */
@Suppress("UNCHECKED_CAST")
open class Serialization : IJson {
    val serializer = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
        //TODO(枚举序列化存在问题)
        serializersModule = SerializersModule {
            val map = Resources.scan("com.dasoops").filter {
                DataEnum::class.java.isAssignableFrom(it) && it.isEnum
            }.map {
                it as Class<DataEnum<*>>
                val use =
                    if (StrDataEnum::class.java.isAssignableFrom(it)) {
                        StrDataEnumSerializerFactory as DataEnumSerializerFactory<DataEnum<*>>
                    } else if (IntDataEnum::class.java.isAssignableFrom(it)) {
                        IntDataEnumSerializerFactory as DataEnumSerializerFactory<DataEnum<*>>
                    } else {
                        it.getAnnotation(UseSerializer::class.java).use.objectInstance!!
                    }
                it.kotlin to use.create(it)
            }
            polymorphic(DataEnum::class) {
                map.forEach {
                    subclass(it.first, it.second)
                }
            }
        }
    }

    init {
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: Serialization
    }

    override fun toJsonStr(obj: Any): String {
        return serializer.encodeToString(obj)
    }

    override fun <T> parse(jsonStr: String, clazz: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return serializer.decodeFromString(serializer.serializersModule.serializer(clazz), jsonStr) as T
    }
}
    
    