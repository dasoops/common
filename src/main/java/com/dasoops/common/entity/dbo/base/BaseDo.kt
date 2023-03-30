package com.dasoops.common.entity.dbo.base

import cn.hutool.core.date.DateUtil
import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableLogic
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
    open var rowId: Long? = null,

    /**
     * 逻辑删除(true为删除)
     */
    @TableField(fill = FieldFill.INSERT)
    open var isDelete: DbBooleanEnum? = null,

    /**
     * 创建用户(通常为Qid)
     */
    @TableField(fill = FieldFill.INSERT)
    open var createUser: Long? = null,

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    open var createTime: Date? = null,

    /**
     * 更新用户(通常为Qid)
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    open var updateUser: Long? = null,

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    open var updateTime: Date? = null,
) : IDo