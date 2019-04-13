package com.snezana.videoclub.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Placeholder for {@link User} class. Used to transfer User data to view in
 * objects that are not Hibernate-poisoned.
 */
public class UserData {

	private Integer id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String telephone;
	private Integer nRentperuser = 0;
	private Set<Role> roles = new HashSet<Role>(0);
	private Set<Film> films = new HashSet<Film>(0);

	/**
	 * Do not allow for default constructor - this class should be initialized
	 * from our code only.
	 */
	@SuppressWarnings("unused")
	private UserData() {}

	public UserData(User user){
		this.id = new Integer(user.getId());
		this.username = new String(user.getUsername());
		this.password = new String(user.getPassword());
		this.firstName = new String(user.getFirstName());
		this.lastName = new String(user.getLastName());
		this.email = new String(user.getEmail());
		this.telephone = new String(user.getTelephone());
		this.nRentperuser = new Integer(user.getnRentperuser());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getnRentperuser() {
		return nRentperuser;
	}

	public void setnRentperuser(Integer nRentperuser) {
		this.nRentperuser = nRentperuser;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Film> getFilms() {
		return films;
	}

	public void setFilms(Set<Film> films) {
		this.films = films;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", telephone=" + telephone + ", nRentperuser="
				+ nRentperuser + "]";
	}

}
