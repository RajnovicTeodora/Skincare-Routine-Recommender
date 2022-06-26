package sbnz.skincare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sbnz.skincare.dto.EditUserDTO;
import sbnz.skincare.dto.UserDTO;
import sbnz.skincare.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Transactional
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/findAllExcludingCurrent/{username}",
            method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<UserDTO>> findAllExcludingCurrent(
            @PathVariable(value = "username") String username,
            @RequestParam(name = "search", required = false, defaultValue = "") String search,
            @RequestParam(name = "role", required = false, defaultValue = "") String role) {
        List<UserDTO> users = this.userService
                    .findAllExcludingCurrent(username, search, role)
                    .stream()
                    .map(UserDTO::new).collect(Collectors.toList());

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<UserDTO> register(@RequestBody EditUserDTO dto) {
        return new ResponseEntity<>(new UserDTO(this.userService.edit(dto)), HttpStatus.OK);
    }
}
