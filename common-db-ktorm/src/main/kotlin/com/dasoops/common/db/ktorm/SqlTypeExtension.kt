package com.dasoops.common.db.ktorm

import com.dasoops.common.json.core.dataenum.DataEnum
import com.dasoops.common.json.core.dataenum.IntDataEnum
import org.ktorm.schema.BaseTable
import org.ktorm.schema.Column
import org.ktorm.schema.int

inline fun <reified T : IntDataEnum> BaseTable<*>.dataEnum(name: String): Column<T> {
    return int(name).transform({ DataEnum.getBy(T::class.java, it)!! }, { it.data })
}