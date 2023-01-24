package com.mateuscristian.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mateuscristian.workshopmongo.dto.UserDto;
import com.mateuscristian.workshopmongo.entities.User;
import com.mateuscristian.workshopmongo.repositories.UserRepository;
import com.mateuscristian.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty()) {
			throw new ObjectNotFoundException("Não encontrado");				
		}
		
		return user.get();
	}
	
	public User insert(User obj) {
		return userRepository.insert(obj);
	}
	
	public User fromDto(UserDto objDto) {
		return new User(objDto.getId(),objDto.getName(),objDto.getEmail());
	}
}
