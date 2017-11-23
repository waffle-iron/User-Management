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
import org.scada_lts.user_management.model.acl.EntityIdentity;
import org.scada_lts.user_management.service.acl.EntityIdentityService;
import org.scada_lts.user_management.tools.JsonConverter;
import org.scada_lts.user_management.web.api.EntityIdentityAPI;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author Arkadiusz Parafiniuk arkadiusz.parafiniuk@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EntityIdentityApiTest {

    @Mock
    EntityIdentityService entityIdentityService;

    @InjectMocks
    private EntityIdentityAPI entityIdentityAPI;

    private MockMvc mockMvc;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(entityIdentityAPI)
                .build();
    }

    @Test
    public void testGetListEntityIdentities() throws Exception {
        List<EntityIdentity> entityIdentities = new ArrayList<>();
        EntityIdentity entityIdentity = new EntityIdentity();
        entityIdentity.setIdentityId("test");
        entityIdentity.setId(1L);

        entityIdentities.add(entityIdentity);
        when(entityIdentityService.getAll()).thenReturn(entityIdentities);
        this.mockMvc.perform(get("/api/entityIdentity/"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].identityId").value("test"))
                .andExpect(jsonPath("$.[0].id").value("1"));
    }

    @Test
    public void testGetEntityIdentity() throws Exception {
        EntityIdentity entityIdentity = new EntityIdentity();
        entityIdentity.setId(12345678L);
        when(entityIdentityService.getEntityIdentity(1L)).thenReturn(entityIdentity);

        this.mockMvc.perform(get("/api/entityIdentity/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("12345678"));
    }

    @Test
    public void testUpdateEntityIdentity() throws Exception {
        EntityIdentity newEntityIdentity = new EntityIdentity();
        newEntityIdentity.setId(1L);

        String json = JsonConverter.getInstance().toJson(newEntityIdentity);

        EntityIdentity entityIdentity = new EntityIdentity();
        entityIdentity.setIdentityId("testIdentity");
        entityIdentity.setId(123L);

        when(entityIdentityService.getEntityIdentity(1L)).thenReturn(entityIdentity);

        mockMvc.perform(
                put("/api/entityIdentity/1").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        newEntityIdentity.setId(2L);
        json = JsonConverter.getInstance().toJson(newEntityIdentity);
        mockMvc.perform(
                put("/api/entityIdentity/1").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteEntityIdentity() throws Exception {
        EntityIdentity entityIdentity = new EntityIdentity();
        when(entityIdentityService.getEntityIdentity(1L)).thenReturn(entityIdentity);

        mockMvc.perform(
                delete("/api/entityIdentity/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteAllEntityIdentities() throws Exception {

        mockMvc.perform(
                delete("/api/entityIdentity/"))
                .andDo(print())
                .andExpect(status().isNotImplemented());

    }


}
