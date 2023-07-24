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
@Suppress("UNCHECKED_CAST")
abstract class BaseDictionaryConfiguration(basePath: List<String>) {

    private val log = LoggerFactory.getLogger(javaClass)
    val classList: Collection<Class<DataEnum<*>>>

    init {
        log.info("初始化字典项")
        classList = Resources.scan(javaClass.classLoader, basePath)
            .filter { DataEnum::class.java.isAssignableFrom(it) && it.isEnum }
            .map { it as Class<DataEnum<*>> }
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

    private fun buildDictName(clazz: Class<DataEnum<*>>): String = StrUtil.lowerFirst(clazz.simpleName)
}