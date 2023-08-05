package com.dasoops.common.core.util.resources

import cn.hutool.core.util.StrUtil
import com.dasoops.common.core.util.trace
import org.slf4j.LoggerFactory
import java.io.File
import java.net.URL
import java.util.*
import java.util.jar.JarFile

/**
 * 资源扫描类
 * TODO(瞅瞅 org.springframework.core.type.classreading.SimpleAnnotationMetadataReadingVisitor)学习学习
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/30
 */
object Resources {
    private val log = LoggerFactory.getLogger(javaClass)

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
        return basePath.map { scan(classLoader, it) }.flatten()
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
        val classSet = HashSet<Class<*>>()
        val finalPath = buildBasePath(basePath)

        //根据url类型获取资源
        classLoader.getResources(finalPath).toList().forEach {
            if (it.protocol == "file") {
                val file = File(it.file)
                scan(classLoader, finalPath, file, classSet)
            } else if (it.protocol == "jar") {
                //去除前缀和后面的路径
                val file = File(buildFilePath(it))
                scanJarSaveToSet(classLoader, finalPath, file, classSet)
            }
        }

        return classSet.filter { !it.isAnnotationPresent(IgnoreResourcesScan::class.java) }
    }

    private fun buildFilePath(url: URL): String {
        var str = StrUtil.removePrefix(url.toString(), "jar:file:")
        str = StrUtil.subBefore(str, "!", false)
        str.replace("\\", "/")
        return str
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
            classSet: MutableSet<Class<*>>,
    ) {

        JarFile(file).entries().toList()
                //包名匹配 .class后缀过滤 目录过滤
                .filter {
                    !it.isDirectory && it.name.removePrefix("BOOT-INF/classes/")
                            .startsWith(basePath) && it.name.endsWith(".class")
                }
                //转为可使用的类路径
                .map {
                    it.name
                            .removePrefix("BOOT-INF/classes/")
                            .removePrefix("META-INF/classes/")
                }.forEach {
                    //添加到集合
                    classSet.add(loadClass(classLoader, it) ?: return@forEach)
                }
    }


    /**
     * 扫描路径保存来设置
     * @param [classLoader] 类加载器
     * @param [basePath] 基本路径
     * @param [file] 文件
     * @param [classSet] 类集合
     */
    private fun scan(
            classLoader: ClassLoader,
            basePath: String,
            file: File,
            classSet: MutableSet<Class<*>>,
    ) {
        //log.debug("scan: $basePath")
        //目录递归
        if (file.exists() && file.isDirectory) {
            Arrays.stream(file.list() ?: return).forEach {
                scan(classLoader, "$basePath/$it", File("${file.path}/$it"), classSet)
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
                    .replace("\\", ".")
                    .removeSuffix(".class")
        }

        //下锅
        return try {
            Class.forName(realPath, false, classLoader)
        } catch (e: NoClassDefFoundError) {
            log.trace { "Resources.loadClass() catch NoClassDefFoundError : ${e.message}" }
            null
        } catch (e: ClassNotFoundException) {
            log.trace { "Resources.loadClass() catch ClassNotFoundException : ${e.message}" }
            null
        }
    }

}