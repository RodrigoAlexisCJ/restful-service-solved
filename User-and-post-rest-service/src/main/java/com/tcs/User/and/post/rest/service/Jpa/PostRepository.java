package com.tcs.User.and.post.rest.service.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.User.and.post.rest.service.User.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
