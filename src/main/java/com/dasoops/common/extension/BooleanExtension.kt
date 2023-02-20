package com.dasoops.common.extension

inline fun Boolean.ifTrue(function: () -> Any?): Any? {
    if (this) {
        return function.invoke()
    }
    return null
}

inline fun Boolean.ifFalse(function: () -> Any?): Any? {
    if (!this) {
        return function.invoke()
    }
    return null
}