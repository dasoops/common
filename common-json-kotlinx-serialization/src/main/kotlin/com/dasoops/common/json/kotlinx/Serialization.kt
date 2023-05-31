package com.dasoops.common.json.kotlinx

import com.dasoops.common.json.core.IJson
import com.dasoops.common.json.core.dataenum.BooleanEnum
import com.dasoops.common.json.core.dataenum.DataEnum
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import kotlinx.serialization.modules.polymorphic

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
            contextual(IntDataEnumSerializer(BooleanEnum::class.java))
        }
    }

    init {
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: Serialization
    }

    override fun toJsonStr(obj: Any): String {
        TODO("Not yet implemented")
    }

    override fun <T> parse(jsonStr: String, clazz: Class<T>): T {
        TODO("Not yet implemented")
    }

    override fun <T> parseList(jsonStr: String): List<T> {
        TODO("Not yet implemented")
    }
}
    
    