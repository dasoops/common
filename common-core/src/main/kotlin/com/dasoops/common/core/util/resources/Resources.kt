package com.dasoops.common.core.util.resources

import cn.hutool.core.lang.ClassScanner
import com.dasoops.common.core.util.trace
import org.slf4j.LoggerFactory

/**
 * 资源扫描类
 * TODO(瞅瞅 org.springframework.core.type.classreading.SimpleAnnotationMetadataReadingVisitor)学习学习
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/30
 */
object Resources {
    private val logger = LoggerFactory.getLogger(javaClass)

    /**
     * 扫描
     * @param [basePath] 基本路径
     * @return [Collection<Class<*>>]
     */
    fun scan(basePath: Collection<String>): Collection<Class<*>> {
        return scan(Thread.currentThread().contextClassLoader, basePath)
    }

    /**
     * 扫描
     * @param [basePath] 基本路径
     * @return [Collection<Class<*>>]
     */
    fun scan(basePath: String): Collection<Class<*>> {
        return scan(Thread.currentThread().contextClassLoader, basePath)
    }

    /**
     * 扫描
     * @param [classLoader] 类加载器
     * @param [basePath] 基本路径
     * @return [Collection<Class<*>>]
     */
    fun scan(
            classLoader: ClassLoader,
            basePath: Collection<String>,
    ): Collection<Class<*>> {
        return basePath.flatMap { scan(classLoader, it) }
    }

    /**
     * 扫描路径保存到set
     * @param [classLoader] 类加载器
     * @param [basePath] 基本路径
     * @return [Pair<Set<Class<*>>, Set<Class<*>>>] [Pair<entitySet,RequestSet>]
     */
    private fun scan(
            classLoader: ClassLoader,
            basePath: String,
    ): Collection<Class<*>> {
        //根据url类型获取资源
        return ClassScanner(basePath).run {
            setClassLoader(classLoader)
            setIgnoreLoadError(true)
            scan()
        }.apply { logger.trace("Resources loadClass failed for ${joinToString(",")}") }.filter { !it.isAnnotationPresent(IgnoreResourcesScan::class.java) }
    }

}