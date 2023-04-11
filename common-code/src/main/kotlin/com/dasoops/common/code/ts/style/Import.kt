package com.dasoops.common.code.ts.style

import cn.hutool.core.text.StrPool
import com.dasoops.common.code.entity.Entity

/**
 * 导入
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/10
 */
object Import {
    fun `for`(importList: List<Entity>, baseImport: String?): String {
        return (baseImport ?: "") + importList.groupBy { it.importPath }.map { entry ->
            if (entry.value.size > 1) {
                return@map """
                    |import {${entry.value.joinToString(",") { it.name }}} from '${entry.key}';
                """.trimMargin()
            }
            return@map "import ${entry.value.first().name} from '${entry.key}';"
        }.joinToString(StrPool.CRLF) + StrPool.CRLF
    }
}