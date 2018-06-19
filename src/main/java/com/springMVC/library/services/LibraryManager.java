/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springMVC.library.services;

import com.springMVC.library.entities.Category;
import com.springMVC.library.entities.Genre;
import com.springMVC.library.entities.Music;
import com.springMVC.library.form.MusicForm;
import java.util.List;
import java.util.Set;
import org.springframework.security.access.annotation.Secured;

/**
 *
 * @author Mehdi
 */
public interface LibraryManager 
{
//    @Secured({"ROLE_USER"})
    public Music insertMusic(MusicForm musicForm, Set<Genre> genres, Category category) ;//throws Exception;

    public Music updateMusic(MusicForm musicForm, long musicId);//long musicId, String genreIds
    public Music addGenreMusic(MusicForm musicForm, long musicId);
    public void deleteMusic(long musicId);
    public List<Music> showMusic();
    public Music showMusicById(long id);
}
