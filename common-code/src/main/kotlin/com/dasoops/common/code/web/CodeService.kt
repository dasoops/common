package com.dasoops.common.code.web

import com.dasoops.common.code.entity.CodeFile
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
     * @return [CodeFile]
     */
    fun getCodeFile(param: CodeParam): CodeFile
}
