package org.scada_lts.user_management.e2e;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.scada_lts.user_management.model.dao.User;
import org.scada_lts.user_management.model.dto.UserDto;
import org.scada_lts.user_management.service.AuthenticationService;
import org.scada_lts.user_management.service.UsersService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationApiTest {

    @Resource
    private AuthenticationService authService;

    @Resource
    private UsersService usersService;

    @Resource
    private MockMvc mockMvc;
    
    @Test
    public void getTokenUser() throws Exception {

        when(authService.auth(new UserDto(null,null))).thenReturn("111111111111111");
        when(usersService.getUser(null)).thenReturn(new User("name","password"));
        this.mockMvc.perform(post("/auth/name/password"))
                .andDo(print()).andExpect(status().isOk());
                //.andExpect(jsonPath("$.name").value("test"));
    }

    
}
