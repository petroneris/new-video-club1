package com.snezana.videoclub.dao;

import java.util.List;

import com.snezana.videoclub.model.Film;
import com.snezana.videoclub.model.User;

/**
 * DAO interface for Admin operations.
 */
public interface AdminDao {

	/**
     * Adds user to database.
     * @param user {@link User} with data (except id) that will be added to
     * database.
     */
	void addUser(User user);

	 /**
     * Deletes user from database.
     * @param username {@link User}'s unique username.
     */
	void deleteUser(String username);

	/**
     * Returns list of all users.
     * @return 
     */
	List<User> usersList();

	/**
     * Returns list of all admins.
     * @return 
     */
	List<User> adminList();

	/**
     * Retrieves {@link User} from database.
     * @param username unique username.
     * @return 
     */
	User getUser(String username);

	 /**
     * Gives new {@link Role} to particular {@link User}.
     * @param username (unique) username.
     * @param rolename rolename.
     */
	void giveRoleToUser(String username, String rolename);

	/**
     * Update/edit data for the {@link User}.
     * <p>
     * This method affects only 'primary' User data, and not 'secondary', 
     * such as Roles.
     * <p>
     * User is identified by his/her unique username, and that cannot ever
     * be altered.
     * @param user {@link User} whose data is to be edited.
     */
	void updateUser(User user);

	/**
     * Returns list of {@link User}s that currently have no rented {@link Film}s.
     * @return 
     */
	List<User> userListNoRent();

	/**
     * Returns list of {@link User}s that currently do have one or more 
     * rented {@link Film}s.
     * @return 
     */
	List<User> userListWithRent();

	/**
     * Returns number of rented Films per User, for all Users in the system.
     * <p>
     * This method does not tie each number to a concrete User. Instead, 
     * numbers from this list are only used for the total number of currently
     * rented Films, for the 'Statistics' page.
     * @return 
     */
	List<Integer> listNumRentsPerUser();

	/**
     * Statistics: Average number of rented Films per User.
     * @return 
     */
	Double statsAvgFilmsPerUser();

	/**
     * Checks if a particular {@link User} has got some rented Films.
     * @param username (unique) User identifier.
     * @return {@code true} if this {@code User} does have rented Film(s),
     * {@code false} otherwise.
     */
	boolean hasGotRentedFilms(String username);

	/**
     * Checks if a particular {@link User} has got five rented Films already.
     * @param username (unique) User identifier.
     * @return {@code true} if this {@code User} does have rented five Film(s),
     * {@code false} otherwise.
     */
	boolean hasAlready5(String username);

	/**
     * Returns total number of {@link User}s currently in the system.
     * @return 
     */
	long totalNoOfUsers();

	/**
     * Adds new Film to db.
     * @param film {@link Film} object to be inserted into database.
     */
	void addFilm(Film film);

	/**
     * Searches for Film by its (unique) name and deletes it from database.
     * @param title (unique) title of the film.
     */
	void deleteFilm(String title);

	/**
     * Returns all available {@link Film}s in a list.
     * @return
     */
	List<Film> listOfFilms();

	/**
     * Searches for a {@link Film} with given name.
     * @param title (unique) Film title.
     * @return {@link Film} with given filmName, or null if it does not exist.
     */
	Film getFilm(String title);

	/**
     * Applies changes for a Film.
     * <p>
     * Film is matched by its {@link Film#name} which is to be unique, and
     * cannot be altered for a single Film; i.e. {@link Film#name} is treated
     * the same way as {@link Film#id}.
     * @param film {@link Film} object that contains some altered data.
     */
	void updateFilm(Film film);

	/**
     * Marks the {@link Film} {@code filmName} as rented by the user {@code username}.
     * @param title title of the Film that is rented.
     * @param username username of the User that rented this Film.
     */
	void rentFilm(String title, String username);

	/**
     * Checks if a Film is rented
     * @param title a {@link Film} with given title.
     * @return {@code true} if a Film is rented, {@code false} otherwise.
     */
	boolean isRented(String title);

	/**
     * "Returns" a {@link Film} to Video Club, marking it no longer rented.
     * @param title (unique) name of the Film.
     * @return Last {@link User} that rented the Film.
     */
	User returnFilm(String title);

	/**
     * Returns all (currently) rented {@link Film}s in a List.
     * @return
     */
	List<Film> rentedFilms();

	/**
     * Returns list of all "available", i.e. not rented {@link Film}s.
     * @return
     */
	List<Film> availableFilms();

	/**
     * Counts all available films currently in database.
     * @return
     */
	long filmsCount();

	/**
     * Statistics: number of rents per {@link Film}.
     * <p>
     * These numbers are not tied to particular {@link Film}s and are used
     * only to summarize the total number of rents, or calculate statistical
     * average rents per film.
     * @return
     */
	List<Integer> rentsPerFilms();

	/**
     * Statistics: average number of rents per Film.
     * @return Statistical average value, rents-per-film.
     */
	Double avgRentsPerFilm();

}
