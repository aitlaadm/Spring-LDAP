package LDAPAMPLITUDE.Repository;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AuthenticatedLdapEntryContextMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapEntryIdentification;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQueryBuilder;

public class PersonRepository {

	@Autowired 
	private LdapTemplate ldapTemplate;
	
	public boolean authentification(String name, String password) {
		boolean flag=false;
		try {
			AuthenticatedLdapEntryContextMapper<DirContextOperations> mapper = (DirContext ctx,
					LdapEntryIdentification ldapEntryIdentification)->{

					try {
						return (DirContextOperations) ctx.lookup(ldapEntryIdentification.getRelativeName());
					} catch (NamingException e) {
						// TODO Auto-generated catch block
						throw new RuntimeException("Echec de look up : "+ldapEntryIdentification.getRelativeName(),e);
					}
					};
			DirContextOperations dco=ldapTemplate.authenticate(LdapQueryBuilder.query().where("Name").is(name), password, mapper);
			flag=((dco!=null)&&(dco.getStringAttribute("Name").equals(name)));
		}catch(Exception e) {
			System.out.println("Echec d'Authentification "+e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
}
