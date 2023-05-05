package com.example.springbootwithmockito.controller;


import com.example.springbootwithmockito.entity.Person;
import com.example.springbootwithmockito.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person/")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @PostMapping("save")
    public Person save(@RequestBody Person person){
        return personRepository.addPerson(person);
    }

    @GetMapping("getPerson/{personId}")
    public Person findPerson(@PathVariable String personId){
        return personRepository.findPersonById(personId);
    }

    @DeleteMapping("deletePerson")
    public String deletePerson(Person person){
        return personRepository.deletePerson(person);
    }

    @PutMapping("update")
    public String updatePerson(@RequestBody Person person){
        return personRepository.editPerson(person);
    }
}
