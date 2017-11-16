package org.scada_lts.user_management.integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.scada_lts.user_management.model.dto.UserDto;
import org.scada_lts.user_management.service.definition.UsersService;
import org.scada_lts.user_management.web.api.UserAPI;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserApiTest {

    @Mock
    private UsersService usersService;

    @InjectMocks
    private UserAPI userApi;

    private MockMvc mockMvc;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userApi)
                .build();
    }

    @Test
    public void testGetListUsers() throws Exception {

        List<UserDto> users = new ArrayList<UserDto>();
        users.add(new UserDto("test"));
        users.add(new UserDto( "test1"));
        //when(usersService.getAll()).thenReturn(users);
        /*this.mockMvc.perform(get("/api/users/"))
                .andDo(print()).andExpect(status().isOk());*/
        //.andExpect(jsonPath("$.name").value("test"));
    }

}
