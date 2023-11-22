package org.excellence.yunnanemploymentbackend.service;

import org.excellence.yunnanemploymentbackend.entity.EnterpriseInfo;
import org.excellence.yunnanemploymentbackend.entity.Notice;
import org.excellence.yunnanemploymentbackend.entity.ReportedData;
import org.excellence.yunnanemploymentbackend.repository.EnterpriseInfoRepository;
import org.excellence.yunnanemploymentbackend.repository.NoticeRepository;
import org.excellence.yunnanemploymentbackend.repository.ReportedDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EnterpriseService {
    @Autowired
    private EnterpriseInfoRepository enterpriseInfoRepository;

    @Autowired
    private ReportedDataRepository reportedDataRepository;

    @Autowired
    private NoticeRepository noticeRepository;

    //enterprise备案？？？


    //enterprise信息上报
    public void inforeport(List<EnterpriseInfo> enterpriseInfoList) {
        enterpriseInfoRepository.saveAll(enterpriseInfoList);
    }

    //enterprise信息展示
    public EnterpriseInfo infoview(String enterpriseUserId) {
        final var info = enterpriseInfoRepository.findById(Integer.valueOf(enterpriseUserId));
        if (info.isPresent()) {
            return info.get();
        }else {
            throw new NoSuchElementException("No EnterpriseInfo found with id: " + enterpriseUserId);
        }
    }

    //enterprise数据上报
    public void datareport(ReportedData reportedData) {
        reportedDataRepository.save(reportedData);
    }



    //enterprise数据查询
    public List<ReportedData> datareview(String enterpriseUserId) {
        return reportedDataRepository.findByEnterpriseUserId(enterpriseUserId);
    }

    //查询所有通知
    public List<Notice> getAllNotice() {
        return (List<Notice>) noticeRepository.findAll();
    }
}
