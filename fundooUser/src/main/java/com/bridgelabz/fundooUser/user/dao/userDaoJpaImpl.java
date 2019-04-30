/*

package com.bridgelabz.fundooNotes.user.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundooNotes.user.model.User;


@Repository
public class userDaoJpaImpl implements userDao 
{

	private EntityManager entityManager;
	
	@Autowired
	public userDaoJpaImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<User> findAll() {

		// create a query
		Query theQuery = 
				entityManager.createQuery("from User");
		
		// execute query and get result list
		List<User> Users = theQuery.getResultList();
		
		// return the results		
		return Users;
	}

	@Override
	public User findById(int theId) {

		// get User
		User theUser = 
				entityManager.find(User.class, theId);
		
		// return User
		return theUser;
	}

	@Override
	public void save(User theUser) {

		// save or update the user
		User dbUser = entityManager.merge(theUser);
		
		// update with id from db ... so we can get generated id for save/insert
		theUser.setId(dbUser.getId());
		
	}

	@Override
	public void deleteById(int theId) {

		// delete object with primary key
		Query theQuery = entityManager.createQuery(
							"delete from User where id=:userId");
		
		theQuery.setParameter("userId", theId);
		
		theQuery.executeUpdate();
	}

}
*/
