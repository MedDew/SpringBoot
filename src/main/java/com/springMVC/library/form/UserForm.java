/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springMVC.library.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Mehdi
 */
@Getter
@Setter
public class UserForm {

    @NotNull
    @NotEmpty(message = "Please fill the First Name field.")
    @Size(min = 2, max = 20, message = "First Name field should be between 2 and 20 charcacters")
    private String firstName;

    @NotNull
    @NotEmpty(message = "Please fill the Last Name field.")
    @Size(min = 2, max = 20, message = "Last Name field should be between 2 and 20 charcacters")
    private String lastName;
    
    @NotNull
    @NotEmpty(message = "Please fill the User Name field")
    @Size(min = 2, max = 25, message = "User Name field should be between 2 and 25 characters")
    private String userName;
    
    @NotNull
    @NotEmpty(message = "Please fill the Password field")
    private String password;
}
