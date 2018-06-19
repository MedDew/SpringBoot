/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springMVC.library.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Mehdi
 */
@Entity
@Table(name = "music")
public class Music {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    
    @NotNull
    @Size(max = 30)
    private String band;
    
    @NotNull
    @Size(max = 30)
    private String album;
    
    
    @NotNull
    private LocalDate releaseYear;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "music_genre", joinColumns = {@JoinColumn(name = "music_id")}, inverseJoinColumns = {@JoinColumn(name = "genre_id")})
    private Set<Genre> genres = new HashSet<Genre>();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;
    
    @Transient
    private LocalDate convertedDate;

    public void setConvertedDate(String convertedDate) 
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(convertedDate, formatter);
        this.convertedDate = localDate;
    }

    public LocalDate getConvertedDate() {
        return convertedDate;
    }
    
    public Music()
    {
        
    }
    
    public Music(String band, String album, String releaseYear, Set<Genre> genres, Category category)
    {
        this.band = band;
        this.album = album;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(releaseYear, formatter);
        System.out.println("====> localDate : "+localDate);
        this.releaseYear = localDate;
        this.genres = genres;
        this.category = category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public Long getId() {
        return Id;
    }

    public String getBand() {
        return band;
    }

    public String getAlbum() {
        return album;
    }

    public LocalDate getReleaseYear() {
        return releaseYear;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setReleaseYear(LocalDate releaseYear) {
        this.releaseYear = releaseYear;
    }
}
