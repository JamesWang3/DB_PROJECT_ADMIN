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

        dashboardVO.setCustomerCount(customerCount);
        dashboardVO.setIndivCustomerCount(indivCustomerCount);
        dashboardVO.setCorpCustomerCount(corpCustomerCount);
        dashboardVO.setClassCount(classCount);
        dashboardVO.setOfficeCount(officeCount);
        dashboardVO.setServiceCount(rentalServiceCount);
        return dashboardVO;
    }

}
