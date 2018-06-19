/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springMVC.library.component;

import com.springMVC.library.DAO.UserDAO;
import com.springMVC.library.entities.User;
import com.springMVC.library.services.UserManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mehdi
 */
@Component
public class CustomLogOutHandler implements LogoutSuccessHandler  
{
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private UserManager userService;
    
    
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException 
    {
        System.err.println("CustomLogOutHandler::onLogoutSuccess");
        System.err.println("auth : "+auth.getName());
        User user = userDAO.getActiveUserByUserName(auth.getName());
        User userUpdated = userService.updateIsLoggedUser(user);
        
        System.out.println("IS USER SIGN OUT : "+userUpdated.getIsLogged());
        
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("/music/login");
    }
    
}
