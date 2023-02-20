package com.dasoops.common.entity.dbo.base

import com.alibaba.fastjson2.annotation.JSONField
import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import java.io.Serializable
import java.util.*

/**
 * @Title: BasePo
 * @ClassPath com.dasoops.dasserver.cq.entity.po.BasePo
 * @Author DasoopsNicole@Gmail.com
 * @Date 2022/10/31
 * @Version 1.0.0
 * @Description: sqlDo基类
 */
abstract class BaseDo : IDo {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    var rowId: Long? = null

    /**
     * 逻辑删除(true为删除)
     */
    @JSONField(serialize = false)
    @TableField(fill = FieldFill.INSERT)
    var isDelete: Int? = null

    /**
     * 创建时间
     */
    @JSONField(serialize = false)
    @TableField(fill = FieldFill.INSERT)
    var createTime: Date? = null

    /**
     * 创建用户(通常为Qid)
     */
    @JSONField(serialize = false)
    @TableField(fill = FieldFill.INSERT)
    var createUser: Long? = null

    /**
     * 更新时间
     */
    @JSONField(serialize = false)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updateTime: Date? = null

    /**
     * 更新用户(通常为Qid)
     */
    @JSONField(serialize = false)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updateUser: Long? = null

}