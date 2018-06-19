package com.springMVC.library.component;

import com.springMVC.library.entities.Music;
import com.springMVC.library.form.MusicForm;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class InitMusicFormComponent {

    public void init(MusicForm musicForm, Music music) 
    {
        Set<Long> genreIds = new HashSet<>();
        music.getGenres().forEach(genre -> {
            genreIds.add(genre.getId());
        });
        
        musicForm.setAlbum(music.getAlbum());
        musicForm.setBand(music.getBand());
        musicForm.setReleaseYear(music.getReleaseYear().toString());
        musicForm.setCategoryId(music.getCategory().getId());
        musicForm.setGenre(genreIds);
    }
}
