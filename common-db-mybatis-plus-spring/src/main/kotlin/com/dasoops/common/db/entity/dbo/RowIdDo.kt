package com.dasoops.common.db.entity.dbo

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.dasoops.common.core.IDo

abstract class RowIdDo(
        /**
         * 主键id
         */
        @TableId(value = "ROW_ID", type = IdType.AUTO)
        open var rowId: Long? = null
) : IDo