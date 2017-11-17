package org.scada_lts.user_management.web.api;


import org.scada_lts.user_management.service.security.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

@RestController
public class AuthenticationAPI {

    @Resource
    private AuthenticationService authService;


    @RequestMapping(value = "/auth/{name}/{passwd}", method = RequestMethod.POST)
    public ResponseEntity<String> auth(@PathParam("name") String username, @PathParam("passwd") String passwd) {

        try {

            String token = authService.auth(username,passwd);
            return new ResponseEntity<>(token, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public Boolean auth(@RequestBody String token) {
        return authService.auth(token);
    }

}
