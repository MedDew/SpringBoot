package com.springMVC.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
//                BCryptPasswordEncoder encodedPW = new BCryptPasswordEncoder();
//                System.out.println("Pass : "+encodedPW.encode("pass"));
//                System.out.println("Word1 : "+encodedPW.encode("word1"));
		SpringApplication.run(LibraryApplication.class, args);
	}
}
