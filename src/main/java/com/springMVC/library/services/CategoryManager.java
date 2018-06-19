/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springMVC.library.services;

import com.springMVC.library.entities.Category;
import java.util.Set;

/**
 *
 * @author Mehdi
 */
public interface CategoryManager 
{
    public Set<Category> showCategory();
    public Category getCategoryById(long Id);
}
