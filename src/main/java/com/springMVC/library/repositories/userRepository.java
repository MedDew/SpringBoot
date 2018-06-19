/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springMVC.library.repositories;

import com.springMVC.library.DAO.UserDAO;
import com.springMVC.library.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Mehdi
 */
//public interface userRepository implements CrudRepository<User, Long>, UserDAO
public interface userRepository extends CrudRepository<User, Long>//, UserDAO
{
    
}