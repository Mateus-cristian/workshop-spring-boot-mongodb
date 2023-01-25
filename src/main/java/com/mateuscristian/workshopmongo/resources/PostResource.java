package com.mateuscristian.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mateuscristian.workshopmongo.entities.Post;
import com.mateuscristian.workshopmongo.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

	@Autowired
	public PostService postService;
	
	@GetMapping
	public ResponseEntity<List<Post>> findAll(){
		
		List<Post> listPosts = postService.findAll();
		
		return ResponseEntity.ok().body(listPosts);
	}
	
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post post = postService.findById(id);
		
		return ResponseEntity.ok().body(post);
	}
}
