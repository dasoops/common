package com.dasoops.common.config

import cn.hutool.core.util.StrUtil
import com.dasoops.common.config.dict.*
import com.dasoops.common.entity.enums.database.ApiEnum
import com.dasoops.common.util.Resources
import com.google.common.base.CaseFormat
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import java.util.*

/**
 * 基本枚举字典配置
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/20
 * @see [BaseEnumDictionaryAutoConfiguration]
 */
@Import(DictionaryController::class)
open class BaseEnumDictionaryAutoConfiguration(vararg basePath: String) {

    private val log = LoggerFactory.getLogger(javaClass)
    val classList: Collection<Class<ApiEnum>>

    init {
        classList = Resources.scan(javaClass.classLoader, *basePath)
            .filter { ApiEnum::class.java.isAssignableFrom(it) && it.isEnum }
            .map { it as Class<ApiEnum> }
    }

    @Bean
    open fun buildOnlyValueDictData(): OnlyValueDictData {
        log.info("初始化字典项")
        return classList.associate { clazz ->
            buildDictName(clazz) to clazz.enumConstants.associate {
                val key = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, it.toString())
                key to it.dbValue
            }.toMap(OnlyValueDictNode())
        }.toMap(OnlyValueDictData())
    }

    @Bean
    open fun buildEasyDictData(): EasyDictData {
        val easyDictData = EasyDictData()
        classList.forEach { clazz ->
            easyDictData[buildDictName(clazz)] =
                clazz.enumConstants.associate {
                    val key = buildNodeKey(it)
                    key to DictInner(
                        value = it.dbValue,
                        key = key,
                        data = it.data,
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
                    value = it.dbValue,
                    key = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, it.toString()),
                    data = it.data,
                )
            }.toList())
        }.toCollection(DictData())
    }

    private fun buildNodeKey(it: ApiEnum): String =
        CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, it.toString())

    private fun buildDictName(clazz: Class<ApiEnum>): String = StrUtil.lowerFirst(clazz.simpleName)
}