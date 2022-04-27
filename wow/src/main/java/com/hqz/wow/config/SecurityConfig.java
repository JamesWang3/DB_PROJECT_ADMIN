package com.hqz.wow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * Customer pages security configurations
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    UserDetailsService userDetailsService;

//    public DaoAuthenticationProvider authProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authProvider());
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web){
        // configure Spring Security to ignore static resources
        web.ignoring()
                .antMatchers("/css/**", "/images/**", "/js/**", "/plugins/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("in config");
        http.authorizeRequests()
                .antMatchers("/login-admin", "/register-admin", "/reset-password", "/confirm-info", "/reset-password-process").permitAll()
                .anyRequest().authenticated()
                .and()
                // Remember me configurations
                .rememberMe()
                .userDetailsService(this.userDetailsService)
                .tokenValiditySeconds(86400)
                .useSecureCookie(true)
                .rememberMeCookieName("wow-cookie")
                //Login configurations
                .and()
                .formLogin()
                .loginPage("/login-admin")
                .defaultSuccessUrl("/dashboard", true)
                .failureUrl("/login-admin?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login-admin")
                .invalidateHttpSession(true)
                .deleteCookies("wow-cookie");
        System.out.println(this.userDetailsService + "in config");
    }
}
