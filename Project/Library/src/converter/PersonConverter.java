package converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data_model.PersonTable;
import model.FileInfo;
import model.Person;

// Convert PeronTable to Person

public class PersonConverter implements IConverter<PersonTable, Person> {

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
	public List<Person> convert(List<PersonTable> personTable_List) {

		List<Person> Person_List = new ArrayList<>();

		for (PersonTable personTable : personTable_List) {
			Person_List.add(convert(personTable));
		}

		return Person_List;
	}

	@Override
	public PersonTable revert(Person person) {

		// Default avatar
		String avatar = "sources//default/avatars//default_avatar.png";
		// Check exist avatar
		if (person.getAvatar() != null)
			avatar = "sources//users//" + person.getUsername() + person.getAvatar().getName();

		PersonTable Person = new PersonTable(person.getId(), person.getUsername(), person.getPassword(),
				person.getName(), person.getMale(), person.getPhonenumber(), (Date) person.getDateofbirth(), avatar);

		return Person;
	}

	@Override
	public List<PersonTable> revert(List<Person> Person_List) {

		List<PersonTable> personTable_List = new ArrayList<>();

		for (Person person : Person_List) {
			personTable_List.add(revert(person));
		}

		return personTable_List;
	}

}
