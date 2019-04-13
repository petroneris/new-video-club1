package com.snezana.videoclub.model;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role", catalog = "video_club")
public class Role {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name", length = 45)
	private String name;

	// @ManyToMany(fetch = FetchType.LAZY)
	// @JoinTable(name = "user_role", catalog = "video_club", joinColumns = {
	// @JoinColumn(name = "rol_id", nullable = false, updatable = false) },
	// inverseJoinColumns = {
	// @JoinColumn(name = "us_id", nullable = false, updatable = false) })
	// private Set<User> users = new HashSet<User>(0);

	public Role() {
	}

	// public Role(String name, Set<User> users) {
	// this.name = name;
	// this.users = users;
	// }

	public Role(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		// result = prime * result + ((users == null) ? 0 : users.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		// if (users == null) {
		// if (other.users != null)
		// return false;
		// } else if (!users.equals(other.users))
		// return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}

}
