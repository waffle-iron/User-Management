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
import org.scada_lts.user_management.dao.acl.EntityIdentityDao;
import org.scada_lts.user_management.model.acl.EntityIdentity;
import org.scada_lts.user_management.service.acl.EntityIdentityService;
import org.scada_lts.user_management.service.acl.EntityIdentityServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * @Author Arkadiusz Parafiniuk arkadiusz.parafiniuk@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EntityIdentityServiceTest {

    @Mock
    EntityIdentityDao entityIdentityDao;

    @InjectMocks
    EntityIdentityService entityIdentityService = new EntityIdentityServiceImpl();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);}

    @Test
    public void testGetAllEntityIdentities() throws Exception {
        List<EntityIdentity> entityIdentities = new ArrayList<>();
        EntityIdentity entityIdentity = new EntityIdentity();
        entityIdentity.setId(1L);
        entityIdentities.add(entityIdentity);

        when(entityIdentityDao.getAll()).thenReturn(entityIdentities);

        assertEquals(entityIdentityService.getAll(), entityIdentityDao.getAll());
    }

    @Test
    public void testGetEntityIdentity() throws Exception {
        EntityIdentity entityIdentity = new EntityIdentity();
        entityIdentity.setId(1L);
        when(entityIdentityDao.get(1L)).thenReturn(entityIdentity);

        assertEquals(entityIdentityService.getEntityIdentity(1L), entityIdentityDao.get(1L));
    }

    //@Test TODO
    public void testCreateEntityIdentity() throws Exception {

    }

    @Test
    public void testUpdateEntityIdentity() throws Exception {
        List<EntityIdentity> entityIdentities = new ArrayList<>();
        EntityIdentity entityIdentity = new EntityIdentity();
        entityIdentity.setIdentityId("beforeChange");
        entityIdentity.setId(1L);
        entityIdentities.add(entityIdentity);

        EntityIdentity updatedEntityIdentity = new EntityIdentity();
        updatedEntityIdentity.setIdentityId("afterChange");
        updatedEntityIdentity.setId(1L);

        when(entityIdentityDao.getAll()).thenReturn(entityIdentities);
        doAnswer(invocation -> entityIdentities.set(0, updatedEntityIdentity)).when(entityIdentityDao).update(entityIdentity);

        assertEquals(entityIdentityDao.getAll().get(0).getIdentityId(), "beforeChange");
        entityIdentityService.update(entityIdentity);
        assertEquals(entityIdentityDao.getAll().get(0).getIdentityId(), "afterChange");
    }

    @Test
    public void testDeleteEntityIdentity() throws Exception {
        List<EntityIdentity> entityIdentityes = new ArrayList<>();
        EntityIdentity entityIdentity = new EntityIdentity();
        entityIdentity.setIdentityId("testIdentity");
        entityIdentity.setId(1L);
        entityIdentityes.add(entityIdentity);

        when(entityIdentityDao.getAll()).thenReturn(entityIdentityes);
        doAnswer(invocation -> entityIdentityes.remove(entityIdentity)).when(entityIdentityDao).delete(entityIdentity);

        assertTrue(entityIdentityService.getAll().contains(entityIdentity));

        entityIdentityService.delete(entityIdentity);

        assertFalse(entityIdentityService.getAll().contains(entityIdentity));
    }

}
