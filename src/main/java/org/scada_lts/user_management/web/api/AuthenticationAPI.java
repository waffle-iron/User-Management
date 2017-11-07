package org.scada_lts.user_management.web.api;

import org.scada_lts.user_management.model.dto.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationAPI {

    @RequestMapping("/user")
    public User user() {
        return new User();
    }


}
