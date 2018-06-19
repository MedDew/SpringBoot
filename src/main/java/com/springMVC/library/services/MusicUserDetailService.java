/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springMVC.library.services;

import com.springMVC.library.DAO.UserDAO;
//import com.springMVC.library.entities.User;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mehdi
 */
@Service
public class MusicUserDetailService implements UserDetailsService 
{
    @Autowired
    private UserDAO userInfoDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException 
    {
        System.err.println("MusicUserDetailService::loadUserByUsername");
        com.springMVC.library.entities.User user = userInfoDAO.getActiveUserByUserName(userName);
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRoleName());
        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(
                                                                                                        user.getUserName(), 
                                                                                                        user.getPassword(), 
                                                                                                        Arrays.asList(authority)
                                                                                                      );
        
        return userDetails;
    }
}
