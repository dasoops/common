package com.dasoops.common.db.ktorm

import cn.hutool.db.ds.DSFactory
import cn.hutool.db.ds.pooled.DbConfig
import cn.hutool.setting.Setting
import com.alibaba.druid.pool.DruidDataSourceFactory
import org.ktorm.database.Database
import org.ktorm.database.SqlDialect
import org.ktorm.database.detectDialectImplementation
import org.ktorm.logging.Logger
import org.ktorm.logging.Slf4jLoggerAdapter

/**
 * ktorm基本配置
 * @author DasoopsNicole@Gmail.com
 * @date 2023-05-04
 */
abstract class KtormRunner {
    fun init(
        dialect: SqlDialect = detectDialectImplementation(),
        logger: Logger = Slf4jLoggerAdapter("ktorm-log"),
        dbConfig: DbConfig,
    ) {
        KtormGlobal.defaultDataSource = DruidDataSourceFactory.createDataSource(
            Setting.create()
                .set("url", dbConfig.url)
                .set("username", dbConfig.user)
                .set("password", dbConfig.pass)
                .set("driverClassName", dbConfig.driver)
                .toProperties()
        )

        KtormGlobal.default = Database.connect(
            dataSource = KtormGlobal.defaultDataSource,
            dialect = dialect,
            logger = logger
        )
        KtormRunner.INSTANCE = this
    }

    abstract fun getUserId(): Long

    companion object {
        lateinit var INSTANCE: KtormRunner
    }
}