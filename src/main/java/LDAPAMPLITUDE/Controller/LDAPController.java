package LDAPAMPLITUDE.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import LDAPAMPLITUDE.Model.Person;
import LDAPAMPLITUDE.Repository.PersonRepository;

@RestController
@RequestMapping("/auth")
public class LDAPController {
	@Autowired
	private PersonRepository repo;
	@PostMapping("/login")
	public boolean auth(@RequestBody Person person) {
		return repo.authentification(person.getName(), person.getPassword());
	}
}
