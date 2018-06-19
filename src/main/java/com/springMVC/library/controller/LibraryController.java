/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springMVC.library.controller;

import com.springMVC.library.services.CategoryManager;
import com.springMVC.library.services.GenreManager;
import com.springMVC.library.services.LibraryManager;
import com.springMVC.library.entities.Music;
import com.springMVC.library.form.MusicForm;
import com.springMVC.library.component.InitMusicFormComponent;
import com.springMVC.library.entities.User;
import com.springMVC.library.services.UserManager;
import java.io.IOException;

import java.util.List;
import java.util.NoSuchElementException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

/**
 *
 * @author Mehdi
 */
@Controller
@RequestMapping("/music")
public class LibraryController 
{

    @Autowired
    private CategoryManager categoryService;

    @Autowired
    private GenreManager genreService;

    @Autowired
    private LibraryManager libraryService;
    
    @Autowired
    private InitMusicFormComponent initMusicFormComponent;
    
    @Autowired
    private UserDetailsService userSecurityService;
    
    @Autowired
    private UserManager userService;
    

    @GetMapping("/musicList")
    public String showMusicList(Model model) {
        List<Music> musicList = libraryService.showMusic();

        model.addAttribute(musicList);
        return "musicList";
    }
    
//    @Secured({"ROLE_USER"})
    @GetMapping("/secure/add")
    public String showFormMusic(MusicForm musicForm, Model model, HttpServletRequest request)//
    {
        model.addAttribute("categorySet", categoryService.showCategory());
        model.addAttribute("genreSet", genreService.showGenre());
        
        UserDetails user = userSecurityService.loadUserByUsername("MedGaz");
        System.err.println("LibraryController::showFormMusic");
        System.out.println("UserName : "+user.getUsername()+" Password :"+user.getPassword()+" : "+user.toString());
        User userToUpdate = userService.showUserByUsername(user.getUsername());
        User userUpdated = userService.updateLastLoginDateIsLoggedUser(userToUpdate);
        System.out.println("IS USER LOGGED IN : "+userUpdated.getIsLogged());
//        model.addAttribute("signedInUser", genreService.showGenre());
        System.err.println("GET showFormMusic : "+request.isUserInRole("ADMIN")+" <==> "+request.isUserInRole("USER"));
        return "musicForm";
    }
    
//    @Secured({"ROLE_USER"})
    @PostMapping("/secure/add")
    public String addMusic(@Valid MusicForm musicForm, BindingResult bindingResult, Model model, HttpServletRequest request) {//throws Exception | NOT WORKING SEE COMMENT ON THE METHOD BELOW throws HttpRequestMethodNotSupportedException
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("categorySet", categoryService.showCategory());
            model.addAttribute("genreSet", genreService.showGenre());
            return showFormMusic(musicForm, model, request);
            //return "musicForm";
        }

        musicForm.getGenre().forEach(m -> {
            System.out.println("com.springMVC.library.controller.LibraryController.addMusic() : " + m.longValue());
        });
        
        if(request.isUserInRole("USER"))
        {
            System.err.println("POST addMusic : "+request.isUserInRole("ADMIN")+" <==> "+request.isUserInRole("USER"));
            return "HttpRequestMethodNotSupportedException";
        }
        else
        {
            Music savedMusic = libraryService.insertMusic(musicForm, 
                                                          genreService.getGenreByIds(musicForm.getGenre()), 
                                                          categoryService.getCategoryById(musicForm.getCategoryId()));
            return "redirect:/music/secure/add/result/" + savedMusic.getId();
        }
        
    }

    @GetMapping("/secure/add/result/{musicId}")
    public String showAddResultMusic(@PathVariable long musicId, ModelMap model) {
        Music music = libraryService.showMusicById(musicId);

        model.addAttribute(music);
        return "musicAddResult";
    }

    @GetMapping("/secure/update/{musicId}")
    public String showUpdateFormMusic(MusicForm musicForm, @PathVariable("musicId") Long musicId, ModelMap model) throws NoSuchElementException {
        Music music = libraryService.showMusicById(musicId);
        initMusicFormComponent.init(musicForm, music);
        
        model.addAttribute("categorySet", categoryService.showCategory());
        model.addAttribute("genreSet", genreService.showGenre());
        model.addAttribute(music);
        return "musicUpdateForm";
    }

//    @PutMapping("/music/update/{musicId}")
    @PostMapping("/secure/update/{musicId}")
    public String updateMusic(@Valid MusicForm musicForm, BindingResult bindingResult, @PathVariable(name = "musicId") Long musicId, ModelMap model) {
        
        if (bindingResult.hasErrors()) {
            Music music = libraryService.showMusicById(musicId);
            
            model.addAttribute("categorySet", categoryService.showCategory());
            model.addAttribute("genreSet", genreService.showGenre());
            model.addAttribute(music);
            return showUpdateFormMusic(musicForm, musicId, model);
//            return "musicUpdateForm";
        }

        Music updatedMusic = libraryService.updateMusic(musicForm, musicId);//, genreId
        return "redirect:/music/secure/update/result/" + updatedMusic.getId();
    }

    @GetMapping("/secure/update/result/{musicId}")
    public String showUpdateResultMusic(@PathVariable long musicId, Model model) {
        Music music = libraryService.showMusicById(musicId);
        model.addAttribute(music);
        return "musicUpdateResult";
    }

    @GetMapping("/secure/update/addGenre/{musicId}")
    public String showUpdateAddGenreFormMusic(MusicForm musicForm, @PathVariable(name = "musicId") long musicId, Model model) throws NoSuchElementException {
        Music music = libraryService.showMusicById(musicId);
        initMusicFormComponent.init(musicForm, music);

        model.addAttribute("categorySet", categoryService.showCategory());
        model.addAttribute("genreSet", genreService.showGenre());
        model.addAttribute(music);
        return "musicUpdateAddGenreForm";
    }

    @PostMapping("/secure/update/addGenre/{musicId}")
    public String updateAddGenreMusic(@Valid MusicForm musicForm, BindingResult bindingResult, @PathVariable(name = "musicId") long musicId, Model model) {
        
        if (bindingResult.hasErrors()) {
            Music music = libraryService.showMusicById(musicId);

            model.addAttribute("categorySet", categoryService.showCategory());
            model.addAttribute("genreSet", genreService.showGenre());
            model.addAttribute(music);
            return showUpdateAddGenreFormMusic(musicForm, musicId, model);
//            return "musicUpdateAddGenreForm";
        }

        Music updatedAddedGenreMusic = libraryService.addGenreMusic(musicForm, musicId);
        return "redirect:/music/secure/update/addGenre/result/" + updatedAddedGenreMusic.getId();
    }

    @GetMapping(path = "/secure/update/addGenre/result/{musicId}")
    public String showUpdateAddGenreResult(@PathVariable(name = "musicId") long musicId, Model model) {
        Music music = libraryService.showMusicById(musicId);
        model.addAttribute(music);
        return "musicUpdateAddGenreResult";
    }

    @GetMapping(path = "/secure/delete/{id}")
    public String showDeletMusic(@PathVariable(name = "id") long musicId, Model model) throws NoSuchElementException {
        libraryService.deleteMusic(musicId);
        return "redirect:/music/musicList";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException e) {
        System.out.println("NoSuchElementException occured : " + e);
        return "NoSuchElementException";
    }
    
    /*
    /!\ NOT WORKING BECAUSE THIS METHOD IS CALL AFTER A SUCCESSFUL SPRING CONTROLLER HANDLER IS FOUND
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e)
    {
        System.out.println("NoSuchElementException occured : " + e);
        return "HttpRequestMethodNotSupportedException";
    }*/

    
    @GetMapping("login")
    public ModelAndView login()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("customLogin");
        
        return mav;
    }
    
    @GetMapping("accessDenied")
    public ModelAndView error()
    {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
	mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        UserDetails user = userSecurityService.loadUserByUsername("MedGaz");
        System.out.println("UserName : "+user.getUsername()+" Password :"+user.getPassword()+" : "+user.toString());
        
        return mav;
    }
}
