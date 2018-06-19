/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springMVC.library.DAO;

import com.springMVC.library.entities.Role;
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
public class RoleDAOImpl implements RoleDAO 
{
    @PersistenceContext
    private EntityManager em;

    @Override
    public Role getRoleByName(String roleName) 
    {
//        Query q = em.createQuery("Select r From Role r Where roleName = ?");
//        q.setParameter(1, "ADMIN");
        Query q = em.createQuery("Select r From Role r Where roleName = :roleName ");
        q.setParameter("roleName", "ROLE_ADMIN");
        
        Role r = (Role) q.getSingleResult();
        
        return r;
    }
    
}
