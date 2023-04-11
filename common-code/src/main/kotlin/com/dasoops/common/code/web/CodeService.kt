package com.dasoops.common.code.web

import com.dasoops.common.code.entity.CodeFileZip
import com.dasoops.common.code.entity.CodeParam

/**
 * 代码生成服务
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/06
 * @see [CodeService]
 */
interface CodeService {
    /**
     * 获取代码文件实体类
     * @param [param] param
     * @return [CodeFileZip]
     */
    fun getCodeFile(param: CodeParam): CodeFileZip
}
