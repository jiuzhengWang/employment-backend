package org.excellence.yunnanemploymentbackend.service;

import org.excellence.yunnanemploymentbackend.entity.EnterpriseInfo;
import org.excellence.yunnanemploymentbackend.entity.Notice;
import org.excellence.yunnanemploymentbackend.entity.ReportedData;
import org.excellence.yunnanemploymentbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private EnterpriseInfoRepository enterpriseInfoRepository;

    @Autowired
    private ReportedDataRepository reportedDataRepository;

    @Autowired
    private NoticeRepository noticeRepository;

    /**
     * 查看所有已备案企业信息
     *
     * @return 所有已备案企业的列表
     */
    public List<EnterpriseInfo> getAllRegisteredEnterprise() {
        return enterpriseRepository.findEnterprisesByIsRegisteredIsTrue().stream()
                .map(enterprise -> enterpriseInfoRepository.findEnterpriseInfoByEnterpriseUserId(enterprise.getUserId()))
                .collect(Collectors.toList());
    }

    /**
     * 查看各市已备案企业信息
     *
     * @param cityUserId 所查询的市的userId
     * @return 当前市已备案的所有企业列表
     */
    public List<EnterpriseInfo> getRegisteredEnterpriseIn(String cityUserId) {
        return enterpriseRepository.findEnterprisesByIsRegisteredIsTrueAndLocatedCityIs(cityUserId).stream()
                .map(enterprise -> enterpriseInfoRepository.findEnterpriseInfoByEnterpriseUserId(enterprise.getUserId()))
                .collect(Collectors.toList());
    }

    /**
     * 获取所有待审核的企业上报数据
     * @return 待审核的企业上报数据的列表
     * @param provinceUserId 省的userId(方便多省扩展)
     */
    public List<ReportedData> getAllReportedDataAwaitingReview(String provinceUserId) {
        return provinceRepository.findById(provinceUserId)
                .map(province -> province.getDataAwaitingReview().stream()
                .map(reportedDataId -> reportedDataRepository.findById(reportedDataId).orElseGet(ReportedData::new))
                .collect(Collectors.toList())).orElseGet(ArrayList::new);
    }

    /**
     * 省审核通过上报的数据
     * @param reportedDataId 上报数据的Id
     * @return 操作是否成功
     */
    public Boolean approveReportedData(String provinceUserId, Integer reportedDataId) {
        try {
            final var wrappedProvince = provinceRepository.findById(provinceUserId);
            if (wrappedProvince.isPresent()) {
                // 省表中移除待审核标志
                final var province = wrappedProvince.get();
                province.getDataAwaitingReview().removeIf(integer -> integer.equals(reportedDataId));
                provinceRepository.save(province);
                // 数据表中添加"已审核通过"标志
                reportedDataRepository.findById(reportedDataId).ifPresentOrElse(reportedData -> {
                    reportedData.setStatus(1); // 1代表审核通过
                    reportedDataRepository.save(reportedData);
                }, () -> {});
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 省拒绝通过企业上报的数据
     * @param provinceUserId 省的UserId
     * @param reportedDataId 上报数据的Id
     * @return 操作是否成功
     */
    public Boolean rejectReportedData(String provinceUserId, Integer reportedDataId) {
        try {
            // TODO: 省拒绝企业上报的数据
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 审核通过企业的备案信息
     * @param provinceUserId 省的UserId
     * @param enterpriseInfoId 备案信息的Id
     * @return 操作是否成功
     */
    public Boolean approveEnterpriseInfo(String provinceUserId, Integer enterpriseInfoId) {
        try {
            // TODO: 审核通过企业的备案信息
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 拒绝通过企业的备案信息
     * @param provinceUserId 省的UserId
     * @param enterpriseInfoId 备案信息的Id
     * @return 操作是否成功
     */
    public Boolean rejectEnterpriseInfo(String provinceUserId, Integer enterpriseInfoId) {
        try {
            // TODO: 拒绝通过企业的备案信息
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 修改企业的上报数据
     * @param reportedData 修改之后的上报数据
     * @return 操作是否成功
     */
    public Boolean modifyReportedData(ReportedData reportedData) {
        // TODO: 修改企业的上报数据
        return true;
    }

    /**
     * 发布通知
     * @param provinceUserId 省用户的UserId
     * @param notice 具体通知内容
     * @return 操作是否成功
     */
    public Boolean releaseNotice(String provinceUserId, Notice notice) {
        // TODO: 发布通知
        noticeRepository.save(notice);
        return true;
    }
}
