package com.dasoops.common.db.ktorm

import cn.hutool.db.ds.DSFactory
import cn.hutool.setting.Setting
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
abstract class BaseKtormConfiguration(
    val dialect: SqlDialect = detectDialectImplementation(),
    val logger: Logger = Slf4jLoggerAdapter("ktorm-log")
) : AutoFill {

    open fun createDefault(
        url: String,
        username: String,
        password: String,
        driverClassName: String
    ): Database {
        KtormGlobal.defaultDataSource = DSFactory.create(
            Setting.create()
                .set("url", url)
                .set("username", username)
                .set("password", password)
                .set("driverClassName", driverClassName)
        ).dataSource

        KtormGlobal.default = Database.connectWithSpringSupport(
            dataSource = KtormGlobal.defaultDataSource,
            dialect = dialect,
            logger = logger
        )

        return KtormGlobal.default
    }
}