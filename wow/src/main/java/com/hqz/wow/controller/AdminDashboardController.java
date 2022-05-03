package com.hqz.wow.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hqz.wow.entity.ClassEntity;
import com.hqz.wow.entity.CustomerEntity;
import com.hqz.wow.entity.OfficeEntity;
import com.hqz.wow.entity.VehicleEntity;
import com.hqz.wow.mapper.CustomerMapper;
import com.hqz.wow.service.*;
import com.hqz.wow.vo.DashboardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    DashboardService   dashboardService;

//    @Autowired
//    private CustomerRepository cRepo;
    /**
     * home page
     *
     * @param model model for the page
     * @return index.html
     */
    @ GetMapping("/dashboardmenu")
    public String index(Model model) {
        // show customer list on home page
        // todo pagination
        DashboardVO dashboardVO = dashboardService.getDashboardService();
        model.addAttribute("dashboardVO", dashboardVO);
        return "dashboardmenu";
    }
    @GetMapping("/dashboard-customer")
    public String showCustomer(Model model){
        List<CustomerEntity> customerEntityList = customerService.getCustomerEntityList();
        model.addAttribute("customerEntityList", customerEntityList);
        return "dashboard-customer";
    }

    @GetMapping("/addCustomerForm")
    public String addIndivCustomerForm(Model model){
        CustomerEntity customerEntity = new CustomerEntity();
        model.addAttribute("customer", customerEntity);
        return "add-customer-form";
    }

//    @GetMapping("/addCorpCustomerForm")
//    public String addCorpCustomerForm(Model model){
////        CustomerEntity customerEntity = new CustomerEntity();
////        model.addAttribute("customer", customerEntity);
//        return "register-corp";
//    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(CustomerEntity customerEntity){
        customerService.updateCustomer(customerEntity);
//        customerMapper.updateById(customerEntity);
        return "redirect:/dashboardmenu";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam(value = "email") String email,Model model){
        CustomerEntity customerEntity = customerService.findCustomerByEmail(email);
        model.addAttribute("customer", customerEntity);
        return "add-customer-form";
    }

    @GetMapping("deleteCustomer")
    public String deleteCustomer(String email){
        customerService.deleteCustomerByEmail(email);
        return "redirect:/dashboardmenu";
    }
}
