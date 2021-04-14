package com.ergou.document.myxcption;

import com.ergou.document.enums.ResultCodeEnum;
import com.ergou.document.pojo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: luoergou
 * @description:
 * @date: 2021-04-14 21:48
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandler {
    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public ResultVO handlerException(Exception e) {
        log.error("[系统异常] {}", ExceptionUtils.getStackTrace(e));
        return ResultVO.failure(ResultCodeEnum.SYSTEM_EXCEPTION.getCode(), e.getMessage());
    }


    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResultVO handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ResultVO.failure(-2, "非法的请求方式");
    }

    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {MyException.class})
    public ResultVO handlerAdminException(MyException e) {
        return ResultVO.failure(e.getCode(), e.getMessage());
    }


    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(value = BindException.class)
    public ResultVO handlerBindException(BindException e) {
        log.error("[验证错误] {}", e.getMessage());
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        StringBuilder errorMsg = new StringBuilder();
        for (ObjectError objectError : allErrors) {
            errorMsg.append(objectError.getDefaultMessage());
            errorMsg.append(",");
        }
        return ResultVO.failure(ResultCodeEnum.DATA_EXCEPTION.getCode(), errorMsg.toString());
    }

    /**
     * 前端传入值spring 无法转换时会报此异常  例 后端接受为 long 前端传入 ""
     *
     * @param exception
     * @param request
     * @return
     */
    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResultVO methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException exception, HttpServletRequest request) {
        String parameterName = exception.getParameter().getParameterName();
        Class<?> parameterType = exception.getParameter().getParameterType();
        Object value = exception.getValue();
        String errorMsg;
        if (value == null || StringUtils.isBlank(value.toString())) {
            errorMsg = "缺少必要的参数, 期望参数:" + parameterName
                    + ", 期望类型:" + parameterType.getName();
        } else {
            errorMsg = "表单参数类型错误, 参数:" + parameterName
                    + ", 期望类型:" + parameterType.getName()
                    + ", 提交类型:" + value.toString();
        }
        return ResultVO.failure(ResultCodeEnum.DATA_EXCEPTION.getCode(), errorMsg);
    }

    /**
     * RequestParam 注解 required为true 且前端未传值时会进入此异常处理器
     *
     * @param exception
     * @param request
     * @return
     */
    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResultVO missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException exception, HttpServletRequest request) {
        String errorMsg = "缺少必要的参数, 期望参数:" + exception.getParameterName()
                + ", 期望类型:" + exception.getParameterType();
        return ResultVO.failure(ResultCodeEnum.DATA_EXCEPTION.getCode(), errorMsg);
    }
}
