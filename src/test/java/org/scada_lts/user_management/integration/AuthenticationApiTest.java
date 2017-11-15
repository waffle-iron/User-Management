package org.scada_lts.user_management.integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.scada_lts.user_management.model.dto.UserDto;
import org.scada_lts.user_management.service.definition.UsersService;
import org.scada_lts.user_management.service.security.AuthenticationService;
import org.scada_lts.user_management.web.api.AuthenticationAPI;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationApiTest {

    @Mock
    private AuthenticationService authService;

    @Mock
    private UsersService usersService;

    @InjectMocks
    private AuthenticationAPI authApi;

    private MockMvc mockMvc;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(authApi)
                .build();
    }
    
    @Test
    public void getTokenUser() throws Exception {

        when(authService.auth("user",null)).thenReturn("111111111111111");
        when(usersService.getUser("name")).thenReturn(new UserDto("name"));
        this.mockMvc.perform(post("/auth/name/password"))
                .andDo(print()).andExpect(status().isOk());
                //.andExpect(jsonPath("$.name").value("test"));
    }

    //@Test
    public void checkToken() throws Exception {
        when(authService.auth("111111")).thenReturn(true);
        //TODO
        String jsonBody = "";

            this.mockMvc.perform(post("/auth")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonBody))
                    .andExpect(status().isOk());

    }

}
