package com.dasoops.common.util.dbcolumn;

import com.dasoops.common.entity.enums.database.IDbColumnEnum;
import com.dasoops.common.exception.DbColumnExceptionEnum;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

import java.io.IOException;

/**
 * @author DasoopsNicole@Gmail.com
 * @version 1.0.0
 * @title IDbColumnEnumDeserializer
 * @classPath com.dasoops.common.util.entity.IDbColumnEnumDeserializer
 * @date 2023/02/18
 * @description 数据库字段枚举反序列化器
 * @see JsonDeserializer
 * @see ContextualDeserializer
 */
public class IDbColumnEnumDeserializer extends JsonDeserializer<IDbColumnEnum> implements ContextualDeserializer {

    /**
     * 记录枚举字段的类，用于获取其定义的所有枚举值
     */
    private Class<? extends IDbColumnEnum> propertyClass;

    public IDbColumnEnumDeserializer() {

    }

    public IDbColumnEnumDeserializer(Class<? extends IDbColumnEnum> propertyClass) {
        this.propertyClass = propertyClass;
    }

    @Override
    public IDbColumnEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        IDbColumnEnum dbColumnEnum = DbColumnUtil.INSTANCE.getBy(propertyClass, Integer.parseInt(p.getText()));
        if (dbColumnEnum == null) {
            throw DbColumnExceptionEnum.UNDEFINEND_VALUE.getException();
        }
        return dbColumnEnum;
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) {
        // 获取枚举字段的类型Class
        return new IDbColumnEnumDeserializer((Class<? extends IDbColumnEnum>) property.getType().getRawClass());
    }

}