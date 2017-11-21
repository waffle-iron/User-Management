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
package org.scada_lts.user_management.service.acl;

import org.scada_lts.user_management.dao.acl.EntryDao;
import org.scada_lts.user_management.model.acl.Entry;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Arkadiusz Parafiniuk arkadiusz.parafiniuk@gmail.com
 *
 * Business logic for Entry
 * @see Entry
 */
@Service
public class EntryServiceImpl implements EntryService {

    @Resource
    EntryDao entryDao;

    @Override
    public List<Entry> getAll() {
        return entryDao.getAll();
    }

    @Override
    public Entry add(Entry entry) {
        return entryDao.create(entry);
    }

    @Override
    public void delete(Entry entry) {
        entryDao.delete(entry);
    }

    @Override
    public Entry getEntry(Long id) {
        return entryDao.get(id);
    }

    @Override
    public void update(Entry entry) {
        entryDao.update(entry);
    }
}