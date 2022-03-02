package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the USERR database table.
 * 
 */
@Entity
@Table(name="USERR")
@NamedQuery(name="Userr.findAll", query="SELECT u FROM Userr u")
public class Userr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USERR_USERID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USERR_USERID_GENERATOR")
	@Column(name="USER_ID", unique=true, nullable=false, precision=10)
	private long userId;

	@Column(name="USER_EMAIL", nullable=false, length=255)
	private String userEmail;

	@Column(name="USER_NAME", nullable=false, length=255)
	private String userName;

	@Column(name="USER_PASSWORD", nullable=false, length=50)
	private String userPassword;

	//bi-directional many-to-one association to Company
	@OneToMany(mappedBy="userr")
	private List<Company> companies;

	//bi-directional many-to-one association to Person
	@OneToMany(mappedBy="userr")
	private List<Person> persons;

	//bi-directional many-to-one association to Rolee
	@OneToMany(mappedBy="userr")
	private List<Rolee> rolees;

	//bi-directional many-to-one association to Company
	@ManyToOne
	@JoinColumn(name="COMPANY_COMP_ID")
	private Company company;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="PERSON_PERS_ID")
	private Person person;

	public Userr() {
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public List<Company> getCompanies() {
		return this.companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public Company addCompany(Company company) {
		getCompanies().add(company);
		company.setUserr(this);

		return company;
	}

	public Company removeCompany(Company company) {
		getCompanies().remove(company);
		company.setUserr(null);

		return company;
	}

	public List<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public Person addPerson(Person person) {
		getPersons().add(person);
		person.setUserr(this);

		return person;
	}

	public Person removePerson(Person person) {
		getPersons().remove(person);
		person.setUserr(null);

		return person;
	}

	public List<Rolee> getRolees() {
		return this.rolees;
	}

	public void setRolees(List<Rolee> rolees) {
		this.rolees = rolees;
	}

	public Rolee addRolee(Rolee rolee) {
		getRolees().add(rolee);
		rolee.setUserr(this);

		return rolee;
	}

	public Rolee removeRolee(Rolee rolee) {
		getRolees().remove(rolee);
		rolee.setUserr(null);

		return rolee;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
