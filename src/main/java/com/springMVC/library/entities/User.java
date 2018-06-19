/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springMVC.library.entities;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Mehdi
 */
@Entity
@Table(name = "User")
public class User 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(max = 30)
    private String lastName;
    
    @NotNull
    @Size(max = 30)
    private String firstName;
    
    @Column(unique = true, nullable = false,length = 25)
    private String userName;

    @NotNull
    private LocalDateTime creationDate;
    
    private LocalDateTime lastLoginDate;
    
    @Column(columnDefinition = "TINYINT NULL DEFAULT 0")
    private Boolean isLogged;
    
    @Column(nullable = false,length = 60)
    private String password;
    
    @Column(columnDefinition = "DEFAULT 1")
    private Boolean isActive;
    

    
    @ManyToOne()//fetch = FetchType.LAZY
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;


    
    public User()
    {
        
    }
    
    public User(String firstName, String  lastName, String userName, String password, Role role)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.systemDefault());
        System.out.println("com.springMVC.library.entities.User.<init>() : "+ldt.getYear()+"-"+ldt.getMonth()+"-"+ldt.getDayOfMonth()+" "+ldt.getHour()+":"+ldt.getMinute()+":"+ldt.getSecond()); 
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String creationDate = ldt.format(dateTimeFormat);
        System.out.println("com.springMVC.library.entities.User.<init>() : "+creationDate);
        this.creationDate = ldt;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.isActive = true;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public Boolean getIsLogged() {
        return isLogged;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public void setIsLogged(Boolean isLogged) {
        this.isLogged = isLogged;
    }
}