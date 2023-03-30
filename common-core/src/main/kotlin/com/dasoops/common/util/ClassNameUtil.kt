package com.dasoops.common.util

/**
 * @title ClassNameUtil
 * @classPath com.dasoops.common.util.ClassNameUtil
 * @author DasoopsNicole@Gmail.com
 * @date 2023/01/07
 * @version 1.0.0
 * @description 类名称工具
 */
object ClassNameUtil {
    fun removeCglibSuffix(classPath: String): String {
//        com.dasoops.dasserver.plugin.reboot.RebootPlugin$$EnhancerBySpringCGLIB$$17aa347c
        val cglibKeyword = "$$"
        if (!classPath.contains(cglibKeyword)) {
            return classPath
        }
        val index = classPath.indexOf("$$")
        return classPath.substring(0, index)
    }
}