package com.dasoops.common.core.util

import org.slf4j.Logger


fun Logger.info(func: () -> String) {
    if (isInfoEnabled) {
        info(func.invoke())
    }
}

fun Logger.debug(func: () -> String) {
    if (isDebugEnabled) {
        debug(func.invoke())
    }
}

fun Logger.warn(func: () -> String) {
    if (isWarnEnabled) {
        warn(func.invoke())
    }
}

fun Logger.error(func: () -> String) {
    if (isErrorEnabled) {
        error(func.invoke())
    }
}

fun Logger.trace(func: () -> String) {
    if (isTraceEnabled) {
        trace(func.invoke())
    }
}