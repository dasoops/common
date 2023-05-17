package com.dasoops.common.db.ktorm

import org.ktorm.database.Database
import javax.sql.DataSource

/**
 * ktorm全局
 * @author DasoopsNicole@Gmail.com
 * @date 2023/05/17
 */
object KtormGlobal {
    /**
     * 默认数据源
     */
    lateinit var defaultDataSource: DataSource

    /**
     * 默认
     */
    lateinit var default: Database
}