package LDAPAMPLITUDE.Service;

import java.util.List;

import LDAPAMPLITUDE.Model.Person;

public interface Service {

	List<Person> findByName(String nom);
}
