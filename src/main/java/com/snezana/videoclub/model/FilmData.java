package com.snezana.videoclub.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Placeholder for {@link Film} class. Used to transfer Film data to view in
 * objects that are not Hibernate-poisoned.
 */
public class FilmData {

	private Integer id;
	private String title;
	private String genre;
	private Integer year;
	private Long timeFilmrent = Film.NOT_RENTED;
	private Integer nRentperfilm = 0;
	private Set<User> users = new HashSet<>();

	/**
	 * Do not allow for default constructor - this class should be initialized from
	 * our code only.
	 */
	@SuppressWarnings("unused")
	private FilmData() {
	}

	public FilmData(Integer id, String title, String genre, Integer year, Long timeFilmRent, Integer nRentPerFilm,
			Set<User> users) {
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.year = year;
		this.timeFilmrent = timeFilmRent;
		this.nRentperfilm = nRentPerFilm;
		if (users != null) {
			setUsers(users);
		}
	}

	public FilmData(Film film) {
		this.id = new Integer(film.getId());
		this.title = new String(film.getTitle());
		this.genre = new String(film.getGenre());
		this.year = new Integer(film.getYear());
		this.timeFilmrent = new Long(film.getTimeFilmrent());
		this.nRentperfilm = new Integer(film.getnRentperfilm());
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
	public String toString() {
		return "FilmData [id=" + id + ", title=" + title + ", genre=" + genre + ", year=" + year + ", timeFilmrent="
				+ timeFilmrent + ", nRentperfilm=" + nRentperfilm + "]";
	}

}
