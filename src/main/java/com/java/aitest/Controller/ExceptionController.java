package com.java.aitest.Controller;

import com.java.aitest.Vo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Winrisef
 * @see <a href="https://github.com/WinriseF">https://github.com/WinriseF</a>
 * date 2025/6/26
 * description:
 */
@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(RuntimeException.class)
    public Result<String> handelException(RuntimeException e) {
        return Result.fail(e.getMessage());
    }
}
