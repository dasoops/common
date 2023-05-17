package com.dasoops.common.core.util

/**
 * 类工具
 * @author DasoopsNicole@Gmail.com
 * @date 2023/05/17
 */
object ClassUtil {
    //cgLib动态代理类分隔符
    const val CGLIB_CLASS_SEPARATOR = "$$"

    /**
     * 获取用户类
     * from Spring ClassUtils
     * @param [clazz] clazz
     * @return [Class<*>?]
     */
    fun getUserClass(clazz: Class<*>): Class<*> {
        if (clazz.name.contains(CGLIB_CLASS_SEPARATOR)) {
            val superclass = clazz.superclass
            if (superclass != null && superclass != Any::class.java) {
                return superclass
            }
        }
        return clazz
    }

    /**
     * 获取用户类
     * from Spring ClassUtils
     * @param [clazz] clazz
     * @return [Class<*>?]
     */
    fun getUserClass(instance: Any): Class<*> {
        return getUserClass(instance.javaClass)
    }

}