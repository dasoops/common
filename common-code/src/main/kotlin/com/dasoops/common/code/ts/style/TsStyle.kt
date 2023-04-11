package com.dasoops.common.code.ts.style

import cn.hutool.core.text.StrPool
import com.dasoops.common.code.entity.CodeFileZipInfo
import com.dasoops.common.code.entity.EntityCodeFile
import com.dasoops.common.code.entity.RequestCodeFile

/**
 * ts风格代码模板池
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/10
 */
object TsStyle {

    fun requestFile(requestCodeFile: RequestCodeFile, codeFileZipInfo: CodeFileZipInfo): String {
        val (another, axiosModelName, requestFileBaseImport, _) = codeFileZipInfo
        return arrayOf(
            //文件注释
            FileHeaderAnno.`for`(requestCodeFile.fileName, another, true),
            //import
            Import.`for`(requestCodeFile.importList, requestFileBaseImport),
            //请求集合
            *requestCodeFile.requestList.map {
                RequestMethod.`for`(it, axiosModelName)
            }.toTypedArray()
        ).joinToString(StrPool.CRLF)
    }

    fun entityFile(entityCodeFile: EntityCodeFile, codeFileZipInfo: CodeFileZipInfo): String {
        val (another, _, _, entityFileBaseImport) = codeFileZipInfo
        return arrayOf(
            //文件注释
            FileHeaderAnno.`for`(entityCodeFile.fileName, another, false),
            //import
            Import.`for`(entityCodeFile.importList, entityFileBaseImport),
            //实体类集合
            *entityCodeFile.entityList.map {
                EntityDefined.`for`(it)
            }.toTypedArray()
        ).joinToString(StrPool.CRLF)
    }
}