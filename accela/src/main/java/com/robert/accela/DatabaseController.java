package com.robert.accela;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
public class DatabaseController {

    @GetMapping("/")
    public String home(Model model) {
        return "Hello from Robert McElhinney";
    }

    @GetMapping("/person")
    public List<Person> select() {
        List<Person> persons = InteractTables.selectPerson();
        return persons;
    }

    @GetMapping("/person/count")
    public Map<String, Integer> count() {
        Map<String, Integer> object = new HashMap<String, Integer>();
        object.put("total", InteractTables.countPersons());
        return object;
    }

    @PostMapping(path = "/person/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addPerson(@RequestBody Person person) {
        if (person.checkNull()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        InteractTables.insertPerson(person);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/person/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> updatePerson(@RequestBody Person person) {
        if (person.checkNull()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!InteractTables.checkPersonExists(person.getId())) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Person does not exist ");
        }

        InteractTables.updatePerson(person);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/person/delete/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable("id") Integer id) {
        
        if (!InteractTables.checkPersonExists(id)) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Person does not exist ");
        }
        InteractTables.deletePerson(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/address/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addAddress(@RequestBody Address address) {
        if (address.checkNull()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!InteractTables.checkPersonExists(address.getPersonId())) {
            return new ResponseEntity<>("Person does not exist", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (InteractTables.checkAddressExists(address.getId())) {
            return new ResponseEntity<>("Address already exists", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        InteractTables.insertAddress(address);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/address/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> updateAddress(@RequestBody Address address) {
        if (address.checkNull()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!InteractTables.checkPersonExists(address.getPersonId())) {
            return new ResponseEntity<>("Person does not exist", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        InteractTables.updateAddress(address);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/address/delete/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable("id") Integer id) {
        InteractTables.deleteAddress(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}