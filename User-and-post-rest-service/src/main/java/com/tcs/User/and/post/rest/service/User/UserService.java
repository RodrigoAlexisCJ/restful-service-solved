package com.tcs.User.and.post.rest.service.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.User.and.post.rest.service.Jpa.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> getAll(){
		return repository.findAll();
	}
	
	public User getUser(int id) {
		return repository.findUserById(id);
	}
	
	public User createUser(User us) {
		return repository.save(us);
	}
	public void deleteUser(int id) {
		repository.deleteById(id);
	}
}

