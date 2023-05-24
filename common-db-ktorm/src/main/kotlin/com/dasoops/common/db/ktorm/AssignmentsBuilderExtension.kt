package com.dasoops.common.db.ktorm

import cn.hutool.core.date.LocalDateTimeUtil
import org.ktorm.dsl.AssignmentsBuilder

fun <E : DasEntity<E>, T : DasTable<E>> AssignmentsBuilder.baseUpdate(it: T) {
    val userId = KtormRunner.INSTANCE.getUserId()
    set(it.updateTime, LocalDateTimeUtil.now())
    set(it.updateUser, userId)
}

fun <E : DasEntity<E>, T : DasTable<E>> AssignmentsBuilder.baseSave(it: T) {
    val userId = KtormRunner.INSTANCE.getUserId()
    set(it.isDelete, false)
    set(it.createTime, LocalDateTimeUtil.now())
    set(it.createUser, userId)
    set(it.updateTime, LocalDateTimeUtil.now())
    set(it.updateUser, userId)
}