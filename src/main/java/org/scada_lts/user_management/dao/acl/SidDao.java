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

import org.scada_lts.user_management.model.acl.Sid;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class responsible for writing and reading Sid (ACL) in the database
 *
 * @author grzegorz bylica grzegorz.bylica@gmail.com
 *
 */
@Repository("sidDao")
public class SidDao implements AclDao<Sid> {

    private Map<Long, Sid> sids = new HashMap<>();
    //TODO dao

    /**
     * Save the Sid object
     * @param elm
     * @return
     */
    @Override
    public Sid create(Sid elm) {

        //TODO DAO
        long id = sids.size()+1;
        elm.setId(id);
        sids.put(id, elm);
        return elm;
        //
    }

    /**
     * Update the Sid object
     * @param elm
     */
    @Override
    public void update(Sid elm) {
        sids.put(elm.getId(), elm);
    }

    /**
     * Remove an Sid object base on id
     * @param elm
     */
    @Override
    public void delete(Sid elm) {
        sids.remove(elm.getId());
    }

    /**
     * Gets an Sid object based on id
     * @param id
     * @return
     */
    @Override
    public Sid get(Long id) {
        return sids.get(id);
    }

    /**
     * Retrieves all Sid objects
     * @return
     */
    @Override
    public List<Sid> getAll() {
       return new ArrayList<Sid>(sids.values());
    }

}
