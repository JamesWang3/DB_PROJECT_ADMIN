package com.hqz.wow.service;

import com.hqz.wow.entity.CustomerEntity;
import com.hqz.wow.entity.OfficeEntity;
import com.hqz.wow.entity.RentalServiceEntity;
import com.hqz.wow.entity.VehicleEntity;
import com.hqz.wow.vo.CheckoutVO;

import java.util.List;

public interface RentalService {
    void addRentalService(VehicleEntity vehicleEntity, OfficeEntity officeEntity, CustomerEntity customerEntity, CheckoutVO checkoutVO);

    RentalServiceEntity getRentalServiceById(int serviceId);

    List<RentalServiceEntity> getAllRentalServiceEntityList();

    List<RentalServiceEntity> getStatusRentalServiceEntityList(String status);
    void resetEodometer(int serviceId, float eodometer);
    void resetStatus(int serviceId, String status);
//
//    public List<RentalServiceEntity> getPRentalServiceEntityList(String status);
//
//    public List<RentalServiceEntity> getFRentalServiceEntityList(String status);
}
