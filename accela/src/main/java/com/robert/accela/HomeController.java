package com.robert.accela;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin
public class HomeController {

    @Autowired
	private JdbcTemplate jdbcTemplate;
    
    @GetMapping("/")
    public String home(Model model) {
        return "Hello from Spring Boot";
    }
    
    @GetMapping("/person")
    public String select() {
        InteractTables.selectPerson();
        return "Hello from Spring Boot";
    }
    
    @GetMapping("/person/count")
    public String count() {
        InteractTables.countPersons();
        return "Hello from Spring Boot";
    }


    
    @PostMapping(path = "/person/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity add(@RequestBody Person person) {

        System.out.println("person.checkNull(): " + person.checkNull());

        if (person.checkNull()) {
            System.out.println("Missing data. Try again.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        System.out.println("add person " + person);

        InteractTables.insertPerson(person);
    
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/person/delete/{id}")
    public ResponseEntity add(@PathVariable("id") Integer id) {
        System.out.println("delete id " + id);
        InteractTables.deletePerson(id);
    
        return new ResponseEntity<>(HttpStatus.OK);
    }
    

}