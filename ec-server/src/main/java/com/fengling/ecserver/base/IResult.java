package com.fengling.ecserver.base;

/**
 * 统一响应结果接口
 * @author fengling
 */
public interface IResult {
    Integer getCode();
    String getMessage();
}
