package com.hqz.wow.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hqz.wow.entity.CustomerEntity;
import com.hqz.wow.entity.OfficeEntity;
import com.hqz.wow.entity.RentalServiceEntity;
import com.hqz.wow.entity.VehicleEntity;
import com.hqz.wow.exception.RegistrationException;
import com.hqz.wow.mapper.RentalServiceMapper;
import com.hqz.wow.util.WowConstants;
import com.hqz.wow.vo.CheckoutVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    RentalServiceMapper rentalServiceMapper;

    @Override
    @Transactional
    public void addRentalService(VehicleEntity vehicleEntity, OfficeEntity officeEntity,
                                 CustomerEntity customerEntity, CheckoutVO checkoutVO)  {
//        try {
            RentalServiceEntity rentalServiceEntity = new RentalServiceEntity();
            rentalServiceEntity.setCouponId(checkoutVO.getCouponId());
            rentalServiceEntity.setCustomerId(customerEntity.getCustomerId());

            rentalServiceEntity.setDDate(checkoutVO.getDDate());
            rentalServiceEntity.setDOffice(checkoutVO.getDOffice());
            if (Objects.equals(customerEntity.getCustomerType(), WowConstants.INDIV_TYPE)) {
                rentalServiceEntity.setDailyOLimit(WowConstants.INDIV_DAILY_O_LIMIT);
            }
            rentalServiceEntity.setPDate(checkoutVO.getPDate());
            rentalServiceEntity.setPOffice(officeEntity.getOfficeID());
            rentalServiceEntity.setSOdometer(vehicleEntity.getOdometer());
            rentalServiceEntity.setEOdometer(vehicleEntity.getOdometer());
            rentalServiceEntity.setVin(vehicleEntity.getVin());
            rentalServiceEntity.setServiceStatus(WowConstants.SERVICE_ONGOING);

            rentalServiceMapper.insert(rentalServiceEntity);
//        } catch (Exception e) {
//            throw new RegistrationException(WowConstants.PLACE_ORDER_ERROR, "Error Place Order");
//        }
    }

    @Override
    public RentalServiceEntity getRentalServiceById(int serviceId) {
        return rentalServiceMapper.selectById(serviceId);
    }

    @Override
    public List<RentalServiceEntity> getAllRentalServiceEntityList() {
        return rentalServiceMapper.selectList(null);
    }

    @Override
    public List<RentalServiceEntity> getStatusRentalServiceEntityList(String status) {
        QueryWrapper<RentalServiceEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("serviceStatus", status);
        return rentalServiceMapper.selectList(wrapper);
    }

    @Override
    public void resetEodometer(int serviceId, float eodometer){
        RentalServiceEntity rentalServiceEntity = getRentalServiceById(serviceId);
        try{
            rentalServiceEntity.setEOdometer(eodometer);
        }
        catch (Exception E){
            if(eodometer < rentalServiceEntity.getEOdometer()){
                System.out.println("error eodometer");
            }
        }
        rentalServiceMapper.updateById(rentalServiceEntity);
    }

    @Override
    public  void resetStatus(int serviceId, String status){
        RentalServiceEntity rentalServiceEntity = getRentalServiceById(serviceId);
        try{
            rentalServiceEntity.setServiceStatus(status);
        }
        catch (Exception e){
            System.out.println("cannot reset status==============");
        }
        rentalServiceMapper.updateById(rentalServiceEntity);
    }

//    @Override
//    public List<RentalServiceEntity> getPRentalServiceEntityList(String status) {
//        QueryWrapper<RentalServiceEntity> wrapper = new QueryWrapper<>();
//        wrapper.eq("serviceStatus", status);
//        return rentalServiceMapper.selectList(wrapper);
//    }
//
//    @Override
//    public List<RentalServiceEntity> getFRentalServiceEntityList(String status) {
//        return null;
//    }
}
