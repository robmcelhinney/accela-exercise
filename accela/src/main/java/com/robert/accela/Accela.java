package com.robert.accela;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Accela {

	public static void main(String[] args) {
		CreateTables.CreateBothTables();
		SpringApplication.run(Accela.class, args);
	}

}
