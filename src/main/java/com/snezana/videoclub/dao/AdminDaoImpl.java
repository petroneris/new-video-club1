package com.snezana.videoclub.dao;

import static com.snezana.videoclub.model.Film.NOT_RENTED;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.snezana.videoclub.model.Film;
import com.snezana.videoclub.model.Role;
import com.snezana.videoclub.model.User;

/**
 * @see AdminDao
 */
@Transactional
public class AdminDaoImpl implements AdminDao {	

	private SessionFactory sessionFactory;

	public AdminDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

	@Override
	public void deleteUser(String username) {
		Session session = sessionFactory.getCurrentSession();
		String query = "DELETE FROM User WHERE username = '" + username + "'";
		Query q = session.createQuery(query);
		q.executeUpdate();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> usersList() {
		int i = 0;
		Session session = sessionFactory.getCurrentSession();
		String query = "SELECT u FROM User u JOIN u.roles r WHERE r.name='USER'";
		Query q = session.createQuery(query);
		List<User> users = q.list();
		for (User u : users) {
			if (u.getFilms().size() > 0) {
				i++;
			}
			if (u.getRoles().size() > 0) {
				i++;
			}
		}
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> adminList() {
		int i = 0;
		Session session = sessionFactory.getCurrentSession();
		String query = "SELECT u FROM User u JOIN u.roles r WHERE r.name='ADMIN'";
		Query q = session.createQuery(query);
		List<User> users = q.list();
		for (User u : users) {
			if (u.getFilms().size() > 0) {
				i++;
			}
			if (u.getRoles().size() > 0) {
				i++;
			}
		}
		return users;
	}

	@Override
	public User getUser(String username) {
		int i = 0;
		Session session = sessionFactory.getCurrentSession();
		String query = "FROM User WHERE username='" + username + "'";
		Query q = session.createQuery(query);
		User u = (User) q.uniqueResult();
		if (u != null && u.getFilms().size() > 0) {
			i++;
		}
		return u;
	}

	@Override
	public void giveRoleToUser(String username, String name) {
		Session session = sessionFactory.getCurrentSession();
		String query = "FROM User WHERE username='" + username + "'";
		Query q = session.createQuery(query);
		User u = (User) q.uniqueResult();
		String query1 = "FROM Role WHERE name='" + name + "'";
		Query q1 = session.createQuery(query1);
		Role r = (Role) q1.uniqueResult();
		Set<Role> e = new HashSet<>();
		e.add(r);
		u.setRoles(e);
		session.update(u);
	}

	@Override
	public void updateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		String query = "UPDATE User SET first_name='" + user.getFirstName() + "'" + "," + "last_name='" + user.getLastName()
				+ "'" + "," + "email='" + user.getEmail() + "'" + "," + "telephone='" + user.getTelephone() + "'"
				+ "WHERE username='" + user.getUsername() + "'";
		Query q = session.createQuery(query);
		q.executeUpdate();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> userListNoRent() {
		List<User> freeUsers = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		String query = "SELECT u FROM User u JOIN u.roles r WHERE r.name='USER'";
		Query q = session.createQuery(query);
		List<User> users = q.list();
		for (User u : users) {
			if (u.getFilms().isEmpty()) {
				freeUsers.add(u);
			}
		}
		return freeUsers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> userListWithRent() {
		List<User> oblUsers = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		String query = "SELECT u FROM User u JOIN u.roles r WHERE r.name='USER'";
		Query q = session.createQuery(query);
		List<User> users = q.list();
		for (User u : users) {
			if (!u.getFilms().isEmpty()) {
				oblUsers.add(u);
			}
		}
		return oblUsers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> listNumRentsPerUser() {
		Session session = sessionFactory.getCurrentSession();
		String query = "SELECT u.nRentperuser FROM User u JOIN u.roles r WHERE r.name='USER'";
		Query q = session.createQuery(query);
		List<Integer> nrRentPrUser = q.list();
		return nrRentPrUser;
	}

	@Override
	public Double statsAvgFilmsPerUser() {
		Double result = 0.0;
		for (int i = 0; i < totalNoOfUsers(); i++) {
			result = result + listNumRentsPerUser().get(i);
		}
		if (totalNoOfUsers() == 0) {
			result = 0d;
		} else {
			result = result / totalNoOfUsers();
		}
		return result;
	}

	@Override
	public boolean hasGotRentedFilms(String username) {
		boolean result = false;
		Session session = sessionFactory.getCurrentSession();
		String query = "FROM User WHERE username='" + username + "'";
		Query q = session.createQuery(query);
		User k = (User) q.uniqueResult();
		result = !k.getFilms().isEmpty();
		session.update(k);
		return result;
	}

	@Override
	public boolean hasAlready5(String username) {
		boolean result = false;
		Session session = sessionFactory.getCurrentSession();
		String query = "FROM User WHERE username='" + username + "'";
		Query q = session.createQuery(query);
		User u = (User) q.uniqueResult();
		result = u.getFilms().size() == 5;
		session.update(u);
		return result;
	}

	@Override
	public long totalNoOfUsers() {
		long n = 0;
		Session session = sessionFactory.getCurrentSession();
		String query = "SELECT COUNT(*) FROM User u JOIN u.roles r WHERE r.name='USER'";
		Query q = session.createQuery(query);
		n = (long) q.uniqueResult();
		return n;
	}

	@Override
	public void addFilm(Film film) {
		Session session = sessionFactory.getCurrentSession();
		session.save(film);

	}

	@Override
	public void deleteFilm(String title) {
		Session session = sessionFactory.getCurrentSession();
		String query = "DELETE FROM Film WHERE title = '" + title + "'";
		Query q = session.createQuery(query);
		q.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Film> listOfFilms() {
		int i = 0;
		Session session = sessionFactory.getCurrentSession();		
		String query = "FROM Film";
		Query q = session.createQuery(query);
		List<Film> films = q.list();
		for (Film f : films) {
			if (f.getUsers().size() > 0) {
				i++;
			}
		}
		return films;
	}

	@Override
	public Film getFilm(String title) {
		int i = 0;
		Session session = sessionFactory.getCurrentSession();
		String query = "FROM Film WHERE title='" + title + "'";
		Query q = session.createQuery(query);
		Film f = (Film) q.uniqueResult();
		if (f != null && f.getUsers().size() > 0) {
			i++;
		}
		return f;
	}

	@Override
	public void updateFilm(Film film) {
		Session session = sessionFactory.getCurrentSession();
		String query = "UPDATE Film SET genre='" + film.getGenre() + "'" + "," + "year='" + film.getYear() + "'"
				+ "WHERE title='" + film.getTitle() + "'";
		Query q = session.createQuery(query);
		q.executeUpdate();

	}

	@Override
	public void rentFilm(String title, String username) {
		Calendar cal = Calendar.getInstance();
		long timestamp = cal.getTimeInMillis();
		Session session = sessionFactory.getCurrentSession();
		String query = "FROM Film WHERE title='" + title + "'";
		Query q = session.createQuery(query);
		Film f = (Film) q.uniqueResult();
		String query1 = "FROM User WHERE username='" + username + "'";
		Query q1 = session.createQuery(query1);
		User u = (User) q1.uniqueResult();
		Set<User> e = new HashSet<>();
		e.add(u);
		f.setUsers(e);
		f.setTimeFilmrent(timestamp);
		f.setnRentperfilm(f.getnRentperfilm() + 1);
		u.setnRentperuser(u.getnRentperuser() + 1);
		session.update(f);
		session.update(u);

	}

	@Override
	public boolean isRented(String title) {
		boolean result = false;
		Session session = sessionFactory.getCurrentSession();
		String query = "FROM Film WHERE title='" + title + "'";
		Query q = session.createQuery(query);
		Film f = (Film) q.uniqueResult();
		result = !f.getUsers().isEmpty();
		session.update(f);
		return result;
	}

	@Override
	public User returnFilm(String title) {
		Session session = sessionFactory.getCurrentSession();
		String query = "FROM Film WHERE title='" + title + "'";
		Query q = session.createQuery(query);
		Film f = (Film) q.uniqueResult();
		String username = "";
		for (User u : f.getUsers()) {
			username = u.getUsername();
			break;
		}
		String query1 = "FROM User WHERE username='" + username + "'";
		Query q1 = session.createQuery(query1);
		User u = (User) q1.uniqueResult();
		Set<User> e = new HashSet<>(0);
		f.setUsers(e);
		f.setTimeFilmrent(NOT_RENTED);
		session.update(f);
		return u;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Film> rentedFilms() {
		List<Film> rentFilmsList = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		String query = "FROM Film";
		Query q = session.createQuery(query);
		List<Film> films = q.list();
		for (Film f : films) {
			if (!f.getUsers().isEmpty()) {
				rentFilmsList.add(f);
			}
		}
		return rentFilmsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Film> availableFilms() {
		List<Film> frFilmsList = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		String query = "FROM Film";
		Query q = session.createQuery(query);
		List<Film> films = q.list();
		for (Film f : films) {
			if (f.getUsers().isEmpty()) {
				frFilmsList.add(f);
			}
		}
		return frFilmsList;
	}

	@Override
	public long filmsCount() {
		long n = 0;
		Session session = sessionFactory.getCurrentSession();
		String query = "SELECT COUNT(*) FROM Film";
		Query q = session.createQuery(query);
		n = (long) q.uniqueResult();
		return n;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> rentsPerFilms() {
		Session session = sessionFactory.getCurrentSession();
		String query = "SELECT nRentperfilm FROM Film";
		Query q = session.createQuery(query);
		List<Integer> nrRentPrFilmsList = q.list();
		return nrRentPrFilmsList;
	}

	@Override
	public Double avgRentsPerFilm() {
		Double result = 0.0;
		for (int i = 0; i < rentsPerFilms().size(); i++) {
			result = result + rentsPerFilms().get(i);
		}
		if (filmsCount() == 0) {
			result = 0d;
		} else {
			result = result / filmsCount();
		}
		return result;
	}

}
