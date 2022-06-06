package sbnz.skincare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import sbnz.skincare.exception.NotFoundException;
import sbnz.skincare.facts.User;
import sbnz.skincare.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User findByUsername(String username) throws NotFoundException {
		return userRepository.findByUsername(username);
	}

	public User findById(Long id) throws AccessDeniedException {
		return userRepository.findById(id).orElse(null);
	}

	public List<User> findAll() throws AccessDeniedException {
		return userRepository.findAll();
	}

}
