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
		FileInfo avatar = getAvatar(personTable);
		LocalDate dateofbirth = getDateOfBirth(personTable);

		// Ctor for table in database
		Person person = new Person(personTable.getId(), personTable.getUsername(), personTable.getPassword(),
				personTable.getName(), personTable.getGender(), personTable.getPhonenumber(), dateofbirth, avatar);

		return person;
	}

	private LocalDate getDateOfBirth(PersonTable personTable) {
		// Date of birth if null will current date
		LocalDate dateofbirth = personTable.getDateofbirth() == null ? LocalDate.now() : personTable.getDateofbirth();
		return dateofbirth;
	}

	private FileInfo getAvatar(PersonTable personTable) {
		// Get avatar
		FileInfo avatar = new FileInfo(
				"sources/users/" + personTable.getUsername() + "/avatars/" + personTable.getAvatar());
		if (avatar.isValid() == false)
			avatar = new FileInfo("sources/default/avatars/default_avatar.png");

		return avatar;
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

		LocalDate dateofbirth = checkDateOfBirth(person);
		writeAvatar(person);

		PersonTable Person = new PersonTable(person.getId(), person.getUsername(), person.getPassword(),
				person.getName(), person.getMale(), person.getPhonenumber(), dateofbirth, person.getAvatar().getName());

		return Person;
	}

	private LocalDate checkDateOfBirth(Person person) {
		// Date of birth if null will get current date
		LocalDate dateofbirth = person.getDateofbirth() == null ? LocalDate.now() : person.getDateofbirth();
		return dateofbirth;
	}

	private void writeAvatar(Person person) {
		// Get avatar
		person.getAvatar().getFile("sources/users/" + person.getUsername() + "/avatars/");
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
