package com.dasoops.common.json.jackson

import com.dasoops.common.json.core.IJson
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JsonNode


fun <T> IJson.parse(jsonStr: String, typeReference: TypeReference<T>): T {
    return Jackson.INSTANCE.parse(jsonStr, typeReference)
}

fun IJson.parseNode(jsonStr: String): JsonNode {
    return Jackson.INSTANCE.parseNode(jsonStr)
}