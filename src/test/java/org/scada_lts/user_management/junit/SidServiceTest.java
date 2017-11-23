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
package org.scada_lts.user_management.junit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.scada_lts.user_management.dao.acl.SidDao;
import org.scada_lts.user_management.model.acl.Sid;
import org.scada_lts.user_management.service.acl.SidService;
import org.scada_lts.user_management.service.acl.SidServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * @Author Arkadiusz Parafiniuk arkadiusz.parafiniuk@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SidServiceTest {
    
    @Mock
    SidDao sidDao;

    @InjectMocks
    SidService sidService = new SidServiceImpl();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);}

    @Test
    public void testGetAllSids() throws Exception {
        List<Sid> sids = new ArrayList<>();
        Sid sid = new Sid();
        sid.setId(1L);
        sids.add(sid);

        when(sidDao.getAll()).thenReturn(sids);

        assertEquals(sidService.getAll(), sidDao.getAll());
    }

    @Test
    public void testGetSid() throws Exception {
        Sid sid = new Sid();
        sid.setId(1L);
        when(sidDao.get(1L)).thenReturn(sid);

        assertEquals(sidService.getSid(1L), sidDao.get(1L));
    }

    //@Test TODO
    public void testCreateEntityIdentity() throws Exception {

    }

    @Test
    public void testUpdateSid() throws Exception {
        List<Sid> sids = new ArrayList<>();
        Sid sid = new Sid();
        sid.setSid("beforeChange");
        sid.setId(1L);
        sids.add(sid);

        Sid updatedSid = new Sid();
        updatedSid.setSid("afterChange");
        updatedSid.setId(1L);

        when(sidDao.getAll()).thenReturn(sids);
        doAnswer(invocation -> sids.set(0, updatedSid)).when(sidDao).update(sid);

        assertEquals(sidDao.getAll().get(0).getSid(), "beforeChange");
        sidService.update(sid);
        assertEquals(sidDao.getAll().get(0).getSid(), "afterChange");
    }

    @Test
    public void testDeleteSid() throws Exception {
        List<Sid> sids = new ArrayList<>();
        Sid sid = new Sid();
        sid.setSid("testSid");
        sid.setId(1L);
        sids.add(sid);

        when(sidDao.getAll()).thenReturn(sids);
        doAnswer(invocation -> sids.remove(sid)).when(sidDao).delete(sid);

        assertTrue(sidService.getAll().contains(sid));

        sidService.delete(sid);

        assertFalse(sidService.getAll().contains(sid));
    }
}
