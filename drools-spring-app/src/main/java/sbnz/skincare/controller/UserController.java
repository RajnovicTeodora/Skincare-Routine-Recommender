package sbnz.skincare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sbnz.skincare.dto.UserDTO;
import sbnz.skincare.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/findAllExcludingCurrent/{username}",
            method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<UserDTO>> findAllExcludingCurrent(@PathVariable(value = "username") String username) {
        List<UserDTO> users = this.userService
                .findAllExcludingCurrent(username)
                .stream()
                .map(UserDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
