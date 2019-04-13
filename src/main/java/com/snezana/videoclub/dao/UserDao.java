package com.snezana.videoclub.dao;

import java.util.List;

import com.snezana.videoclub.model.Film;
import com.snezana.videoclub.model.User;

/**
 * DAO interface for User operations.
 */
public interface UserDao {

	/**
     * Retrieves {@link User} from database.
     * @param username unique username.
     * @return 
     */
	User getUser(String username);

	/**
	 * Enables change of {@link User} password by User
	 * @param user
	 */
	void updateUserPassword(User user);

	/**
     * Returns {@code List} of {@code Film}s currently rented by specific User.
     * @param username (unique) User identifier.
     * @return 
     */
	List<Film> filmsRentedByUser(String username);

	/**
     * Searches for a {@link Film} with given name.
     * @param title (unique) Film title.
     * @return {@link Film} with given filmName, or null if it does not exist.
     */
	Film getFilm(String title);

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
     * Checks if a particular {@link User} has got five rented Films already.
     * @param username (unique) User identifier.
     * @return {@code true} if this {@code User} does have rented five Film(s),
     * {@code false} otherwise.
     */
	boolean hasAlready5(String username);

	/**
     * Returns list of all "available", i.e. not rented {@link Film}s.
     * @return
     */
	List<Film> availableFilms();

}
