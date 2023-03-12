package com.dasoops.common.cache.v2.builder.dsl

import com.dasoops.common.cache.v2.base.CacheOrFactory
import com.dasoops.common.cache.v2.builder.HashFactoryBuilder
import com.dasoops.common.cache.v2.builder.ListFactoryBuilder
import com.dasoops.common.cache.v2.builder.ValueFactoryBuilder
import com.dasoops.common.cache.v2.base.CacheFactory
import com.dasoops.common.cache.v2.factory.CacheFactoryImpl
import org.springframework.data.redis.core.StringRedisTemplate

class GroupCacheBuilderDsl internal constructor(val redis: StringRedisTemplate) {
    inline fun <reified Entity : Any> list(keyStr: String, entityClass: Class<Entity> = Entity::class.java): ListFactoryBuilder<Entity> {
        return ListFactoryBuilder(redis, keyStr, entityClass)
    }

    inline fun <reified Entity : Any> value(keyStr: String, entityClass: Class<Entity> = Entity::class.java): ValueFactoryBuilder<Entity> {
        return ValueFactoryBuilder(redis, keyStr, entityClass)
    }

    inline fun <reified HK : Any, reified HV : Any> hash(keyStr: String, keyClass: Class<HK> = HK::class.java, valueClass: Class<HV> = HV::class.java): HashFactoryBuilder<HK, HV> {
        return HashFactoryBuilder(redis, keyStr, keyClass, valueClass)
    }

    inline fun <reified Key : Any, InnerKey : Any, Inner : CacheOrFactory> CacheFactory<InnerKey, Inner>.getBy(keyClass: Class<Key>): CacheFactory<Key, CacheFactory<InnerKey, Inner>> {
        return CacheFactoryImpl(this)
    }
}