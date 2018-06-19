/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springMVC.library.services;

import com.springMVC.library.entities.Role;

/**
 *
 * @author Mehdi
 */
public interface RoleManager 
{
    public Role getRoleByName(String name);
}
