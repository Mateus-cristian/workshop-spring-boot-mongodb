package com.mateuscristian.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.mateuscristian.workshopmongo.entities.Post;
import com.mateuscristian.workshopmongo.repositories.PostRepository;
import com.mateuscristian.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	
	@Autowired
	private PostRepository postRepository;
	
	public List<Post> findAll(){
		return postRepository.findAll();
	}
	
	
	public Post findById(String id) {
		Optional<Post> post = postRepository.findById(id);
		
		if(post.isEmpty()) {
			throw new ObjectNotFoundException("Não encontrado");				
		}
		
		return post.get();
	}
}
