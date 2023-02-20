package com.dasoops.common.util.entity;

import com.dasoops.common.entity.enums.base.IExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @title ExportExceptionEnum
 * @classPath com.dasoops.common.util.entity.ExportExceptionEnum
 * @author DasoopsNicole@Gmail.com
 * @date 2022/12/30
 * @version 1.0.0
 * @description 导出异常枚举(901xx)
 * @see IExceptionEnum
 */
@Getter
@AllArgsConstructor
public enum ExportExceptionEnum implements IExceptionEnum {

    /**
     * eem快速生成
     */
    DATA_NULL("导出数据为空"),
    URL_ENCODER_ERROR("导出部分url转码错误"),
    DOWNLOAD_ERROR("文件下载失败,请重试"),
    ;


    @Override
    public Integer getCode() {
        return 90100 + ordinal();
    }

    @Getter
    final String msg;


}
