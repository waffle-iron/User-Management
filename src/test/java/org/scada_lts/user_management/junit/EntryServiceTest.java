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
import org.scada_lts.user_management.dao.acl.EntryDao;
import org.scada_lts.user_management.model.acl.Entry;
import org.scada_lts.user_management.service.acl.EntryService;
import org.scada_lts.user_management.service.acl.EntryServiceImpl;
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
public class EntryServiceTest {

    @Mock
    EntryDao entryDao;

    @InjectMocks
    EntryService entryService = new EntryServiceImpl();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);}

    @Test
    public void testGetAllEntries() throws Exception {
        List<Entry> entries = new ArrayList<>();
        Entry entry = new Entry();
        entry.setId(1L);
        entries.add(entry);

        when(entryDao.getAll()).thenReturn(entries);

        assertEquals(entryService.getAll(), entryDao.getAll());
    }

    @Test
    public void testGetEntry() throws Exception {
        Entry entry = new Entry();
        entry.setId(1L);
        when(entryDao.get(1L)).thenReturn(entry);

        assertEquals(entryService.getEntry(1L), entryDao.get(1L));
    }

    //@Test TODO
    public void testCreateEntityIdentity() throws Exception {

    }

    @Test
    public void testUpdateEntry() throws Exception {
        List<Entry> entries = new ArrayList<>();
        Entry entry = new Entry();
        entry.setId(1L);
        entries.add(entry);

        Entry updatedEntry = new Entry();
        updatedEntry.setId(2L);

        when(entryDao.getAll()).thenReturn(entries);
        doAnswer(invocation -> entries.set(0, updatedEntry)).when(entryDao).update(entry);

        assertTrue(entryDao.getAll().get(0).getId() == 1L);
        entryService.update(entry);
        assertTrue(entryDao.getAll().get(0).getId() == 2L);
    }

    @Test
    public void testDeleteEntry() throws Exception {
        List<Entry> entries = new ArrayList<>();
        Entry entry = new Entry();
        entry.setId(1L);
        entries.add(entry);

        when(entryDao.getAll()).thenReturn(entries);
        doAnswer(invocation -> entries.remove(entry)).when(entryDao).delete(entry);

        assertTrue(entryService.getAll().contains(entry));

        entryService.delete(entry);

        assertFalse(entryService.getAll().contains(entry));
    }
    
}
