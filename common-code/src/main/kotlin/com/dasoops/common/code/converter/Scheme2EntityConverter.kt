package com.dasoops.common.code.converter

import com.dasoops.common.code.entity.Entity
import io.swagger.models.Scheme
import org.springframework.core.convert.converter.Converter

class Scheme2EntityConverter : Converter<Scheme, Entity> {
    override fun convert(source: Scheme): Entity {
        TODO("Not yet implemented")
    }
}
