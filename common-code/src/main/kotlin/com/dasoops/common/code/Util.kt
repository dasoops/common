package com.dasoops.common.code

import cn.hutool.core.util.StrUtil

/**
 * 文件路径和名字工具
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/10
 */
object CodeFileUtil {

    fun filePath(group: String, basePath: String) = basePath + StrUtil.addPrefixIfNot(group.removeSuffix(fileName(group)), "/")

    fun fileName(group: String) = group.substringAfterLast("/")

    fun importPath(group: String, entityFileBasePath: String) = "@" + filePath(group, entityFileBasePath) + fileName(group)
}