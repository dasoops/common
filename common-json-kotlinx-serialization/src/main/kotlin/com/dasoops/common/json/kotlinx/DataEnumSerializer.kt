package com.dasoops.common.json.kotlinx

import com.dasoops.common.json.core.dataenum.DataEnum
import kotlinx.serialization.KSerializer
import kotlin.reflect.KClass

annotation class UseSerializer(
    val use: KClass<DataEnumSerializerFactory<DataEnum<*>>>
)

/**
 * 数据枚举序列化器
 * @author DasoopsNicole@Gmail.com
 * @date 2023/05/31
 * @see [DataEnumSerializer]
 */
abstract class DataEnumSerializer<T : DataEnum<*>>(val clazz: Class<out T>) : KSerializer<T>

/**
 * 数据枚举序列化器工厂
 * @author DasoopsNicole@Gmail.com
 * @date 2023/05/31
 * @see [DataEnumSerializerFactory]
 */
abstract class DataEnumSerializerFactory<T : DataEnum<*>> {
    abstract fun create(entityClass: Class<T>): DataEnumSerializer<T>
}