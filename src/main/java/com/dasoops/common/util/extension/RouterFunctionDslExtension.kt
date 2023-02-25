package com.dasoops.common.util.extension

import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.function.RouterFunctionDsl

fun RouterFunctionDsl.createView(path: String, to: String) {
    this.GET(path) { ok().build { _, _ -> ModelAndView(to) } }
}