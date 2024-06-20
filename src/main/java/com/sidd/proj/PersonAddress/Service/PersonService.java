package com.sidd.proj.PersonAddress.Service;

import java.util.List;
import com.sidd.proj.PersonAddress.CustomErrorHandling.DataMatchedException;
import com.sidd.proj.PersonAddress.CustomErrorHandling.DataNotFoundException;
import com.sidd.proj.PersonAddress.CustomErrorHandling.NoRecordsAvaliable;
import com.sidd.proj.PersonAddress.Entity.Person;

public interface PersonService {

	Person savePerson(Person person) throws DataMatchedException;

	List<Person> getAll() throws NoRecordsAvaliable;

	Person get(long id) throws DataNotFoundException;

	Person update(Person person, long id) throws DataNotFoundException;

	Person delete(long id) throws DataNotFoundException;


}
