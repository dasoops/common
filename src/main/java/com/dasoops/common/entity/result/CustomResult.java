package com.dasoops.common.entity.result;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;

/**
 * @title CustomResult
 * @classPath cn.qiaoml.bean.vo.result.CustomResult
 * @author DasoopsNicole@Gmail.com
 * @date 2022/12/01
 * @version 1.0.0
 * @description 自定义结果类
 * @see BaseResult
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "自定义结果类", description = "自定义结果类")
public class CustomResult extends HashMap<String, Object> implements IResult {

    @Override
    public Integer getCode() {
        Object codeObj = super.get("code");
        if (codeObj instanceof Integer) {
            return (Integer) codeObj;
        }
        return null;
    }

    @Override
    public void setCode(Integer code) {
        super.put("code", code);
    }

    @Override
    public String getMsg() {
        Object msgObj = super.get("msg");
        if (msgObj instanceof String) {
            return (String) msgObj;
        }
        return null;
    }

    @Override
    public void setMsg(String msg) {
        super.put("msg", msg);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final CustomResult customResult = new CustomResult();

        public Builder success() {
            return this.code(200).msg("请求成功");
        }

        public Builder code(Integer code) {
            customResult.put("code", code);
            return this;
        }

        public Builder msg(String msg) {
            customResult.put("msg", msg);
            return this;
        }

        public Builder data(Object data) {
            customResult.put("data", data);
            return this;
        }

        public Builder page(Integer current, Integer size, Integer total) {
            return this.current(current).size(size).total(total);
        }

        public Builder current(Integer current) {
            customResult.put("current", current);
            return this;
        }

        public Builder size(Integer size) {
            customResult.put("size", size);
            return this;
        }

        public Builder total(Integer total) {
            customResult.put("total", total);
            return this;
        }

        public Builder param(String key, Object value) {
            customResult.put(key, value);
            return this;
        }

        public CustomResult build() {
            return customResult;
        }

    }

}
