package com.tcs.User.and.post.rest.service.Jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.User.and.post.rest.service.User.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
public User findUserById(Integer id);

@Override 
public List<User> findAll();
}

