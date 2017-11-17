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

import java.util.List;

/**
 * Interface responsible for writing and reading data (ACL) in the database
 *
 * @author grzegorz bylica grzegorz.bylica@gmail.com
 */
public interface AclDao<T> {

    /**
     * Save the ACL object
     * @param elm
     * @return
     */
    T create(T elm);

    /**
     * Update the ACL object
     * @param elm
     */
    void update(T elm);

    /**
     * Delete the ACL object
     * @param elm
     */
    void delete(T elm);

    /**
     * Gets an ACL object based on id
     * @param id
     * @return
     */
    T get(Long id);

    /**
     * Get all objects of a particular <T> ACL class
     * @return
     */
    List<T> getAll();
}
