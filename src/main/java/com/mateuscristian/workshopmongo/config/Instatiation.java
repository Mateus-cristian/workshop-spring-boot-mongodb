package com.mateuscristian.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.mateuscristian.workshopmongo.dto.AuthorDto;
import com.mateuscristian.workshopmongo.dto.CommentDto;
import com.mateuscristian.workshopmongo.entities.Post;
import com.mateuscristian.workshopmongo.entities.User;
import com.mateuscristian.workshopmongo.repositories.PostRepository;
import com.mateuscristian.workshopmongo.repositories.UserRepository;

@Configuration
public class Instatiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		
		Post post1 = new Post(null, sdf.parse("21/01/2023"), "Partiu viagem", "Irei viajar a São Paulo abraços",new AuthorDto(maria));
		Post post2 = new Post(null, sdf.parse("21/01/2023"), "Bom dia acordei", "Dia lindo!!",new AuthorDto(maria));
		
		CommentDto c1 = new CommentDto("Boa viagem mano!",sdf.parse("21/01/2023"),new AuthorDto(alex));
		CommentDto c2 = new CommentDto("Vai em paz!",sdf.parse("21/01/2023"),new AuthorDto(bob));
		CommentDto c3 = new CommentDto("muito bão!",sdf.parse("21/01/2023"),new AuthorDto(maria));
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		
		postRepository.saveAll(Arrays.asList(post1,post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userRepository.save(maria);
	}

}
