package com.dasoops.common.dict

import cn.hutool.core.util.StrUtil
import com.dasoops.common.core.util.resources.Resources
import com.dasoops.common.json.core.dataenum.DataEnum
import com.google.common.base.CaseFormat
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import java.util.*

/**
 * 基本枚举字典配置
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/20
 * @see [BaseDictionaryConfiguration]
 */
@Import(DictionaryController::class)
abstract class BaseDictionaryConfiguration(vararg basePath: String) {

    private val log = LoggerFactory.getLogger(javaClass)
    val classList: Collection<Class<DataEnum<*>>>

    init {
        log.info("初始化字典项")
        classList = Resources.scan(javaClass.classLoader, *basePath)
            .filter { DataEnum::class.java.isAssignableFrom(it) && it.isEnum }
            .map { it as Class<DataEnum<*>> }
    }

    @Bean
    open fun buildValueDictData(): ValueDictData {
        return classList.associate { clazz ->
            buildDictName(clazz) to clazz.enumConstants.associate {
                val key = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, it.toString())
                key to it.data
            }.toMap(ValueDictNode())
        }.toMap(ValueDictData())
    }

    @Bean
    open fun buildReverseValueDictData(): ReverseValueDictData {
        return classList.associate { clazz ->
            buildDictName(clazz) to clazz.enumConstants.associate {
                val key = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, it.toString())
                it.data to key
            }.toMap(ReverseValueDictNode())
        }.toMap(ReverseValueDictData())
    }

    @Bean
    open fun buildArrayDictData(): ArrayDictData {
        return classList.associate { clazz ->
            buildDictName(clazz) to clazz.enumConstants.map {
                val key = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, it.toString())
                ArrayDictDataNode(
                    key = key,
                    value = it.data
                )
            }.toList()
        }.toMap(ArrayDictData())
    }

    @Bean
    open fun buildEasyDictData(): EasyDictData {
        val easyDictData = EasyDictData()
        classList.forEach { clazz ->
            easyDictData[buildDictName(clazz)] =
                clazz.enumConstants.associate {
                    val key = buildNodeKey(it)
                    key to DictInner(
                        value = it.data,
                        key = key,
                        data = if (ApiEnum::class.java.isAssignableFrom(clazz)) {
                            (it as ApiEnum).dataMap
                        } else {
                            null
                        },
                    )
                }.toMap(EasyDictNode())
        }
        return easyDictData
    }

    @Bean
    open fun buildDictData(): DictData {
        return classList.map { clazz ->
            DictNode(buildDictName(clazz), clazz.enumConstants.map {
                DictInner(
                    value = it.data,
                    key = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, it.toString()),
                    data = if (ApiEnum::class.java.isAssignableFrom(clazz)) {
                        (it as ApiEnum).dataMap
                    } else {
                        null
                    },
                )
            }.toList())
        }.toCollection(DictData())
    }

    private fun buildNodeKey(it: DataEnum<*>): String =
        CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, it.toString())

    private fun buildDictName(clazz: Class<DataEnum<*>>): String = StrUtil.lowerFirst(clazz.simpleName)
}