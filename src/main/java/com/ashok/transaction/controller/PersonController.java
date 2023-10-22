package com.ashok.transaction.controller;

import com.ashok.transaction.entity.Person;
import com.ashok.transaction.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
public class PersonController {

  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  // Implement CRUD operations for Person here (POST, GET, PUT, DELETE)

  // Save a new person
  @PostMapping
  public ResponseEntity<Person> savePerson(@RequestBody Person person) {
    Person savedPerson = personService.save(person);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
  }
}
