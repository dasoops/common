package com.dasoops.common.entity.dbo.base

import cn.hutool.core.date.DateUtil
import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.dasoops.common.entity.enums.database.DbBooleanEnum
import java.util.*

/**
 * @Title: BasePo
 * @ClassPath com.dasoops.dasserver.cq.entity.po.BasePo
 * @Author DasoopsNicole@Gmail.com
 * @Date 2022/10/31
 * @Version 1.0.0
 * @Description: sqlDo基类
 */
abstract class BaseDo(
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    var rowId: Long,

    /**
     * 逻辑删除(true为删除)
     */
    @TableField(fill = FieldFill.INSERT)
    var isDelete: DbBooleanEnum,

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    var createTime: Date,

    /**
     * 创建用户(通常为Qid)
     */
    @TableField(fill = FieldFill.INSERT)
    var createUser: Long,

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updateTime: Date,

    /**
     * 更新用户(通常为Qid)
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updateUser: Long,
) : IDo