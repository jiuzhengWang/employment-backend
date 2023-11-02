package org.excellence.yunnanemploymentbackend.service;

import org.excellence.yunnanemploymentbackend.entity.EnterpriseInfo;
import org.excellence.yunnanemploymentbackend.entity.ReportedData;
import org.excellence.yunnanemploymentbackend.repository.EnterpriseInfoRepository;
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

    public void inforeport(List<EnterpriseInfo> enterpriseInfoList) {
        enterpriseInfoRepository.saveAll(enterpriseInfoList);
    }

    public EnterpriseInfo infoview(String enterpriseUserId) {
        final var info = enterpriseInfoRepository.findById(Integer.valueOf(enterpriseUserId));
        if (info.isPresent()) {
            return info.get();
        }else {
            throw new NoSuchElementException("No EnterpriseInfo found with id: " + enterpriseUserId);
        }
    }

    public void datareport(ReportedData reportedData) {
        reportedDataRepository.save(reportedData);
    }

    public List<ReportedData> datareview(String enterpriseUserId) {
        return reportedDataRepository.findByEnterpriseUserId(enterpriseUserId);
    }
}
