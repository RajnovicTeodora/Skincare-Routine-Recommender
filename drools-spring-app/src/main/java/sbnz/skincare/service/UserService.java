package sbnz.skincare.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sbnz.skincare.dto.EditUserDTO;
import sbnz.skincare.exception.EmailTakenException;
import sbnz.skincare.facts.User;
import sbnz.skincare.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User findById(Long id) throws AccessDeniedException {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() throws AccessDeniedException {
        return userRepository.findAll();
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public List<User> findAllExcludingCurrent(String username) {
        return this.userRepository.findAllByUsernameNotLike(username);
    }


    public User edit(EditUserDTO dto) {
        Optional<User> maybeUser = this.userRepository.findByUsername(dto.getUsername());

        if (!maybeUser.isPresent())
            throw new UsernameNotFoundException(String.format("User with username %s not found", dto.getUsername()));
        User user = maybeUser.get();

        Optional<User> maybeSameEmailUser = this.userRepository.findByEmail(dto.getEmail());
        if (maybeSameEmailUser.isPresent() && !Objects.equals(maybeSameEmailUser.get().getId(), user.getId()))
            throw new EmailTakenException(String.format("Email %s is already taken", dto.getEmail()));

        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());

        return this.save(user);
    }
}
