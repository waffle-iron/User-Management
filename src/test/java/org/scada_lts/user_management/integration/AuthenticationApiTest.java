/*
 * (c) 2017 Abil'I.T. http://abilit.eu/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
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

/**
 * @author Grzegorz Bylica grzegorz.bylica@gmail.com
 **/
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
