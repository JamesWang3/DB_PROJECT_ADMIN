package com.hqz.wow.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hqz.wow.entity.ClassEntity;
import com.hqz.wow.entity.CustomerEntity;
import com.hqz.wow.entity.OfficeEntity;
import com.hqz.wow.entity.VehicleEntity;
import com.hqz.wow.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class AdminDashboardController {

    @Resource
    CustomerService customerService;

    @Resource
    VehicleService vehicleService;

    @Resource
    OfficeService officeService;


    @Resource
    RentalService rentalService;

    /**
     * home page
     *
     * @param model model for the page
     * @return index.html
     */
    @RequestMapping("/dashboard")
    public String index(Model model) {
        // show customer list on home page
        // todo pagination
        List<CustomerEntity> customerEntityList = customerService.getCustomerEntityList();
        model.addAttribute("customerEntityList", customerEntityList);
        return "dashboard";
    }
}
