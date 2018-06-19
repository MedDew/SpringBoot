/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springMVC.library.services;

import com.springMVC.library.entities.Genre;
import com.springMVC.library.repositories.genreRepository;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
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
public class GenreMangerService implements GenreManager
{
    @Autowired
    private genreRepository genreRepo;
    
    @Override
    public Set<Genre> showGenre() {
        Iterable<Genre> genreResult = genreRepo.findAll();
        Set<Genre> genreSet = new LinkedHashSet<>();
        genreSet.addAll((Collection<? extends Genre>) genreResult);
        return genreSet;
    }

    @Override
    public Set<Genre> getGenreByIds(Set<Long> genreIds) 
    {
        Iterable<Genre> genreResult = genreRepo.findAllById(genreIds);
        Set<Genre> genreSet = new HashSet<>();
        genreSet.addAll((Collection<? extends Genre>) genreResult);
        return genreSet;
    }
}
