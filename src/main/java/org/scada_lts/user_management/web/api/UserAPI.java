package org.scada_lts.user_management.web.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.scada_lts.user_management.model.dto.InputUser;
import org.scada_lts.user_management.model.dto.UserDto;
import org.scada_lts.user_management.service.definition.UsersService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserAPI {

    private static final Log LOG = LogFactory.getLog(UserAPI.class);

    @Resource
    private UsersService usersService;

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> listAllUsers() {

       LOG.info("GET /api/user/");

       List<UserDto> users = usersService.getAll();
       if (users.isEmpty()) {
           LOG.warn("users is empty");
           return new ResponseEntity(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);

    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {

        LOG.info("GET /api/user/{id} id:"+id);

        UserDto user = usersService.getUser(id);
        if (user == null) {
            LOG.warn("user id:"+id+" is not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody InputUser inputUser, UriComponentsBuilder ucBuilder) {

        LOG.info("POST /user/ inputUser:"+inputUser);

        if (usersService.getUser(inputUser.getName()) != null) {
            LOG.warn("user is exist");
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        UserDto user = usersService.add(inputUser.getName(), inputUser.getPasswd());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody InputUser inputUser) {

        LOG.info("PUT /user/{id} id:"+id);

        UserDto currentUser = usersService.getUser(id);

        if (currentUser == null) {
            LOG.warn("user id:"+id+" is not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        currentUser.setName(inputUser.getName());
        usersService.update(currentUser);

        return new ResponseEntity<UserDto>(currentUser, HttpStatus.OK);

    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {

        LOG.info("DELETE /user/{id} id:"+id);

        UserDto user = usersService.getUser(id);
        if (user == null) {
            LOG.warn("user id:"+id+" is not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        usersService.del(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAllUser() {

        LOG.info("DELEtE /user/");

        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }

}
