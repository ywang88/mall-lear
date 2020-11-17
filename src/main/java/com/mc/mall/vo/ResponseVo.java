package com.mc.mall.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mc.mall.Enum.ResponseEnum;
import lombok.Data;
import org.springframework.validation.BindingResult;

/**
 * Author wangyi
 * Create 2020-11-17 21:51
 * ClassName ResponseVo
 * Version 1.0
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseVo<T> {
    private Integer status;
    private String msg;
    private T data;

    public ResponseVo(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResponseVo(Integer status, T data) {
        this.status = status;
        this.data = data;
    }

    public static <T> ResponseVo<T> successByMsg(String msg) {
        return new ResponseVo<>(ResponseEnum.SUCCESS.getCode(), msg);
    }

    public static <T> ResponseVo<T> success(T data) {
        return new ResponseVo<>(ResponseEnum.SUCCESS.getCode(), data);
    }

    public static <T> ResponseVo<T> success() {
        return new ResponseVo<>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getDesc());
    }

    public static <T> ResponseVo<T> error(ResponseEnum responseEnum) {
        return new ResponseVo<>(responseEnum.getCode(), responseEnum.getDesc());
    }

    public static <T> ResponseVo<T> error(ResponseEnum responseEnum, String msg) {
        return new ResponseVo<>(responseEnum.getCode(), responseEnum.getDesc());
    }

    public static <T> ResponseVo<T> error(ResponseEnum responseEnum, BindingResult bindingResult) {
        return new ResponseVo<>(responseEnum.getCode(), bindingResult.getFieldError().getField() + " " + bindingResult.getFieldError().getDefaultMessage());
    }

}
