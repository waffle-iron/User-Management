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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.scada_lts.user_management.model.acl.EntityClass;
import org.scada_lts.user_management.service.acl.EntityClassService;
import org.scada_lts.user_management.web.api.EntityClassAPI;
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

/**
 * @Author Arkadiusz Parafiniuk arkadiusz.parafiniuk@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EntityClassApiTest {

    @Mock
    EntityClassService entityClassService;

    @InjectMocks
    private EntityClassAPI entityClassAPI;

    private MockMvc mockMvc;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(entityClassAPI)
                .build();
    }

    @Test
    public void testGetListEntityClasses() throws Exception {
        List<EntityClass> entityClasses = new ArrayList<>();
        EntityClass entityClass = new EntityClass("testEntityClass");
        entityClass.setId(12345678L);
        entityClasses.add(entityClass);
        when(entityClassService.getAll()).thenReturn(entityClasses);
        this.mockMvc.perform(get("/api/entityClass/"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value("12345678"));
    }

    @Test
    public void testGetEntityClass() throws Exception {
        EntityClass entityClass = new EntityClass("testEntityClass");
        entityClass.setId(12345678L);
        when(entityClassService.getEntityClass(1L)).thenReturn(entityClass);

        this.mockMvc.perform(get("/api/entityClass/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("12345678"));
    }

    @Test
    public void testCreateEntityClass() throws Exception {
        EntityClass newEntityClass = new EntityClass("testEntityClass");

        String json = toJson(newEntityClass);

        EntityClass entityClass = new EntityClass(newEntityClass.getClassName());

        when(entityClassService.add(newEntityClass)).thenReturn(entityClass);

        mockMvc.perform(
                post("/api/entityClass/").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated());

    }

    @Test
    public void testUpdateEntityClass() throws Exception {
        EntityClass newEntityClass = new EntityClass("testEntityClass");

        String json = toJson(newEntityClass);

        EntityClass entityClass = new EntityClass("testEntityClass");
        EntityClass entityClassChanged = new EntityClass("changed");

        when(entityClassService.getEntityClass(1L)).thenReturn(entityClass);

        mockMvc.perform(
                put("/api/entityClass/1").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

    }

    @Test
    public void testDeleteEntityClass() throws Exception {

        EntityClass entityClass = new EntityClass("testEntityClass");
        when(entityClassService.getEntityClass(1L)).thenReturn(entityClass);

        mockMvc.perform(
                delete("/api/entityClass/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteAllEntityClasses() throws Exception {

        mockMvc.perform(
                delete("/api/entityClass/"))
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
