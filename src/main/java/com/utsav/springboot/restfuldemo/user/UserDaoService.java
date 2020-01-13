package com.utsav.springboot.restfuldemo.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component 
public class UserDaoService {

	private static List<User> users = new ArrayList<User>();
	
	private static Integer usersCount = 3;
	
	static {
		users.add(new User(1, "Utsav", new Date()));
		users.add(new User(2, "Meher", new Date()));
		users.add(new User(3, "Rakesh", new Date()));
	}
	
	public List<User> getAllUsers(){
		return users;
	}
	
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
	
	public User findUser(int id) {
		for (User user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteUser(int id) {
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
