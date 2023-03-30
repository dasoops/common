package com.dasoops.common.cache.v2.builder

import com.dasoops.common.cache.v2.CacheManager
import com.dasoops.common.cache.v2.base.CacheTemplate
import com.dasoops.common.cache.v2.factory.HashFactory
import com.dasoops.common.util.convert.DefaultToStringConvert
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