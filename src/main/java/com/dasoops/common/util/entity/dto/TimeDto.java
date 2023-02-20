package com.dasoops.common.util.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * @title TimeDto
 * @classPath com.dasoops.common.util.entity.TimeDto
 * @author DasoopsNicole@Gmail.com
 * @date 2023/01/10
 * @version 1.0.0
 * @description 时间dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeDto {

    /**
     * 数
     */
    private Long count;

    /**
     * 时间单位
     */
    private TimeUnit timeUnit;

}
