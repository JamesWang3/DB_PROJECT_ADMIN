package com.hqz.wow.service.security;

import com.hqz.wow.entity.AdminEntity;
import com.hqz.wow.service.AdminService;
//import com.hqz.wow.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminDetailServiceImpl implements UserDetailsService {

    @Autowired
    AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String admin_id) throws UsernameNotFoundException {// 一直找不到admin
        AdminEntity admin = adminService.findAdminByAdminId(admin_id);
        if (admin == null) {
            System.out.println("load admin == null =======================!!!!!!!!!");
            throw new UsernameNotFoundException(admin_id);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + admin.getAdmin_id()));
        System.out.println("in Detail");
        return new User(admin.getAdmin_id(), admin.getA_password(), authorities);
    }
}

///new version detialserviceimpl
//@Service
//public class AdminDetailServiceImpl implements UserDetailsService {
//
//    @Autowired
//    UserRepository adminService;
//
//    @Override
//    public UserDetails loadUserByUsername(String admin_id) throws UsernameNotFoundException {
//        AdminEntity admin = adminService.findByUsername(admin_id);
//        if (admin == null) {
//            throw new UsernameNotFoundException(admin_id);
//        }
//
//        UserDetails user = User.withUsername(admin_id).password(admin.getA_password()).authorities("USER").build();
//        return user;
//
////        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
////        authorities.add(new SimpleGrantedAuthority("ROLE_" + Integer.parseInt(admin_id)));
////        return new User(String.valueOf(admin.getAdmin_id()), admin.getA_password(), authorities);
//    }
//}

