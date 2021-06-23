package LDAPAMPLITUDE.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

import LDAPAMPLITUDE.Repository.PersonRepository;

@Configuration
@EnableLdapRepositories
public class Config {
	
	@Bean
	public LdapContextSource ldapContextSource() {
		LdapContextSource lcs = new LdapContextSource();
		
		lcs.setUrl("ldap://Amplitude.fr/OU=AMPLITUDE,dc=Amplitude,dc=fr");
		lcs.setBase("objectClass=OrganizationalPerson");
//		lcs.setBase("OU=Utilisateurs");
		
		return lcs;
	}
	
	@Bean
	LdapTemplate ldapTemplate() {
		return new LdapTemplate(ldapContextSource());
	}
	
	@Bean
	PersonRepository repo() {
		return new PersonRepository();
	}


}
