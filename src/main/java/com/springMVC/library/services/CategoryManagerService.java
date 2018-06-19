/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springMVC.library.services;

import com.springMVC.library.entities.Category;
import com.springMVC.library.repositories.categoryRepository;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mehdi
 */
@Service
@Transactional
public class CategoryManagerService implements CategoryManager
{
    @Autowired
    private categoryRepository categoryRepo;
    
    @Override
    public Set<Category> showCategory() {
        Iterable<Category> categoryResult = categoryRepo.findAll();
        Set<Category> categorySet = new LinkedHashSet<>();
        categorySet.addAll((Collection<? extends Category>) categoryResult);
        return categorySet;
    }

    @Override
    public Category getCategoryById(long Id) {
        Optional<Category> categoryResult = categoryRepo.findById(Id);
        Category category = categoryResult.get();
        return category;
    }
    
}
