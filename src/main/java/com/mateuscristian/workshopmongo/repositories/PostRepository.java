package com.mateuscristian.workshopmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mateuscristian.workshopmongo.entities.Post;
import com.mateuscristian.workshopmongo.entities.User;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	}
