package com.dasoops.common.db.entity.dbo

import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableLogic
import com.dasoops.common.core.IDo
import com.dasoops.common.json.core.dataenum.BooleanEnum
import org.apache.ibatis.type.JdbcType
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
        @TableId(value = "ROW_ID", type = IdType.AUTO)
        override var rowId: Long? = null,

        /**
         * 逻辑删除(true为删除)
         */
        @TableField(value = "IS_DELETE", fill = FieldFill.INSERT)
        @TableLogic
        open var isDelete: BooleanEnum? = null,

        /**
         * 创建用户(通常为Qid)
         */
        @TableField(value = "CREATE_USER", fill = FieldFill.INSERT)
        open var createUser: Long? = null,

        /**
         * 创建时间
         */
        @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT, jdbcType = JdbcType.TIMESTAMP)
        open var createTime: Date? = null,

        /**
         * 更新用户(通常为Qid)
         */
        @TableField(value = "UPDATE_USER", fill = FieldFill.INSERT_UPDATE)
        open var updateUser: Long? = null,

        /**
         * 更新时间
         */
        @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE, jdbcType = JdbcType.TIMESTAMP)
        open var updateTime: Date? = null,
) : RowIdDo(rowId)