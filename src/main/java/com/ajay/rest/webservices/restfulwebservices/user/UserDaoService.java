package com.ajay.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	// JPA/Hibernate > Database
	// UserDaoService > Static List
	
	private static List<User> users = new ArrayList<>();
	private static int usersCount = 0;
	
	static {
		users.add(new User(++usersCount, "Ajay", LocalDate.now().minusYears(28)));
		users.add(new User(++usersCount, "Vijay", LocalDate.now().minusYears(35)));
		users.add(new User(++usersCount, "Sujay", LocalDate.now().minusYears(25)));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User findOne(int id)
	{
		//return users.stream().filter(user -> user.getId().equals(id)).findFirst().get();
		return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
	}
	
	public User save(User user)
	{
		user.setId(++usersCount);
		users.add(user);
		return user;
	}
	
	//Delete a user by ID
	public void deleteById(int id)
	{
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
	}


}
