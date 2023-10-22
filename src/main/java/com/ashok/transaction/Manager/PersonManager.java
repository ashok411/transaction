package com.ashok.transaction.Manager;

import com.ashok.transaction.entity.Person;
import com.ashok.transaction.exception.MyCustomException;
import com.ashok.transaction.repository.IPersonRepository;
import javax.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("personManager")
@Transactional
@NoArgsConstructor
public class PersonManager {
    @Autowired
  private IPersonRepository personRepository;


  public Person save(Person person) throws MyCustomException {
     Person person1 = personRepository.save(person);
     return person1;



  }


}
