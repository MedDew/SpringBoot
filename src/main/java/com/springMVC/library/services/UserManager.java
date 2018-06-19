/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springMVC.library.services;

import com.springMVC.library.entities.Role;
import com.springMVC.library.entities.User;
import com.springMVC.library.form.UserForm;
import java.util.List;

/**
 *
 * @author Mehdi
 */
public interface UserManager {
    
    public User insertUser(UserForm userForm, Role role);
    public User updateUser(User user);
    public User updateLastLoginDateIsLoggedUser(User user);
    public User updateIsLoggedUser(User user);
    public void deleteUser();
    public List<User> showUser();
    public User showUserById(long id);
    public User showUserByUsername(String userName);
}
