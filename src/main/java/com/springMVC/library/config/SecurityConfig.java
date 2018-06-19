/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springMVC.library.config;

import com.springMVC.library.component.CustomLogOutHandler;
import com.springMVC.library.services.MusicUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Mehdi
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private MusicUserDetailService musicUserDetailService;
    
    @Autowired
    private CustomLogOutHandler customLogOutHandler;
    
    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
        .antMatchers("/music/secure/**").hasAnyRole("ADMIN")//, "USER"
        .and().formLogin()
        .loginPage("/music/login")
        .loginProcessingUrl("/music-login")
        .usernameParameter("username")
        .passwordParameter("password")
        .defaultSuccessUrl("/music/secure/add")
        .and().logout()
        .logoutUrl("/music/logout")
        .logoutSuccessHandler(customLogOutHandler)
        //Useless when creating a custom logout handler
        //.logoutSuccessUrl("/music/login")
        .and().exceptionHandling()
        .accessDeniedPage("/music/accessDenied");
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception
    {
        BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        System.err.println("SecurityConfig::configureGlobal");
        auth.userDetailsService(musicUserDetailService).passwordEncoder(pwEncoder);
    }
    
}
