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
import org.scada_lts.user_management.dao.acl.EntityClassDao;
import org.scada_lts.user_management.model.acl.EntityClass;
import org.scada_lts.user_management.service.acl.EntityClassService;
import org.scada_lts.user_management.service.acl.EntityClassServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * @Author Arkadiusz Parafiniuk arkadiusz.parafiniuk@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EntityClassServiceTest {

    @Mock
    EntityClassDao entityClassDao;

    @InjectMocks
    EntityClassService entityClassService = new EntityClassServiceImpl();

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllEntityClasses() throws Exception {
        List<EntityClass> entityClasses = new ArrayList<>();
        EntityClass entityClass = new EntityClass("testClass");
        entityClass.setId(1L);
        entityClasses.add(entityClass);

        when(entityClassDao.getAll()).thenReturn(entityClasses);

        assertEquals(entityClassService.getAll(), entityClassDao.getAll());
    }

    @Test
    public void testGetEntityClass() throws Exception {
        EntityClass entityClass = new EntityClass("testClass");
        entityClass.setId(1L);
        when(entityClassDao.get(1L)).thenReturn(entityClass);

        assertEquals(entityClassService.getEntityClass(1L), entityClassDao.get(1L));
    }

    //@Test TODO
    public void testCreateEntityClass() throws Exception {
        List<EntityClass> entityClasses = new ArrayList<>();
        EntityClass entityClass = new EntityClass("testClass");
        entityClass.setId(1L);

        when(entityClassDao.getAll()).thenReturn(entityClasses);
        doAnswer(invocation -> entityClasses.add(entityClass)).when(entityClassDao).create(entityClass);

        assertFalse(entityClassService.getAll().contains(entityClass));
        entityClassService.add(entityClass);
        assertTrue(entityClassService.getAll().contains(entityClass));
    }

    @Test
    public void testUpdateEntityClass() throws Exception {
        List<EntityClass> entityClasses = new ArrayList<>();
        EntityClass entityClass = new EntityClass("testClass");
        entityClass.setId(1L);
        entityClasses.add(entityClass);

        EntityClass updatedEntityClass = new EntityClass("changedName");
        updatedEntityClass.setId(1L);

        when(entityClassDao.getAll()).thenReturn(entityClasses);
        doAnswer(invocation -> entityClasses.set(0, updatedEntityClass)).when(entityClassDao).update(entityClass);

        assertEquals(entityClassDao.getAll().get(0).getClassName(), "testClass");
        entityClassService.update(entityClass);
        assertEquals(entityClassDao.getAll().get(0).getClassName(), "changedName");
    }

    @Test
    public void testDeleteEntityClass() throws Exception {
        List<EntityClass> entityClasses = new ArrayList<>();
        EntityClass entityClass = new EntityClass("testClass");
        entityClass.setId(1L);
        entityClasses.add(entityClass);

        when(entityClassDao.getAll()).thenReturn(entityClasses);
        doAnswer(invocation -> entityClasses.remove(entityClass)).when(entityClassDao).delete(entityClass);

        assertTrue(entityClassService.getAll().size()==1);

        entityClassService.delete(entityClass);

        assertTrue(entityClassService.getAll().size()==0);

    }


}
