package com.sidd.proj.PersonAddress.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidd.proj.PersonAddress.CustomErrorHandling.DataMatchedException;
import com.sidd.proj.PersonAddress.CustomErrorHandling.DataNotFoundException;
import com.sidd.proj.PersonAddress.CustomErrorHandling.NoRecordsAvaliable;
import com.sidd.proj.PersonAddress.Entity.Address;
import com.sidd.proj.PersonAddress.Entity.Person;
import com.sidd.proj.PersonAddress.Repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public Person savePerson(Person person) throws DataMatchedException {
		return personRepository.save(person);
	}

	@Override
	public List<Person> getAll() throws NoRecordsAvaliable {
		List<Person> person = personRepository.findAll();
		if(person.isEmpty())
			throw new NoRecordsAvaliable("No Records Avaliable");
		return person;
	}

	@Override
	public Person get(long id) throws DataNotFoundException {
		boolean result = personRepository.existsById(id);
		if(!result) throw new DataNotFoundException("Data not Found");
		return personRepository.findById(id).get();
	}

	@Override
	public Person update(Person person, long id) throws DataNotFoundException {
		boolean result = personRepository.existsById(id);
		if(!result) throw new DataNotFoundException("Data not Found");
		
		Person updatePerson = personRepository.findById(id).get();
				
		if(person.getFirstName() != null & person.getFirstName() != "")
			updatePerson.setFirstName(person.getFirstName());

		if(person.getLastName() != null & person.getLastName() != "")
			updatePerson.setLastName(person.getLastName());
		
		if(person.getRollNo() != 0)
			updatePerson.setRollNo(person.getRollNo());
		
		if(person.getCourse() != null & person.getCourse() != "")
			updatePerson.setCourse(person.getCourse());

//		if(person.getAddress().getCityName() != null & person.getAddress().getCityName() != "")
//			updatePerson.setAddress();
		
		personRepository.save(updatePerson);
		
		return updatePerson;
	}

	@Override
	public Person delete(long id) throws DataNotFoundException {
		boolean result = personRepository.existsById(id);
		if(!result) throw new DataNotFoundException("Data not Found");
		
		Person person = personRepository.findById(id).get();
		personRepository.deleteById(id);
		
		return person;
	}



}
