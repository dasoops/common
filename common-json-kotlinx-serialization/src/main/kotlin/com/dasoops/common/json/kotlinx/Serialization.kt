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

/**
 * @title: Json
 * @classPath com.dasoops.common.core.util.json.Json
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/18
 * @version 1.0.0
 * @description: json序列化
 * @see [Json]
 */
open class Serialization : IJson {
    val serializer = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
        serializersModule = SerializersModule {
            val map = Resources.scan("com.dasoops").filter {
                DataEnum::class.java.isAssignableFrom(it) && it.isEnum
            }.map {
                val use = it.getAnnotation(UseSerializer::class.java).use
                @Suppress("UNCHECKED_CAST")
                val create = use.objectInstance!!.create(it as Class<DataEnum<*>>)
                it.kotlin to create
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
    
    