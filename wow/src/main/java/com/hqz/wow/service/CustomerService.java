package com.hqz.wow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hqz.wow.entity.ClassEntity;
import com.hqz.wow.entity.CustomerEntity;
import com.hqz.wow.vo.CorpCustomerVO;
import com.hqz.wow.vo.IndivCustomerVO;
import com.hqz.wow.vo.ResetPasswordVO;

import java.util.List;

public interface CustomerService {

    CustomerEntity findCustomerByEmail(String email);

    boolean checkIfCustomerExist(String email);

    void registerCorpCustomer(CorpCustomerVO corpCustomerVO) throws RuntimeException;

    void registerIndivCustomer(IndivCustomerVO indivCustomerVO) throws RuntimeException;

    void resetPassword(String email, ResetPasswordVO resetPasswordVO) throws RuntimeException;

    boolean validateSecQuestion(String email, int questionId, String answer);

    List<CustomerEntity> getCustomerEntityList();

    void deleteCustomer(CustomerEntity customerEntity);

    void deleteCustomerByEmail(String customerId);

    Page<CustomerEntity> findCustomerByKeyword(String email, String key);

     void updateCustomer(CustomerEntity customer);
}
