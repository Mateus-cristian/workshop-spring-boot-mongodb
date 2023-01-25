package com.mateuscristian.workshopmongo.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mateuscristian.workshopmongo.dto.UserDto;
import com.mateuscristian.workshopmongo.entities.Post;
import com.mateuscristian.workshopmongo.entities.User;
import com.mateuscristian.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserDto>> findAll(){	
		List<User> list = userService.findAll();
		List<UserDto> listDto = list.stream().map(x -> new UserDto(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);	
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<User> findById(@PathVariable String id){	
		User obj = userService.findById(id);
		
		return ResponseEntity.ok().body(obj);	
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDto objDto){
		User obj = userService.fromDto(objDto);
		obj = userService.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id){	
		 userService.delete(id);
		
		return ResponseEntity.noContent().build();	
	}
	

	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDto objDto,@PathVariable String id){
		User obj = userService.fromDto(objDto);
		obj.setId(id);
		obj = userService.update(obj);
			
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value="/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id){	
		User obj = userService.findById(id);
		List<Post> list = obj.getPosts();
		
		return ResponseEntity.ok().body(list);	
	}
	
}
