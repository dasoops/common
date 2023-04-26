package com.dasoops.common.db.jpa

import com.dasoops.common.core.entity.dataenum.DataEnum

/**
 * 逻辑删除(Jpa独有,其他db一般直接使用BooleanEnum)
 * sbJpa 关键词搞个TRUE连tmd全限定类名也不读取了,搞了我一个下午
 * @author DasoopsNicole@Gmail.com
 * @date 2023-04-25
 */
enum class IsDelete(override val data: Int) : DataEnum {
    NO(20),
    YES(21),
    ;
}
