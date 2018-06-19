/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springMVC.library.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Mehdi
 */
@Entity
@Table(name = "genre")
public class Genre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    
    @NotNull
    @Size(max = 30)
    private String genreName;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "genres")
    private Set<Music> music = new HashSet<Music>();

    public void setId(Long Id) {
        this.Id = Id;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public void setMusic(Set<Music> music) {
        this.music = music;
    }

    public Long getId() {
        return Id;
    }

    public String getGenreName() {
        return genreName;
    }

    public Set<Music> getMusic() {
        return music;
    }

    @Override
    public String toString() 
    {
        String genreId = String.valueOf(this.getId());
        return genreId; 
    }
}
