package com.dasoops.common.code.ts.style

object Anno {

    fun `for`(description: String?, prefix: String? = "") = """
        |$prefix/**
        |$prefix * ${description ?: "no description"}
        |$prefix */
    """.trimMargin()
}