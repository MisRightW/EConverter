package com.fengling.ecserver.base;

public enum ResultEnum implements IResult{
    SUCCESS(200, "接口调用成功"),
    VALIDATE_FAILED(202, "参数校验失败"),
    COMMON_FAILED(203, "接口调用失败"),
    FORBIDDEN(204, "没有权限访问资源");

    private Integer code;
    private String message;

    ResultEnum() { }

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
