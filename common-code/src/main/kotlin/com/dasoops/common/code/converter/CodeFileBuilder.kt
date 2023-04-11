package com.dasoops.common.code.converter

import com.dasoops.common.code.entity.CodeFileZip
import com.dasoops.common.code.entity.CodeFileZipInfo
import io.swagger.v3.oas.models.OpenAPI

/**
 * OpenApi2代码文件实体类转换器
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/07
 * @see [CodeFileBuilder]
 */
class CodeFileBuilder(
    val source: OpenAPI,
    val another: String,
    val axiosModelName: String,
    val excludeRequestPrefix: String?,
    val requestFileBaseImport: String?,
    val entityFileBaseImport: String?,
    val requestFileBasePath: String,
    val entityFileBasePath: String,
) {

    fun build(): CodeFileZip {
        val finalEntityFileBasePath = entityFileBasePath.removeSuffix("/")
        val finalRequestFileBasePath = requestFileBasePath.removeSuffix("/")
        val entityList = EntityListBuilder(
            source = source,
            entityFileBasePath = finalEntityFileBasePath.removeSuffix("/")
        ).build()
        return CodeFileZip(
            info = CodeFileZipInfo(
                another = another,
                axiosModelName = axiosModelName,
                requestFileBaseImport = requestFileBaseImport,
                entityFileBaseImport = entityFileBaseImport,
            ),
            entityFileList = EntityCodeFileBuilder(
                entityList = entityList,
                entityFileBasePath = finalEntityFileBasePath
            ).build(),
            requestFileList = RequestCodeFileBuilder(
                source = source,
                entityList = entityList,
                requestFileBasePath = finalRequestFileBasePath,
                excludeRequestPrefix = excludeRequestPrefix,
            ).build()
        )
    }
}
