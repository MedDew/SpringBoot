/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springMVC.library.controller;

import com.springMVC.library.entities.Role;
import com.springMVC.library.services.UserManager;
import com.springMVC.library.entities.User;
import com.springMVC.library.form.UserForm;
import com.springMVC.library.services.RoleManager;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Mehdi
 */
@Controller
public class UserController 
{
    @Autowired
    private UserManager userService;
    
    @Autowired
    private RoleManager roleService;
    
    @GetMapping(path = "/user")
    public String showUser()
    {
        return "userList";
    }
    
    @GetMapping(path = "/user/post")
    public String showFormUser(UserForm userForm)
    {
        return "userForm";
    }
    
    @PostMapping(path = "/user/post")
    public String postUser(@Valid UserForm userForm, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "userForm";
        }
        
        Role r = roleService.getRoleByName("ROLE_ADMIN");
        System.out.println("com.springMVC.library.controller.UserController.postUser() Role : "+r.getId()+" => "+ r.getRoleName());
        System.out.println("com.springMVC.library.controller.UserController.postUser() : "+userForm.getFirstName()+" ==> "+userForm.getLastName());
        
        
        User savedUser = userService.insertUser(userForm, r);
        return "redirect:/user/post/result/"+savedUser.getId();
    }
    
    @GetMapping("/user/post/result/{id}")
    public String showPostResultUser(@PathVariable long id, ModelMap model)
    {
        System.out.println("com.springMVC.library.controller.UserController.showPostResultUser() : userIdToFind : "+id);
//        Optional<User> result = userRepo.findById(id);
//        User user = result.get();
        User user = userService.showUserById(id);
        model.addAttribute("firstNameLastName", user.getFirstName()+" "+user.getLastName());
        return "userPostResult";
    }
}
