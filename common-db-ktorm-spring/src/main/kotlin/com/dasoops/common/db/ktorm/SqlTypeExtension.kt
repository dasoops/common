package com.dasoops.common.db.ktorm

import com.dasoops.common.core.entity.dataenum.DataEnum
import com.dasoops.common.core.util.DataEnumUtil
import org.ktorm.schema.BaseTable
import org.ktorm.schema.Column
import org.ktorm.schema.int

inline fun <reified T : DataEnum> BaseTable<*>.dataEnum(name: String): Column<T> {
    return int(name).transform({ DataEnumUtil.getBy(T::class.java, it)!! }, { it.data })
}