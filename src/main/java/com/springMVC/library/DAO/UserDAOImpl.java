/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springMVC.library.DAO;

import com.springMVC.library.entities.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mehdi
 */
@Repository
@Transactional
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public User getActiveUserByUserName(String userName) {
        
        Boolean isActive = true;
        Query q = em.createQuery("SELECT u FROM User u WHERE userName = ? AND  isActive = ? ");//AND u.role.roleName = ?
        q.setParameter(0, userName);
        q.setParameter(1, isActive);
//        q.setParameter(2, "ADMIN");
        User user = (User) q.getSingleResult();
        return user;
    }
    
}
