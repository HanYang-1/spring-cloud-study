package com.atguigu.cloud.resp;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResultData<T> {
    private String code;/** 结果状态 ,具体状态码参见枚举类ReturnCodeEnum.java*/
    private String message;
    private T data;
    private long timestamp;

    public ResultData() {
        this.timestamp=System.currentTimeMillis();
    }
    public static <T> ResultData<T> success(T data){
        ResultData resultData = new ResultData<>();
        resultData.setCode(ReturnCodeEnum.RC200.getCode()).setMessage(ReturnCodeEnum.RC200.getMessage()).setData(data);
        return resultData;
    }
    public static <T> ResultData<T> fail(String code,String message){
        ResultData resultData = new ResultData<>();
        resultData.setCode(code).setMessage(message).setData(null);
        return resultData;
    }
}
