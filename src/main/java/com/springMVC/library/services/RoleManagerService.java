/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springMVC.library.services;

import com.springMVC.library.DAO.RoleDAO;
import com.springMVC.library.entities.Role;
import com.springMVC.library.repositories.roleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mehdi
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleManagerService implements RoleManager
{
    @Autowired
    private roleRepository roleRepo;
    
    @Autowired
    private RoleDAO roleDAO;
    
    @Override
    public Role getRoleByName(String name) 
    {
        Role r = roleDAO.getRoleByName(name);
        return r;
    }
    
}
