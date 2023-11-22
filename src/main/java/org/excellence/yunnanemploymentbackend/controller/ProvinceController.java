package org.excellence.yunnanemploymentbackend.controller;

import org.excellence.yunnanemploymentbackend.entity.Response;
import org.excellence.yunnanemploymentbackend.service.EnterpriseService;
import org.excellence.yunnanemploymentbackend.service.ProvinceService;
import org.excellence.yunnanemploymentbackend.entity.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="province")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private EnterpriseService enterpriseService;

    //province 基本信息统计
    //    1. 已备案企业数量
    //    2. 就业总人数
    //



    //province 备案通过


    //province 发布通知


    //province 显示全部通知
    @GetMapping(path = "notice/getall")
    public @ResponseBody Response<List<Notice>> getAllNotice() {
        try{
            List<Notice> allNotice = enterpriseService.getAllNotice();
            return new Response<>(true,"Notice got successfully", allNotice);

        } catch (Exception ex) {
            return new Response<>(false, ex.getMessage(), null);
        }
    }

    //province 发布通知
    @PostMapping(path = "notice/release")
    public @ResponseBody Response<Boolean> releaseNotice(@RequestBody Notice notice) {
        try{
            if(provinceService.releaseNotice("Yunnan",notice)){
                return new Response<>(true,"ok",true);
            }
            else{
                return new Response<>(true, "oh no", false);
            }
        } catch (Exception ex) {
            return new Response<>(false, ex.getMessage(), null);
        }
    }


}
