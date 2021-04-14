package com.ergou.document.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 返回的具体数据
     */
    private T data;

    public static <T> ResultVO<T> success(T data) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setData(data);
        return resultVO;
    }

    public static <T> ResultVO<T> success(T data,String msg) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setData(data);
        resultVO.setMsg(msg);
        return resultVO;
    }

    public static <T> ResultVO<T> success(T data,String msg,Integer code) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setData(data);
        resultVO.setMsg(msg);
        return resultVO;
    }

    public static ResultVO<String> success() {
        ResultVO<String> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        return resultVO;
    }

    public static ResultVO<String> failure(Integer errorCode, String message) {
        ResultVO<String> resultVO = new ResultVO<>();
        resultVO.setCode(errorCode);
        resultVO.setMsg(message);
        return resultVO;
    }
}
