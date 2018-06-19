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
import com.springMVC.library.repositories.categoryRepository;
import com.springMVC.library.repositories.genreRepository;
import com.springMVC.library.repositories.musicRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
@Transactional(rollbackFor = Exception.class)
public class LibraryManagerService implements LibraryManager {

    @Autowired
    private musicRepository musicRepo;

    @Autowired
    private categoryRepository categoryRepo;

    @Autowired
    private genreRepository genreRepo;

    @Override
    public Music insertMusic(MusicForm musicForm, Set<Genre> genres, Category category) {//throws Exception {
        Music m = new Music(musicForm.getBand(), musicForm.getAlbum(), musicForm.getReleaseYear(), genres, category);
        Music savedMusic = musicRepo.save(m);
//        if(1 == 1)
//        {
//            throw new Exception();
//        }
        return savedMusic;
    }

    @Override
    public Music updateMusic(MusicForm musicForm, long musicId) {//, long musicId
        Optional<Music> musicResult = musicRepo.findById(musicId);//musicId
        Music music = musicResult.get();
        music.setAlbum(musicForm.getAlbum());
        music.setBand(musicForm.getBand());

        //Convert String date To Loacl Date
        music.setConvertedDate(musicForm.getReleaseYear());
        System.out.println("Converted Date : " + music.getConvertedDate());
        music.setReleaseYear(music.getConvertedDate());
        //Convert String date To Loacl Date

        //Recover the correct category;
        Optional<Category> categoryResult = categoryRepo.findById(musicForm.getCategoryId());
        Category category = categoryResult.get();
        music.setCategory(category);

        //Recover the correct genre
        Iterable<Genre> genreResult = genreRepo.findAllById(musicForm.getGenre());
        //Method 1 : updating genre by repling the current ones by others
        Set<Genre> genreSet = new HashSet<>();
        genreResult.forEach(g -> {
            genreSet.add(g);
        });
        music.setGenres(genreSet);

        //Useless
        //musicRepo.save(music);
        return music;
    }

    @Override
    public Music addGenreMusic(MusicForm musicForm, long musicId) {
        Optional<Music> musicResult = musicRepo.findById(musicId);//musicId
        Music music = musicResult.get();
        music.setAlbum(musicForm.getAlbum());
        music.setBand(musicForm.getBand());

        //Convert String date To Loacl Date
        music.setConvertedDate(musicForm.getReleaseYear());
        System.out.println("Converted Date : " + music.getConvertedDate());
        music.setReleaseYear(music.getConvertedDate());
        //Convert String date To Loacl Date

        //Recover the correct category;
        Optional<Category> categoryResult = categoryRepo.findById(musicForm.getCategoryId());
        Category category = categoryResult.get();
        music.setCategory(category);

        //Recover the correct genre
        Iterable<Genre> genreResult = genreRepo.findAllById(musicForm.getGenre());

        //Method 2 : add genre to existing ones
        genreResult.forEach(g -> {
            music.getGenres().add(g);
        });

        return music;
    }

    @Override
    public void deleteMusic(long musicId) {
        musicRepo.deleteById(musicId);
    }

    @Override
    public List<Music> showMusic() {
        Iterable<Music> musicResult = musicRepo.findAll();
        List<Music> musicList = new ArrayList<>();
        musicResult.forEach(m -> musicList.add(m));
        return musicList;
    }

    @Override
    public Music showMusicById(long id) {
        Optional<Music> musicResult = musicRepo.findById(id);
        Music music = musicResult.get();
        return music;
    }
}
