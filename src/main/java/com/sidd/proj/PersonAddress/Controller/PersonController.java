package com.sidd.proj.PersonAddress.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sidd.proj.PersonAddress.CustomErrorHandling.DataMatchedException;
import com.sidd.proj.PersonAddress.CustomErrorHandling.DataNotFoundException;
import com.sidd.proj.PersonAddress.CustomErrorHandling.NoRecordsAvaliable;
import com.sidd.proj.PersonAddress.Entity.Person;
import com.sidd.proj.PersonAddress.Service.PersonService;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/")
    // This will delete all the "cache" which has value "persons"
    @CacheEvict(value = "persons", allEntries = true)
    public Person save(@RequestBody Person person) throws DataMatchedException {
        return personService.savePerson(person);
    }

    @GetMapping("/")
    // This will store the "cache" with the value "persons"
    @Cacheable(value = "persons")
    public List<Person> getAll() throws NoRecordsAvaliable {
        System.err.println("Get ----------- ");
        return personService.getAll();
    }

    @GetMapping("/{id}")
    // This will store the "cache" with the value "person" and key "id"
    @Cacheable(value = "person", key = "#id")
    public Person get(@PathVariable("id") long id) throws DataNotFoundException {
        System.err.println("Get By Id ----------- ");
        return personService.get(id);
    }

    @PutMapping("/{id}")
    // This will delete the "cache" which has key "id"
    @CacheEvict(value = "person", key = "#id")
    public Person update(@RequestBody Person person, @PathVariable("id") long id) throws DataNotFoundException {
        return personService.update(person, id);
    }

    @DeleteMapping("/{id}")
    // This will delete the "cache" which has value "persons" and key "id"
    @Caching(evict = {
            @CacheEvict(value = "person", key = "#id"),
            @CacheEvict(value = "persons", allEntries = true)
    })
    public Person delete(@PathVariable("id") long id) throws DataNotFoundException {
        return personService.delete(id);
    }

}
