package com.dasoops.common.db.ktorm

import org.ktorm.database.Database
import org.ktorm.database.SqlDialect
import org.ktorm.database.detectDialectImplementation
import org.springframework.context.annotation.Bean
import javax.sql.DataSource

/**
 * ktorm基本配置
 * @author DasoopsNicole@Gmail.com
 * @date 2023-05-04
 */
abstract class BaseKtormConfiguration(val dialect: SqlDialect = detectDialectImplementation()) : AutoFill {
    @Bean
    open fun database(dataSource: DataSource): Database {
        return Database.connectWithSpringSupport(
            dataSource = dataSource,
            dialect = dialect
        )
    }
}