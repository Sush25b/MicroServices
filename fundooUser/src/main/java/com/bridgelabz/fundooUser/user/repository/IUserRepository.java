package com.bridgelabz.fundooUser.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bridgelabz.fundooUser.user.model.User;

public interface IUserRepository extends JpaRepository<User, Long>
{
	//creating a new method
	public Optional<User> findByEmailid(String emailid);
	
	//creating a new method
	public Optional<User> findByid(Long id);
}
