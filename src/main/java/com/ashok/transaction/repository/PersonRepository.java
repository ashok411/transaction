package com.ashok.transaction.repository;


import com.ashok.transaction.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository("person_repository")
public class PersonRepository extends GenericRepository<Person, String> implements
    IPersonRepository  {

    public PersonRepository() {
    super(Person.class);
  }


}
