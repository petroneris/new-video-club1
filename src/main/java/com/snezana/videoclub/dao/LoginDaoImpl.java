package com.snezana.videoclub.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.snezana.videoclub.model.User;

/**
 * @see LoginDao
 */
@Repository("loginDao")
public class LoginDaoImpl extends AbstractDao<Integer, User> implements LoginDao {

	public void save(User user) {
		persist(user);
	}

	public User findById(int id) {
		return getByKey(id);
	}

	public User findByUsername(String username) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", username));
		return (User) crit.uniqueResult();
	}

}
