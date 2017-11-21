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
import org.scada_lts.user_management.model.acl.EntityClass;
import org.scada_lts.user_management.model.acl.EntityIdentity;
import org.scada_lts.user_management.model.acl.Entry;
import org.scada_lts.user_management.model.acl.Sid;
import org.scada_lts.user_management.model.dto.InputFilterAcl;
import org.scada_lts.user_management.model.dto.InputHasPermission;
import org.scada_lts.user_management.model.security.Permission;
import org.scada_lts.user_management.service.acl.PermissionEvaluatorService;
import org.scada_lts.user_management.tools.JsonConverter;
import org.scada_lts.user_management.web.api.PermissionEvaluatorAPI;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Grzegorz Bylica grzegorz.bylica@gmail.com
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PermissionEvaluatorApiTest {

    @Mock
    private PermissionEvaluatorService permissionEvaluatorService;


    @InjectMocks
    private PermissionEvaluatorAPI permissionEvaluatorAPI;

    private MockMvc mockMvc;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(permissionEvaluatorAPI)
                .build();
    }

    @Test
    public void filterDatBaseOnACL() throws Exception {


        Sid sid = new Sid();
        EntityClass entityClass = new EntityClass();
        Permission permision = new Permission();

        InputFilterAcl inputFilterAcl = new InputFilterAcl(sid, entityClass, permision);

        String json = JsonConverter.getInstance().toJson(inputFilterAcl);

        EntityIdentity ei = new EntityIdentity();
        List<Entry> resultFilterDataBaseOnAcl = new ArrayList<Entry>();
        resultFilterDataBaseOnAcl.add(new Entry(ei, 0L, sid, (byte) 1));

        when(permissionEvaluatorService.filterDataBaseOnACL(sid,entityClass,permision)).thenReturn(resultFilterDataBaseOnAcl);

        mockMvc.perform(
                post("/api/permission/filter").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());


    }

    @Test
    public void hasPermission() throws Exception {

        Sid sid = new Sid();
        EntityIdentity ei = new EntityIdentity();
        Permission permision = new Permission();

        InputHasPermission inputHasPermission = new InputHasPermission(sid, ei, permision);

        String json = JsonConverter.getInstance().toJson(inputHasPermission);

        when(permissionEvaluatorService.hasPermission(sid,ei,permision)).thenReturn(true);

        mockMvc.perform(
                post("/api/permission/hasPermission").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

    }

}
