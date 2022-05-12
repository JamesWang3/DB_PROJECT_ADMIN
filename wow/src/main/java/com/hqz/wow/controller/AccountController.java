package com.hqz.wow.controller;

import com.hqz.wow.entity.CorpInfoEntity;
import com.hqz.wow.entity.SecurityQuestionEntity;
import com.hqz.wow.service.AdminService;
import com.hqz.wow.service.CorpInfoService;
import com.hqz.wow.service.CustomerService;
import com.hqz.wow.service.SecurityQuestionService;
import com.hqz.wow.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Controller for customer account related views
 * register, login, confirm info, reset password
 */
@Controller
@Slf4j
public class AccountController {


    @Resource
    AdminService adminService;

    @Resource
    SecurityQuestionService securityQuestionService;

    /**
     * Corporation customer registration - GET
     *
     * @param model Model for the page
     * @return register-corp.html
     */

    /**
     * Admin registration - GET
     *
     * @param model Model for the page
     * @return admin.html
     */
    @GetMapping("/register-admin")
    public String registerAdmin(Model model) {
        // When user start register (GET), prepare VO to receive user input
        AdminVO adminVO = new AdminVO();

        model.addAttribute("adminVO", adminVO);
        return "register-admin";
    }

    /**
     * Process admin registration
     *
     * @param adminVO admin information
     * @param bindingResult   User input validation
     * @param model           Model for the page
     * @return Input invalid return registration page, successful return login page
     */
    @PostMapping("/register-admin")
    public String registerSaveAdmin(@Valid @ModelAttribute("adminVO") AdminVO adminVO, BindingResult bindingResult, Model model) {

        // check if id already registered
        if (adminService.checkIfAdminExist(adminVO.getAdminId())) {
            model.addAttribute("adminExists", true);
            System.out.println("already exist!");
            return "register-admin";
        }

        if (bindingResult.hasErrors()) {
            // input invalid, display error messages
            return "register-admin";
        }
        try {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String password = bCryptPasswordEncoder.encode(adminVO.getPassword());
            adminVO.setPassword(password);
            adminService.registerAdmin(adminVO);
            return "login-admin";
        } catch (Exception e) {
            model.addAttribute("error", true);
            return "register-admin";
        }
    }

    /**
     * login page with Spring Security
     *
     * @return login-admin.html
     */
    @RequestMapping("/login-admin")
    public String login() {
        return "login-admin";
    }

    /**
     * logout page, only takes POST request to prevent CSRF
     *
     * @param request  Http request
     * @param response Http response
     * @return login page
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        // get authentication information
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            // Spring Security logout API
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/dashboardmenu";
    }

    /**
     * User information confirmation for password reset (e-mail address, security question and answer)
     *
     * @param model Model for the page
     * @return confirm-info.html
     */
    @GetMapping("/confirm-info")
    public String forgetPassword(Model model) {
        if (!model.containsAttribute("infoConfirmVO")) {
            // prepare VO to receive user input
            InfoConfirmVO infoConfirmVO = new InfoConfirmVO();
            model.addAttribute("infoConfirmVO", infoConfirmVO);
        }
        if (!model.containsAttribute("questionList")) {
            // prepare security question list
            List<SecurityQuestionEntity> questionList = securityQuestionService.getSecQuestions();
            model.addAttribute("questionList", questionList);
        }
        return "confirm-info";
    }

    /**
     * process user information confirmation
     *
     * @param infoConfirmVO      user information input
     * @param bindingResult      user input validation result
     * @param redirectAttributes attributes needed to be redirected (normal model won't be redirected)
     * @param model              model for the page
     * @return info invalid redirect to confirm-info.html, successful return reset-password.html
     */
//    @PostMapping("/reset-password")
//    public String resetPassword(@Valid @ModelAttribute("infoConfirmVO") InfoConfirmVO infoConfirmVO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
//        if (bindingResult.hasErrors()) {
//            // redirect error messages
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.infoConfirmVO", bindingResult);
//            redirectAttributes.addFlashAttribute("infoConfirmVO", infoConfirmVO);
//            return "redirect:/confirm-info";
//        }
//
//        String email = infoConfirmVO.getEmail();
//        // check if e-mail exists
//        if (!customerService.checkIfCustomerExist(email)) {
//            redirectAttributes.addFlashAttribute("emailNotExists", true);
//            return "redirect:/confirm-info";
//        }
//
//        // validate customer security question
//        if (!customerService.validateSecQuestion(email, infoConfirmVO.getQuestionId(), infoConfirmVO.getSecAnswer())) {
//            redirectAttributes.addFlashAttribute("wronganswer", true);
//            return "redirect:/confirm-info";
//        }
//
//        // prepare VO for reset password
//        ResetPasswordVO resetPasswordVO = new ResetPasswordVO();
//        model.addAttribute("resetPasswordVO", resetPasswordVO);
//        model.addAttribute("email", email);
//
//        return "reset-password";
//    }

    /**
     * porcess user reset password (validate password and update database)
     *
     * @param resetPasswordVO VO for password information
     * @param request         Http request
     * @param model           model for the page
     * @return password invalid return reset-password.html, successful return login.html
     */
//    @PostMapping("/reset-password-process")
//    public String processResetPassword(@ModelAttribute("resetPasswordVO") ResetPasswordVO resetPasswordVO, HttpServletRequest request, Model model) {
//        if (!resetPasswordVO.getPassword().equals(resetPasswordVO.getConfirmPassword())) {
//            model.addAttribute("passwordMismatch", true);
//            model.addAttribute("email", request.getParameter("email"));
//            return "reset-password";
//        }
//        try {
//            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//            String password = bCryptPasswordEncoder.encode(resetPasswordVO.getPassword());
//            resetPasswordVO.setPassword(password);
//            String email = request.getParameter("email");
//            customerService.resetPassword(email, resetPasswordVO);
//            return "/login";
//        } catch (Exception e) {
//            model.addAttribute("error", true);
//            return "reset-password";
//        }
//    }
}
