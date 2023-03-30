package com.dasoops.common.util

import cn.hutool.core.annotation.AnnotationUtil
import org.springframework.stereotype.Component
import java.lang.reflect.Modifier

/**
 * spring工具类拓展
 *
 * @author rongdi
 * @date 2021-03-06
 * @blog [...](https://www.cnblogs.com/rongdi)
 */
object SpringUtil : cn.hutool.extra.spring.SpringUtil() {
    fun isSpringBeanClass(cla: Class<*>): Boolean {
        //如果为空或是接口或是抽象类直接返回false
        if (cla.isInterface || Modifier.isAbstract(cla.modifiers)) {
            return false
        }
        var targetClass: Class<*>? = cla
        while (targetClass != null) {
            //如果包含spring注解则返回true
            if (AnnotationUtil.hasAnnotation(cla, Component::class.java)) {
                return true
            }
            targetClass = targetClass.superclass
        }
        return false
    }

}