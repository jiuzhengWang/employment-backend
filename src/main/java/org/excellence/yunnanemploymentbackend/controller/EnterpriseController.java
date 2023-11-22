package org.excellence.yunnanemploymentbackend.controller;

import org.excellence.yunnanemploymentbackend.entity.EnterpriseInfo;
import org.excellence.yunnanemploymentbackend.entity.Notice;
import org.excellence.yunnanemploymentbackend.entity.ReportedData;
import org.excellence.yunnanemploymentbackend.entity.Response;
import org.excellence.yunnanemploymentbackend.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "enterprise")
public class EnterpriseController {
    @Autowired
    private EnterpriseService enterpriseService;



    @PostMapping(path = "inforeport")
    public @ResponseBody Response<Void> inforeport(@RequestBody List<EnterpriseInfo> enterpriseInfoList) {
        try {
            enterpriseService.inforeport(enterpriseInfoList);
            return new Response<>(true, "Report submitted successfully", null);
        } catch (Exception ex) {
            return new Response<>(false, ex.getMessage(), null);
        }
    }

    @GetMapping(path = "infoview/{userId}")
    public @ResponseBody Response<EnterpriseInfo> infoview(@PathVariable("userId") String enterpriseUserId) {
        try {
            EnterpriseInfo info = enterpriseService.infoview(enterpriseUserId);
            return new Response<>(true, "Information retrieved successfully", info);
        } catch (Exception ex) {
            return new Response<>(false, ex.getMessage(), null);
        }
    }

    @PostMapping(path = "datareport")
    public @ResponseBody Response<Void> datareport(@RequestBody ReportedData reportedData) {
        try {
            enterpriseService.datareport(reportedData);
            return new Response<>(true, "Data reported successfully", null);
        } catch (Exception ex) {
            return new Response<>(false, ex.getMessage(), null);
        }
    }

    @GetMapping(path = "datareview/{userId}")
    public @ResponseBody Response<List<ReportedData>> datareview(@PathVariable("userId") String enterpriseUserId) {
        try {
            List<ReportedData> data = enterpriseService.datareview(enterpriseUserId);
            return new Response<>(true, "Data reviewed successfully", data);
        } catch (Exception ex) {
            return new Response<>(false, ex.getMessage(), null);
        }
    }

    //enterprise 显示全部通知
    @GetMapping(path = "notice/getall")
    public @ResponseBody Response<List<Notice>> getAllNotice() {
        try{
            List<Notice> allNotice = enterpriseService.getAllNotice();
            return new Response<>(true,"Notice got successfully", allNotice);

        } catch (Exception ex) {
            return new Response<>(false, ex.getMessage(), null);
        }
    }



}
