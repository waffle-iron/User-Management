package org.scada_lts.user_management;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class StartController {
    
    @RequestMapping("/")
    public String index() {
        return "Greetings from User Menagement SacadaLTS!";
    }
    
}
