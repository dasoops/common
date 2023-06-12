package com.dasoops.common.json.kotlinx

import com.dasoops.common.json.core.dataenum.DataEnum
import com.dasoops.common.json.core.dataenum.StrDataEnum
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object StrDataEnumSerializerFactory : DataEnumSerializerFactory<StrDataEnum>() {
    override fun create(entityClass: Class<StrDataEnum>) = StrDataEnumSerializer(entityClass)

    class StrDataEnumSerializer<T : StrDataEnum>(clazz: Class<T>) : DataEnumSerializer<T>(clazz) {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("strDataEnum", PrimitiveKind.STRING)

        override fun deserialize(decoder: Decoder): T {
            return DataEnum.getBy(clazz, decoder.decodeString())!!
        }

        override fun serialize(encoder: Encoder, value: T) {
            encoder.encodeString(value.data)
        }
    }
}
