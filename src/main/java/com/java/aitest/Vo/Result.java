package com.java.aitest.Vo;

import lombok.Data;

/**
 * @author Winrisef
 * @see <a href="https://github.com/WinriseF">https://github.com/WinriseF</a>
 * date 2025/6/26
 * description:
 */
@Data
public class Result<T> {
    private Integer code; // 状态码
    private String message; // 响应消息
    private T data; // 响应数据

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Result<T> success(String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(200); // 成功状态码
        result.setData(data);
        result.setMessage(message);
        return result;
    }
    public static <T> Result<T> fail(String message) {
        Result<T> result = new Result<>();
        result.setCode(500); // 失败状态码
        result.setMessage(message);
        return result;
    }
}
