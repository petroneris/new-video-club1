package com.snezana.videoclub.dao;

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
import com.snezana.videoclub.model.User;

/**
 * @see UserDao
 */
@Transactional
public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;

	public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
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
	public List<Film> filmsRentedByUser(String username) {
		List<Film> flmsPerUserList = new ArrayList<>();
		Session session = sessionFactory.getCurrentSession();
		String query = "FROM User WHERE username='" + username + "'";
		Query q = session.createQuery(query);
		User u = (User) q.uniqueResult();
		for (Film f : u.getFilms()) {
			flmsPerUserList.add(f);
		}
		return flmsPerUserList;
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
	public void updateUserPassword(User user) {
		Session session = sessionFactory.getCurrentSession();
		String query = "UPDATE User SET password='" + user.getPassword() + "'" + "WHERE username='" + user.getUsername()
				+ "'";
		Query q = session.createQuery(query);
		q.executeUpdate();
	}

}
