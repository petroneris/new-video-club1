package com.snezana.videoclub.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "film", catalog = "video_club")
public class Film {

	public static final long NOT_RENTED = -1;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "title", length = 255)
	private String title;

	@Column(name = "genre", length = 45)
	private String genre;

	@Column(name = "year")
	private Integer year;

	@Column(name = "time_filmrent")
	private Long timeFilmrent = NOT_RENTED;

	@Column(name = "n_rentperfilm")
	private Integer nRentperfilm = 0;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_film", catalog = "video_club", joinColumns = {
			@JoinColumn(name = "film_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "user_id", nullable = false, updatable = false) })
	private Set<User> users = new HashSet<User>(0);

	public Film() {
	}

	public Film(String title, String genre, Integer year) {
		this.title = title;
		this.genre = genre;
		this.year = year;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Long getTimeFilmrent() {
		return timeFilmrent;
	}

	public void setTimeFilmrent(Long timeFilmrent) {
		this.timeFilmrent = timeFilmrent;
	}

	public Integer getnRentperfilm() {
		return nRentperfilm;
	}

	public void setnRentperfilm(Integer nRentperfilm) {
		this.nRentperfilm = nRentperfilm;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nRentperfilm == null) ? 0 : nRentperfilm.hashCode());
		result = prime * result + ((timeFilmrent == null) ? 0 : timeFilmrent.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		// result = prime * result + ((users == null) ? 0 : users.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		Film other = (Film) obj;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nRentperfilm == null) {
			if (other.nRentperfilm != null)
				return false;
		} else if (!nRentperfilm.equals(other.nRentperfilm))
			return false;
		if (timeFilmrent == null) {
			if (other.timeFilmrent != null)
				return false;
		} else if (!timeFilmrent.equals(other.timeFilmrent))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", genre=" + genre + ", year=" + year + ", timeFilmrent="
				+ timeFilmrent + ", nRentperfilm=" + nRentperfilm + "]";
	}

}
