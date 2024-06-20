package com.sidd.proj.PersonAddress.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sidd.proj.PersonAddress.Entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
