package com.dasoops.common.json.kotlinx

import com.dasoops.common.json.core.dataenum.DataEnum
import com.dasoops.common.json.core.dataenum.IntDataEnum
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object IntDataEnumSerializerFactory : DataEnumSerializerFactory<IntDataEnum>() {
    override fun create(entityClass: Class<IntDataEnum>) = IntDataEnumSerializer(entityClass)

    class IntDataEnumSerializer<T : IntDataEnum>(clazz: Class<T>) : DataEnumSerializer<IntDataEnum>(clazz) {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("intDataEnum", PrimitiveKind.INT)
        override fun deserialize(decoder: Decoder): IntDataEnum {
            return DataEnum.getBy(clazz, decoder.decodeInt())!!
        }

        override fun serialize(encoder: Encoder, value: IntDataEnum) {
            encoder.encodeInt(value.data)
        }
    }
}
