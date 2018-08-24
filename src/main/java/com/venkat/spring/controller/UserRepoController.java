package com.venkat.spring.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.venkat.hello.Greeting;
import com.venkat.hello.UserRepository;
import com.venkat.spring.entity.User;

@RestController
@RequestMapping(path="/demo")
public class UserRepoController {
	
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
	@Autowired 
	private UserRepository userRepository;

	@PostMapping(path = "/add/{name}/{email}") // Map ONLY GET Requests
	public @ResponseBody String addNewUser(@PathVariable("name") String name, @PathVariable("email") String email) {
		// @ResponseBody means the returned String is the response, not a view
		// name
		// @RequestParam means it is a parameter from the GET or POST request

		User n = new User();
		n.setName(name);
		n.setEmail(email);
		userRepository.save(n);
		return "Saved";
	}

	@GetMapping(path = "/all")
	public  @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
	
    @GetMapping(path="/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
