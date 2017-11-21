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

import org.scada_lts.user_management.dao.acl.EntityClassDao;
import org.scada_lts.user_management.model.acl.EntityClass;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Arkadiusz Parafiniuk arkadiusz.parafiniuk@gmail.com
 *
 * Business logic for entityClass
 * @see EntityClass
 */
@Service
public class EntityClassServiceImpl implements EntityClassService {

    @Resource
    EntityClassDao entityClassDao;

    @Override
    public List<EntityClass> getAll() {
        return entityClassDao.getAll();
    }

    @Override
    public EntityClass add(EntityClass entityClass) {
        return entityClassDao.create(entityClass);
    }

    @Override
    public void del(EntityClass entityClass) {
        entityClassDao.delete(entityClass);
    }

    @Override
    public EntityClass getEntityClass(Long id) {
        return entityClassDao.get(id);
    }

    @Override
    public void update(EntityClass entityClass) {
        entityClassDao.update(entityClass);
    }
}


