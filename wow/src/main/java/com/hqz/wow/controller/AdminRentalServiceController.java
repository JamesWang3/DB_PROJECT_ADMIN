package com.hqz.wow.controller;

import com.hqz.wow.entity.CustomerEntity;
import com.hqz.wow.entity.RentalServiceEntity;
import com.hqz.wow.mapper.CustomerMapper;
import com.hqz.wow.service.CustomerService;
import com.hqz.wow.service.OfficeService;
import com.hqz.wow.service.RentalService;
import com.hqz.wow.service.VehicleService;
import com.hqz.wow.vo.RentalServiceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class AdminRentalServiceController {


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


    @GetMapping("/dashboard-rentalservice-all")
    public String showRentalService(Model model){
        List<RentalServiceEntity> rentalServiceEntities = rentalService.getAllRentalServiceEntityList();
        model.addAttribute("rentalService", rentalServiceEntities);
        return "dashboard-rentalservice-all";
    }

//    @GetMapping("/dashboard-rentalservice-status")
//    public String showRentalServiceStatus(@RequestParam(value = "status") String status, Model model){
//        List<RentalServiceEntity> rentalServiceEntities = rentalService.getStatusRentalServiceEntityList(status);
//        model.addAttribute("rentalService",rentalServiceEntities);
//        return "dashboard-rentalservice-all";
//    }

    @GetMapping("/showUpdateService")
    public String showUpdateService(@RequestParam(value = "serviceId") int serviceId, Model model){
        RentalServiceEntity rentalServiceEntity = rentalService.getRentalServiceById(serviceId);
        model.addAttribute("rentalServiceEntity", rentalServiceEntity);
        return "update-service";
    }

    @PostMapping("/saveUpdateService")
    public String saveUpdateService(RentalServiceEntity rentalServiceEntity){
        rentalService.resetEodometer(rentalServiceEntity.getServiceId(),rentalServiceEntity.getEOdometer());
        rentalService.resetStatus(rentalServiceEntity.getServiceId(), rentalServiceEntity.getServiceStatus());
        return "redirect:/dashboard-rentalservice-all";
    }


}
