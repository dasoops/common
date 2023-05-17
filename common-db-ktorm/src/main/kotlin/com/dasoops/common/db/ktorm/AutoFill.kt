package com.dasoops.common.db.ktorm

interface AutoFill {
    /**
     * 获取当前用户id
     * @return [Long]
     */
    fun getUserId(): Long
}