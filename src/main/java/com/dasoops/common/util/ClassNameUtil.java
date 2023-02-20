package com.dasoops.common.util;

/**
 * @title ClassNameUtil
 * @classPath com.dasoops.common.util.ClassNameUtil
 * @author DasoopsNicole@Gmail.com
 * @date 2023/01/07
 * @version 1.0.0
 * @description 类名称工具
 */
public class ClassNameUtil {

    public static String removeCglibSuffix(String classPath) {
//        com.dasoops.dasserver.plugin.reboot.RebootPlugin$$EnhancerBySpringCGLIB$$17aa347c
        final String cglibKeyword = "$$";
        if (!classPath.contains(cglibKeyword)) {
            return classPath;
        }
        int index = classPath.indexOf("$$");
        String substring = classPath.substring(0, index);
        return substring;
    }

}
