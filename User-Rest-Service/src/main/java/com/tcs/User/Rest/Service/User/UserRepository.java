package com.tcs.User.Rest.Service.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
public User findUserById(Integer id);

@Override 
public List<User> findAll();
}
