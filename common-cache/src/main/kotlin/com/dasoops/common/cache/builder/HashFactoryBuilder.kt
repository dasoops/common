package com.dasoops.common.cache.builder

import com.dasoops.common.cache.CacheManager
import com.dasoops.common.cache.base.CacheTemplate
import com.dasoops.common.cache.factory.HashFactory
import com.dasoops.common.DefaultToStringConvert
import org.springframework.core.convert.converter.Converter

class HashFactoryBuilder<HK : Any, HV : Any>(
    private val redis: CacheTemplate,
    private val keyStr: String,
    private val hashKeyClass: Class<HK>,
    private val hashValueClass: Class<HV>,
) : FactoryBuilder {
    fun <Key : Any> getBy(keyClass: Class<Key>, keyConverter: Converter<Key, String> = DefaultToStringConvert()): HashFactory<Key, HK, HV> {
        return HashFactory(redis, keyStr, hashKeyClass, hashValueClass, keyConverter).apply { CacheManager.cacheList.add(this) }
    }
}