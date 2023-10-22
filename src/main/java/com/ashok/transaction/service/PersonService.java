package com.ashok.transaction.service;

import com.ashok.transaction.Manager.PersonManager;
import com.ashok.transaction.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonService {
    private final PersonManager personManager;

    @Autowired
    public PersonService(PersonManager personManager) {
        this.personManager = personManager;
    }

    public Person save(Person person){
       return personManager.save(person);
    }

    // Implement your business logic for Person here if needed
}
