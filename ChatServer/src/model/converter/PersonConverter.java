package model.converter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import data_model.PersonTable;
import model.sendmodel.FileInfo;
import model.sendmodel.Person;

// Convert PeronTable to Person

public class PersonConverter implements Converter<PersonTable, Person> {

	@Override
	public Person convert(PersonTable personTable) {
		Person person = null;
		
		if(personTable != null)
		{
			// Get avatar
			FileInfo avatar = new FileInfo(
					"sources/users/" + personTable.getUsername() + "/avatars/" + personTable.getAvatar());
			
			// Date of birth if null will current date
			LocalDate dateofbirth = personTable.getDateofbirth() == null ? LocalDate.now() : personTable.getDateofbirth();

			// Ctor for table in database
			person = new Person(personTable.getId(), personTable.getUsername(), personTable.getPassword(),
					personTable.getName(), personTable.getGender(), personTable.getPhonenumber(),
					dateofbirth, avatar);
		}
		return person;
	}

	@Override
	public List<Person> convert(List<PersonTable> personTableList) {

		List<Person> personList = new ArrayList<>();

		for (PersonTable personTable : personTableList) {
			personList.add(convert(personTable));
		}

		return personList;
	}

	@Override
	public PersonTable revert(Person person) {

		// Date of birth if null will get current date
		LocalDate dateofbirth = person.getDateofbirth() == null ? LocalDate.now() : person.getDateofbirth();
		// Get avatar
		person.getAvatar().getFile("sources/users/" + person.getUsername() + "/avatars/");
		
		PersonTable Person = new PersonTable(person.getId(), person.getUsername(), person.getPassword(),
				person.getName(), person.getMale(), person.getPhonenumber(), dateofbirth,
				person.getAvatar().getName());

		return Person;
	}

	@Override
	public List<PersonTable> revert(List<Person> personList) {

		List<PersonTable> personTableList = new ArrayList<>();

		for (Person person : personList) {
			personTableList.add(revert(person));
		}

		return personTableList;
	}

}
