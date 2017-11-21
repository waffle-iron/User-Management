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

import org.scada_lts.user_management.dao.acl.EntityIdentityDao;
import org.scada_lts.user_management.model.acl.EntityIdentity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Arkadiusz Parafiniuk arkadiusz.parafiniuk@gmail.com
 *
 * Business logic for entityIdentity
 * @see EntityIdentity
 */
@Service
public class EntityIdentityServiceImpl implements EntityIdentityService {

    @Resource
    EntityIdentityDao entityIdentityDao;

    @Override
    public List<EntityIdentity> getAll() {
        return entityIdentityDao.getAll();
    }

    @Override
    public EntityIdentity add(EntityIdentity entityIdentity) {
        return entityIdentityDao.create(entityIdentity);
    }

    @Override
    public void del(EntityIdentity entityIdentity) {
        entityIdentityDao.delete(entityIdentity);
    }

    @Override
    public EntityIdentity getEntityIdentity(Long id) {
        return entityIdentityDao.get(id);
    }

    @Override
    public void update(EntityIdentity entityIdentity) {
        entityIdentityDao.update(entityIdentity);
    }
}
