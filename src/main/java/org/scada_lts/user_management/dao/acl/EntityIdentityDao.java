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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.scada_lts.user_management.model.acl.EntityIdentity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class responsible for writing and reading EntityIdentity (ACL) in the database
 *
 * @author grzegorz bylica grzegorz.bylica@gmail.com
 */
@Repository("entityIdentityDao")
public class EntityIdentityDao implements AclDao<EntityIdentity> {

    private static final Log LOG = LogFactory.getLog(EntityIdentityDao.class);

    private Map<Long, EntityIdentity> entity = new HashMap<>();
    //TODO dao

    /**
     * Save the EntityIdentity object
     * @param elm
     * @return
     */
    @Override
    public EntityIdentity create(EntityIdentity elm) {
        long id = entity.size()+1;
        elm.setId(id);
        entity.put(id, elm);
        return elm;
    }

    /**
     * Update the EntityIdentity object
     * @param elm
     */
    @Override
    public void update(EntityIdentity elm) {
        entity.put(elm.getId(), elm);
    }

    /**
     * Remove an EntityIdentity object based on id
     * @param elm
     */
    @Override
    public void delete(EntityIdentity elm) {
        entity.remove(elm.getId());

    }

    /**
     * Gets an EntityIdentity object based on id
     * @param id
     * @return
     */
    @Override
    public EntityIdentity get(Long id) {
        return entity.get(id);
    }

    /**
     * Retrieves all EntityIdentity objects
     * @return
     */
    @Override
    public List<EntityIdentity> getAll() {
        return new ArrayList<EntityIdentity>(entity.values());
    }
}
