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
package org.scada_lts.user_management.dao.acl;

import org.scada_lts.user_management.model.acl.Entry;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class responsible for writing and reading EntityIdentity (ACL) in the database
 *
 * @author grzegorz bylica grzegorz.bylica@gmail.com
 *
 */
@Repository("entryDao")
public class EntryDao implements AclDao<Entry> {

    private Map<Long, Entry> entres = new HashMap<>();
    //TODO dao

    /**
     * Save the Entry object
     * @param elm
     * @return
     */
    @Override
    public Entry create(Entry elm) {
        //TODO DAO
        long id = entres.size()+1;
        elm.setId(id);
        entres.put(id, elm);

        return elm;
    }

    /**
     * Update the Entry object
     * @param elm
     */
    @Override
    public void update(Entry elm) {
        entres.put(elm.getId(), elm);
    }

    /**
     * Remove an Entry object base on id
     * @param elm
     */
    @Override
    public void delete(Entry elm) {
        entres.remove(elm.getId());
    }

    /**
     * Gets an Entry object based on id
     * @param id
     * @return
     */
    @Override
    public Entry get(Long id) {
        return entres.get(id);
    }

    /**
     * Retrieves all Entry objects
     * @return
     */
    @Override
    public List<Entry> getAll() {
        return new ArrayList<Entry>(entres.values());
    }
}
