package model.converter;

import java.util.ArrayList;
import java.util.List;

import data_model.PersonTable;
import model.sendmodel.FileInfo;
import model.sendmodel.Person;

// Convert PeronTable to Person

public class PersonConverter implements Converter<PersonTable, Person> {

	@Override
	public Person convert(PersonTable personTable) {

		// Get avatar
		FileInfo avatar = new FileInfo(personTable.getAvatar());
		// Ctor for table in database
		Person person = new Person(personTable.getId(), personTable.getUsername(), personTable.getPassword(),
				personTable.getName(), personTable.getGender(), personTable.getPhonenumber(),
				personTable.getDateofbirth(), avatar);

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

		// Default avatar
		String avatar = "sources//default/avatars//default_avatar.png";
		// Check exist avatar
		if (person.getAvatar() != null)
			avatar = "sources//users//" + person.getUsername() + person.getAvatar().getName();

		PersonTable Person = new PersonTable(person.getId(), person.getUsername(), person.getPassword(),
				person.getName(), person.getMale(), person.getPhonenumber(), person.getDateofbirth(), avatar);

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
