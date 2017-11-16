package org.scada_lts.user_management.web.api;

import org.scada_lts.user_management.model.dto.InputUser;
import org.scada_lts.user_management.model.dto.UserDto;
import org.scada_lts.user_management.service.definition.UsersService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;

@RestController
public class UserAPI {

    @Resource
    private UsersService usersService;

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<String> listAllUsers() {
        System.out.printf("TEST");
        /*List<UserDto> users = usersService.getAll();
        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }*/
        return new ResponseEntity<String>("test", HttpStatus.NO_CONTENT.OK);
//        return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);

    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
        UserDto user = usersService.getUser(id);
        if (user == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody InputUser inputUser, UriComponentsBuilder ucBuilder) {

        //User currentUser = userService.fi
        if (usersService.getUser(inputUser.getName()) != null) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        UserDto user = usersService.add(inputUser.getName(), inputUser.getPasswd());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody InputUser inputUser) {
        UserDto currentUser = usersService.getUser(id);

        if (currentUser == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        currentUser.setName(inputUser.getName());
        usersService.update(currentUser);

        return new ResponseEntity<UserDto>(currentUser, HttpStatus.OK);

    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        UserDto user = usersService.getUser(id);
        if (user == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        usersService.del(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAllUser() {
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }

}
