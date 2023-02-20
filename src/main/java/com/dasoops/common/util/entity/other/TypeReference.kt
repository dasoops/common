package com.dasoops.common.util.entity.other

import com.dasoops.common.util.entity.dto.JsonObj
import com.fasterxml.jackson.core.type.TypeReference

/**
 * @Title: ListTypeReference
 * @ClassPath com.dasoops.common.util.entity.other.ListTypeReference
 * @Author DasoopsNicole@Gmail.com
 * @Date 2023/02/18
 * @Version 1.0.0
 * @Description: ListTypeReference
 * @see [ListTypeReference]
 */
class ListTypeReference<T> : TypeReference<List<T>>()

/**
 * @Title: MapTypeReference
 * @ClassPath com.dasoops.common.util.entity.other.MapTypeReference
 * @Author DasoopsNicole@Gmail.com
 * @Date 2023/02/18
 * @Version 1.0.0
 * @Description: mapTypeReference
 * @see [MapTypeReference]
 */
class MapTypeReference<T> : TypeReference<Map<String, T>>()