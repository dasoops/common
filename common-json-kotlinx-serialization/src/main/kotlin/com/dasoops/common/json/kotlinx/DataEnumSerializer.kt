package com.dasoops.common.json.kotlinx

import com.dasoops.common.json.core.dataenum.DataEnum
import com.dasoops.common.json.core.dataenum.IntDataEnum
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * 枚举数据序列化器
 * @author DasoopsNicole@Gmail.com
 * @date 2023/05/31
 * @see [DataEnumSerializer]
 */
class DataEnumSerializer<T : IntDataEnum>(val clazz: Class<T>) : KSerializer<T> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("dataEnum", PrimitiveKind.INT)

    override fun deserialize(decoder: Decoder): T {
        return DataEnum.getBy(clazz, decoder.decodeInt())!!
    }

    override fun serialize(encoder: Encoder, value: T) {
        encoder.encodeInt(value.data)
    }
}

/**
 * 枚举数据序列化器
 * @author DasoopsNicole@Gmail.com
 * @date 2023/05/31
 * @see [DataEnumSerializer]
 */
class IntDataEnumSerializer<T : IntDataEnum>(val clazz: Class<T>) : KSerializer<T> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("dataEnum", PrimitiveKind.INT)

    override fun deserialize(decoder: Decoder): T {
        return DataEnum.getBy(T::class.java, decoder.decodeInt())!!
    }

    override fun serialize(encoder: Encoder, value: T) {
        encoder.encodeInt(value.data)
    }
}