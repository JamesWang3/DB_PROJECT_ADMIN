package com.hqz.wow.service;

import com.hqz.wow.entity.AdminEntity;
import com.hqz.wow.entity.CustomerEntity;
import com.hqz.wow.vo.AdminVO;
import com.hqz.wow.vo.CorpCustomerVO;
import com.hqz.wow.vo.IndivCustomerVO;
import com.hqz.wow.vo.ResetPasswordVO;

public interface AdminService {

    AdminEntity findAdminByAdminId(String admin_id);

    boolean checkIfAdminExist(String admin_id);

    void registerAdmin(AdminVO adminVO) ;

    void resetPassword(String admin_id, ResetPasswordVO resetPasswordVO);
}
