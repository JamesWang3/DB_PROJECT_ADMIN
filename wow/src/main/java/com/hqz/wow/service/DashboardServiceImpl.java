package com.hqz.wow.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hqz.wow.entity.*;
import com.hqz.wow.mapper.*;
import com.hqz.wow.vo.DashboardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    IndivCustomerMapper indivCustomerMapper;

    @Autowired
    CorpCustomerMapper corpCustomerMapper;

    @Autowired
    ClassMapper  classMapper;

    @Autowired
    OfficeMapper officeMapper;

    @Autowired
    RentalServiceMapper rentalServiceMapper;

    @Autowired
    PaymentMapper paymentMapper;

    @Override
    public DashboardVO getDashboardService() {
        DashboardVO dashboardVO = new DashboardVO();
        QueryWrapper<CustomerEntity> wrapper1 = new QueryWrapper<>();
        Long customerCount = customerMapper.selectCount(wrapper1);

        QueryWrapper<IndivCustomerEntity> wrapper2 = new QueryWrapper<>();
        Long indivCustomerCount = indivCustomerMapper.selectCount(wrapper2);

        QueryWrapper<CorpCustomerEntity> wrapper3 = new QueryWrapper<>();
        Long corpCustomerCount = corpCustomerMapper.selectCount(wrapper3);

        QueryWrapper<ClassEntity> wrapper4 = new QueryWrapper<>();
        Long classCount = classMapper.selectCount(wrapper4);

        QueryWrapper<OfficeEntity> wrapper5 = new QueryWrapper<>();
        Long officeCount = officeMapper.selectCount(wrapper5);

        QueryWrapper<RentalServiceEntity> wrapper6 = new QueryWrapper<>();
        Long rentalServiceCount = rentalServiceMapper.selectCount(wrapper6);

        QueryWrapper<RentalServiceEntity> wrapper7 = new QueryWrapper<>();
        wrapper7.eq("service_status", 'O');
        Long serviceStatusOCount = rentalServiceMapper.selectCount(wrapper7);

        QueryWrapper<RentalServiceEntity> wrapper8 = new QueryWrapper<>();
        wrapper8.eq("service_status", 'P');
        Long serviceStatusPCount = rentalServiceMapper.selectCount(wrapper8);

        QueryWrapper<RentalServiceEntity> wrapper9 = new QueryWrapper<>();
        wrapper9.eq("service_status", 'A');
        Long serviceStatusACount = rentalServiceMapper.selectCount(wrapper9);

        QueryWrapper<RentalServiceEntity> wrapper10 = new QueryWrapper<>();
        wrapper10.eq("service_status", 'F');
        Long serviceStatusFCount = rentalServiceMapper.selectCount(wrapper10);

        QueryWrapper<RentalServiceEntity> wrapper11 = new QueryWrapper<>();
        Long paymentUSD = rentalServiceMapper.selectCount(wrapper11);

        dashboardVO.setCustomerCount(customerCount);
        dashboardVO.setIndivCustomerCount(indivCustomerCount);
        dashboardVO.setCorpCustomerCount(corpCustomerCount);
        dashboardVO.setClassCount(classCount);
        dashboardVO.setOfficeCount(officeCount);
        dashboardVO.setServiceCount(rentalServiceCount);
        dashboardVO.setServiceStatusOCount(serviceStatusOCount);
        dashboardVO.setServiceStatusPCount(serviceStatusPCount);
        dashboardVO.setServiceStatusACount(serviceStatusACount);
        dashboardVO.setServiceStatusFCount(serviceStatusFCount);
        return dashboardVO;
    }

}
