package com.dasoops.common.db.ktorm

import org.ktorm.schema.Table
import org.ktorm.schema.boolean
import org.ktorm.schema.datetime
import org.ktorm.schema.long

/**
 * 表基类
 * 因为BaseTable有了又不想从命名空间里面翻,干脆随便起一个,后期可能会改?
 * @author DasoopsNicole@Gmail.com
 * @date 2023-05-04
 */
abstract class DasTable<E : DasEntity<E>>(tableName: String) : Table<E>(tableName) {
    /**
     * 主键id
     */
    val rowId = long("row_id").primaryKey().bindTo { it.rowId }

    /**
     * 逻辑删除
     */
    val isDelete = boolean("is_delete").bindTo { it.isDelete }

    /**
     * 创建时间
     */
    val createTime = datetime("create_time").bindTo { it.createTime }

    /**
     * 创建用户
     */
    val createUser = long("create_user").bindTo { it.createUser }

    /**
     * 更新时间
     */
    val updateTime = datetime("update_time").bindTo { it.updateTime }

    /**
     * 更新用户
     */
    val updateUser = long("update_user").bindTo { it.updateUser }
}