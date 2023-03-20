package com.dasoops.common.config

import cn.hutool.core.util.StrUtil
import com.dasoops.common.cache.v2.factory.CommonOperations.log
import com.dasoops.common.config.dict.*
import com.dasoops.common.entity.enums.database.ApiEnum
import com.google.common.base.CaseFormat
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import java.io.File
import java.net.URL
import java.util.*
import java.util.jar.JarFile

/**
 * 基本枚举字典配置
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/20
 * @see [BaseEnumDictionaryAutoConfiguration]
 */
@Import(DictionaryController::class)
open class BaseEnumDictionaryAutoConfiguration(val basePath: String) {
    val classList: Collection<Class<ApiEnum>>

    init {
        //构建路径
        val basePath = buildBasePath(basePath)
        log.info("构建路径,path: $basePath")

        //扫描所有Class,获取实体类和接口
        val classLoader = this::class.java.classLoader
        classList = scanClassSaveToSet(classLoader, basePath)
    }

    @Bean
    open fun buildOnlyValueDictData(): OnlyValueDictData {
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

    /**
     * 扫描路径保存到set
     * @param [classLoader] 类加载器
     * @param [basePath] 基本路径
     * @return [Pair<Set<Class<*>>, Set<Class<*>>>] [Pair<entitySet,RequestSet>]
     */
    private fun scanClassSaveToSet(
        classLoader: ClassLoader,
        basePath: String
    ): Collection<Class<ApiEnum>> {
        val classSet = HashSet<Class<*>>()

        //根据url类型获取资源
        val resources: Enumeration<URL> = classLoader.getResources(basePath)
        while (resources.hasMoreElements()) {
            val url = resources.nextElement()
            if (url.protocol == "file") {
                val file = File(url.file)
                scanClassSaveToSet(classLoader, basePath, file, classSet)
            } else if (url.protocol == "jar") {
                //去除前缀和后面的路径
                val filePath = with(url.file) {
                    this.substring(6, this.lastIndexOf("!"))
                }
                val file = File(filePath)
                scanJarSaveToSet(classLoader, basePath, file, classSet)
            }
        }


        return classSet.mapNotNull {
            if (ApiEnum::class.java.isAssignableFrom(it) && it.isEnum) {
                it as Class<ApiEnum>
            } else {
                null
            }
        }
    }

    /**
     * 扫描jar保存到集合
     * @param [classLoader] 类加载器
     * @param [basePath] 基本路径
     * @param [file] 文件
     * @param [classSet] 类集合
     */
    private fun scanJarSaveToSet(
        classLoader: ClassLoader,
        basePath: String,
        file: File,
        classSet: MutableSet<Class<*>>
    ) {
        val jarEntries = JarFile(file).entries()
        jarEntries.toList()
            //包名匹配 .class后缀过滤 目录过滤
            .filter {
                with(it.name) {
                    startsWith(basePath) && endsWith(".class") && !it.isDirectory
                }
            }
            //转为可使用的类路径
            .map { it.name.replace("/", ".").removeSuffix(".class") }
            //添加到集合
            .forEach {
                try {
                    classSet.add(Class.forName(it, false, classLoader))
                } catch (e: ClassNotFoundException) {
                    log.error("class not found: $it")
                }
            }

    }


    /**
     * 扫描路径保存来设置
     * @param [classLoader] 类加载器
     * @param [basePath] 基本路径
     * @param [file] 文件
     * @param [classSet] 类集合
     */
    private fun scanClassSaveToSet(
        classLoader: ClassLoader,
        basePath: String,
        file: File,
        classSet: MutableSet<Class<*>>
    ) {
        //log.debug("scan: $basePath")
        //目录递归
        if (file.exists() && file.isDirectory) {
            Arrays.stream(file.list() ?: return).forEach {
                scanClassSaveToSet(classLoader, "$basePath/$it", File("${file.path}/$it"), classSet)
            }
            return
        }
        classSet.add(loadClass(classLoader, basePath) ?: return)
    }


    /**
     * 构建基本路径
     * @param [basePath] 基本路径
     * @return [String]
     */
    private fun buildBasePath(basePath: String): String {
        val path = basePath.apply { removeSuffix("/") }
        return path.replace(".", "/")
    }

    /**
     * 加载类
     * @param [classLoader] 类加载器
     * @param [path] 路径
     * @return [Class<*>?]
     */
    private fun loadClass(classLoader: ClassLoader, path: String): Class<*>? {
        //焯水
        val realPath = with(path) {
            this.replace("/", ".")
                .removeSuffix(".class")
        }

        //下锅
        return try {
            classLoader.loadClass(realPath)
        } catch (e: Exception) {
            log.error("error: ", e)
            null
        }
    }

    private fun buildNodeKey(it: ApiEnum): String =
        CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, it.toString())

    private fun buildDictName(clazz: Class<ApiEnum>): String = StrUtil.lowerFirst(clazz.simpleName)
}