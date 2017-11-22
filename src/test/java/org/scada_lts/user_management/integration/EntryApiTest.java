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
import org.scada_lts.user_management.model.acl.Entry;
import org.scada_lts.user_management.service.acl.EntryService;
import org.scada_lts.user_management.tools.JsonConverter;
import org.scada_lts.user_management.web.api.EntryAPI;
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
public class EntryApiTest {
    @Mock
    EntryService entryService;

    @InjectMocks
    private EntryAPI entryAPI;

    private MockMvc mockMvc;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(entryAPI)
                .build();
    }

    @Test
    public void testGetListEntry() throws Exception {
        List<Entry> entries = new ArrayList<>();

        when(entryService.getAll()).thenReturn(entries);
        this.mockMvc.perform(get("/api/entry/"))
                .andDo(print()).andExpect(status().isNoContent());

        Entry entry = new Entry();
        entry.setId(1L);
        entries.add(entry);

        when(entryService.getAll()).thenReturn(entries);
        this.mockMvc.perform(get("/api/entry/"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value("1"));
    }

    @Test
    public void testGetEntry() throws Exception {
        Entry entry = new Entry();
        entry.setId(1L);
        when(entryService.getEntry(1L)).thenReturn(entry);

        this.mockMvc.perform(get("/api/entry/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    public void testUpdateEntry() throws Exception {
        Entry newEntry = new Entry();
        newEntry.setId(1L);

        String json = JsonConverter.getInstance().toJson(newEntry);

        Entry entry = new Entry();

        when(entryService.getEntry(1L)).thenReturn(entry);

        mockMvc.perform(
                put("/api/entry/1").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        newEntry.setId(2L);
        json = JsonConverter.getInstance().toJson(newEntry);
        mockMvc.perform(
                put("/api/entry/1").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteEntry() throws Exception {
        Entry entry = new Entry();
        when(entryService.getEntry(1L)).thenReturn(entry);

        mockMvc.perform(
                delete("/api/entry/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteAllEntry() throws Exception {

        mockMvc.perform(
                delete("/api/entry/"))
                .andDo(print())
                .andExpect(status().isNotImplemented());

    }

}
