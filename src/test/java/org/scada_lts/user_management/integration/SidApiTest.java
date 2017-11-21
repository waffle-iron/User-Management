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
import org.scada_lts.user_management.model.acl.Sid;
import org.scada_lts.user_management.service.acl.SidService;
import org.scada_lts.user_management.tools.JsonConverter;
import org.scada_lts.user_management.web.api.SidAPI;
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
public class SidApiTest {

    @Mock
    SidService sidService;

    @InjectMocks
    private SidAPI sidAPI;

    private MockMvc mockMvc;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(sidAPI)
                .build();
    }

    @Test
    public void testGetListSids() throws Exception {
        List<Sid> sids = new ArrayList<>();
        Sid sid = new Sid();
        sid.setSid("test");
        sid.setId(1L);

        sids.add(sid);
        when(sidService.getAll()).thenReturn(sids);
        this.mockMvc.perform(get("/api/sid/"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].sid").value("test"))
                .andExpect(jsonPath("$.[0].id").value("1"));
    }

    @Test
    public void testGetSid() throws Exception {
        Sid sid = new Sid();
        sid.setId(12345678L);
        when(sidService.getSid(1L)).thenReturn(sid);

        this.mockMvc.perform(get("/api/sid/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("12345678"));
    }

    @Test
    public void testUpdateSid() throws Exception {
        Sid newSid = new Sid();
        newSid.setId(1L);

        String json = JsonConverter.getInstance().toJson(newSid);

        Sid sid = new Sid();
        sid.setSid("testSid");
        sid.setId(123L);

        when(sidService.getSid(1L)).thenReturn(sid);

        mockMvc.perform(
                put("/api/sid/1").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        newSid.setId(2L);
        json = JsonConverter.getInstance().toJson(newSid);
        mockMvc.perform(
                put("/api/sid/1").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteSid() throws Exception {
        Sid sid = new Sid();
        when(sidService.getSid(1L)).thenReturn(sid);

        mockMvc.perform(
                delete("/api/sid/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteAllSids() throws Exception {

        mockMvc.perform(
                delete("/api/sid/"))
                .andDo(print())
                .andExpect(status().isNotImplemented());

    }
}
