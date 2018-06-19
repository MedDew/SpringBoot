/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springMVC.library.form;

import java.util.Set;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Mehdi
 */
@Getter
@Setter
@ToString
public class MusicForm {

    @NotNull
    @NotEmpty(message = "Please fill the album field")
    @Size(max = 30, message = "The album name is too long(max 30 characters)")
    private String album;

    @NotNull
    @NotEmpty(message = "Please fill the band field")
    @Size(max = 30, message = "The band name is too long(max 30 characters)")
    private String band;

    @NotNull
    @NotEmpty(message = "Please fill the Release Year field")
    private String releaseYear;

    @NotNull(message = "Choose a category")
    private Long categoryId;

    @NotNull
    @NotEmpty(message = "Please fill the Genre field")
    private Set<Long> genre;

}
