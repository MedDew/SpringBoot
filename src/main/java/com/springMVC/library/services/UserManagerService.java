/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springMVC.library.services;

import com.springMVC.library.DAO.UserDAO;
import com.springMVC.library.entities.Role;
import com.springMVC.library.entities.User;
import com.springMVC.library.form.UserForm;
import com.springMVC.library.repositories.userRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mehdi
 */
@Service
@Transactional
public class UserManagerService implements UserManager
{
    @Autowired
    private userRepository userRepo;
    
    @Autowired
    private UserDAO userDAO;
    
    @Override
    public User insertUser(UserForm userForm, Role role) {
        BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        String password = pwEncoder.encode(userForm.getPassword());
        
        User u = new User(userForm.getFirstName(), userForm.getLastName(), userForm.getUserName(), password,role);
        User savedUser = userRepo.save(u);
        
        return savedUser;
    }

    @Override
    public User updateUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User updateLastLoginDateIsLoggedUser(User user) 
    {
//        Optional<User> userResult = userRepo.findById(user.getId());
//        User foundUser = userResult.get();
        user.setIsLogged(true);
        LocalDateTime localDateTime = LocalDateTime.now();
        user.setLastLoginDate(localDateTime);
        
        return user;
    }

    @Override
    public void deleteUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<com.springMVC.library.entities.User> showUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User showUserById(long id) {
        Optional<User> userResult = userRepo.findById(id);
        User user = userResult.get();
        return user;
    }

    @Override
    public User showUserByUsername(String userName) {
        User user = userDAO.getActiveUserByUserName(userName);
        return user;
    }

    @Override
    public User updateIsLoggedUser(User user) 
    {
        user.setIsLogged(Boolean.FALSE);
        
        return user;
    }
}
