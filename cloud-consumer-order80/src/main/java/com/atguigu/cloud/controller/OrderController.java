package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {
//    public static final String PaymentSrv_URL="http://localhost:8001";//先写死,硬编码
    public static final String PaymentSrv_URL="http://cloud-payment-service";//服务注册中心上的微服务
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/pay/add")
    public ResultData addOrder(PayDTO payDTO){
        return restTemplate.postForObject(PaymentSrv_URL + "/pay/add", payDTO, ResultData.class);
    }
    //
    @GetMapping(value = "/consumer/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PaymentSrv_URL+"/pay/get/"+id,ResultData.class,id);
    }
    @PutMapping(value = "/consumer/pay/update")
    public ResultData updatePayInfo(@RequestBody PayDTO payDTO){
        return restTemplate.exchange(PaymentSrv_URL+ "/pay/update", HttpMethod.PUT, new HttpEntity<>(payDTO), ResultData.class).getBody();
    }

    @GetMapping(value = "/consumer/pay/del/{id}")
    public ResultData deletePayInfo(@PathVariable("id") Integer id){
//        restTemplate.delete(PaymentSrv_URL+"/pay/del/"+id);
//        return ResultData.success(1);
        return restTemplate.exchange(PaymentSrv_URL + "/pay/del/" + id, HttpMethod.DELETE, null, ResultData.class).getBody();
    }
    @GetMapping(value = "/consumer/pay/getAll")
    public ResultData getAllPayInfo(){
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/getAll", ResultData.class);
    }
    @GetMapping(value = "/consumer/pay/get/info")
    private String getInfoByConsul()
    {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/info", String.class);
    }
}
