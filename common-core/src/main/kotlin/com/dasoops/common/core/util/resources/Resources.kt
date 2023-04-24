package com.dasoops.common.core.util.resources

import cn.hutool.core.util.StrUtil
import org.slf4j.LoggerFactory
import java.io.File
import java.net.URL
import java.util.*
import java.util.jar.JarFile

/**
 * 资源类
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
    fun scan(vararg basePath: String): Collection<Class<*>> {
        return scan(this::class.java.classLoader, *basePath)
    }

    /**
     * 扫描
     * @param [classLoader] 类加载器
     * @param [basePath] 基本路径
     * @return [Collection<Class<*>>]
     */
    fun scan(
        classLoader: ClassLoader,
        vararg basePath: String
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
        basePath: String
    ): Collection<Class<*>> {
        val classSet = HashSet<Class<*>>()
        val finalPath = buildBasePath(basePath)

        //根据url类型获取资源
        val resources: Enumeration<URL> = classLoader.getResources(finalPath)
        while (resources.hasMoreElements()) {
            val url = resources.nextElement()
            if (url.protocol == "file") {
                val file = File(url.file)
                scan(classLoader, finalPath, file, classSet)
            } else if (url.protocol == "jar") {
                //去除前缀和后面的路径
                val file = File(buildFilePath(url))
                scanJarSaveToSet(classLoader, finalPath, file, classSet)
            }
        }

        return classSet.filter { !it.isAnnotationPresent(IgnoreResourcesScan::class.java) }
    }

    private fun buildFilePath(url: URL): String {
        var str = StrUtil.removePrefix(url.toString(), "jar:file:/")
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
        classSet: MutableSet<Class<*>>
    ) {
        val jarEntries = JarFile(file).entries()
        jarEntries.toList()
            //包名匹配 .class后缀过滤 目录过滤
            .filter {
                with(it.name) {
                    startsWith("WEB-INF/classes/$basePath") && endsWith(".class") && !it.isDirectory
                }
            }
            //转为可使用的类路径
            .map { it.name.removePrefix("WEB-INF/classes/").replace("/", ".").removeSuffix(".class") }
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
    private fun scan(
        classLoader: ClassLoader,
        basePath: String,
        file: File,
        classSet: MutableSet<Class<*>>
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

}