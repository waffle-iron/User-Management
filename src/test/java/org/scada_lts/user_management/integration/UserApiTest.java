package org.scada_lts.user_management.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.scada_lts.user_management.model.dto.InputUser;
import org.scada_lts.user_management.model.dto.UserDto;
import org.scada_lts.user_management.service.definition.UsersService;
import org.scada_lts.user_management.web.api.UserAPI;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



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
        users.add(new UserDto("admin"));
        when(usersService.getAll()).thenReturn(users);
        this.mockMvc.perform(get("/api/user/"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("admin"));
    }

    @Test
    public void testGetUser() throws Exception {
        UserDto userDto = new UserDto("admin");
        when(usersService.getUser(1L)).thenReturn(userDto);

        this.mockMvc.perform(get("/api/user/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("admin"));


    }

    @Test
    public void testCreateUser() throws Exception {

        InputUser inputUser = new InputUser("admin","admin");

        String json = toJson(inputUser);

        UserDto userDto = new UserDto(inputUser.getName());

        when(usersService.add(inputUser.getName(), inputUser.getPasswd())).thenReturn(userDto);

        mockMvc.perform(
                post("/api/user/").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated());

    }

    @Test
    public void testUpdateUser() throws Exception {
        InputUser inputUser = new InputUser("admin", "admin");

        String json = toJson(inputUser);

        UserDto userDto = new UserDto("admin");
        UserDto userDtoChanged = new UserDto("changed");


        when(usersService.getUser(1L)).thenReturn(userDto);

        mockMvc.perform(
                put("/api/user/1").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

    }

    @Test
    public void testDeleteUser() throws Exception {

        UserDto userDto = new UserDto("admin");
        when(usersService.getUser(1L)).thenReturn(userDto);

        mockMvc.perform(
                delete("/api/user/1"))
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    public void testDeleteAllUser() throws Exception {

        mockMvc.perform(
                delete("/api/user/"))
                .andDo(print())
                .andExpect(status().isNotImplemented());

    }

    private String toJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }





}
