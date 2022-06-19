package sbnz.skincare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sbnz.skincare.dto.NewUserDTO;
import sbnz.skincare.exception.NotFoundException;
import sbnz.skincare.exception.UsernameTakenException;
import sbnz.skincare.facts.Dermatologist;
import sbnz.skincare.facts.UserRole;
import sbnz.skincare.repository.UserRoleRepository;

@Service
public class DermatologistService {

    private final UserRoleRepository userRoleRepository;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DermatologistService(UserService userService, UserRoleRepository userRoleRepository,
                                PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Dermatologist register(NewUserDTO dto) {
        if (userService.findByUsername(dto.getUsername()) != null)
            throw new UsernameTakenException("Username already taken!");

        UserRole role = this.userRoleRepository.findByName("DERMATOLOGIST").orElseThrow(NotFoundException::new);
        Dermatologist dermatologist = new Dermatologist(dto, role);

        dermatologist.setPassword(passwordEncoder.encode(dto.getPassword()));
        dermatologist.setRole(role);

        this.userService.save(dermatologist);
        return dermatologist;
    }
}
