/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springMVC.library.DAO;

import com.springMVC.library.entities.Role;

/**
 *
 * @author Mehdi
 */
public interface RoleDAO 
{
    public Role getRoleByName(String roleName);
}
