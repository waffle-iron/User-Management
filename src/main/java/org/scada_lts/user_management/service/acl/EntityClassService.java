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

import org.scada_lts.user_management.model.acl.EntityClass;

import java.util.List;

/**
 * Class created by Arkadiusz Parafiniuk
 * E-mail: arkadiusz.parafiniuk@gmail.com
 */
public interface EntityClassService {
    List<EntityClass> getAll();

    EntityClass add(String className);

    void del(EntityClass entityClass);

    EntityClass getEntityClass(Long id);

    EntityClass getEntityClass(String className);

    void update(EntityClass entityClass);
}
