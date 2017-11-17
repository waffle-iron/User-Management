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
package org.scada_lts.user_management.dao.definition;

import org.scada_lts.user_management.model.definition.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class responsible for writing and reading User (Definition) in the database
 *
 * @author grzegorz bylica grzegorz.bylica@gmail.com
 */
@Repository("userDao")
public class UserDao implements DefinitionDao<User> {

    private Map<Long, User> users = new HashMap<>();
    private Map<String, User> usersBaseOnName = new HashMap<>();

    /**
     * Save the User (definition) object
      * @param elm
     * @return
     */
    @Override
    public User create(User elm) {
        long id = users.size()+1;
        elm.setId(id);
        users.put(id, elm);
        usersBaseOnName.put(elm.getName(), elm);
        return elm;
    }

    /**
     * Update the User (definition) object
     * @param elm
     */
    @Override
    public void update(User elm) {
        users.put(elm.getId(),elm);
        usersBaseOnName.put(elm.getName().trim().toUpperCase(),elm);
    }

    /**
     * Delete the User (definition) object
     * @param elm
     */
    @Override
    public void delete(User elm) {
        users.remove(elm.getId());
        //TODO update usersBaseOnName
    }

    /**
     * Gets an User (definition) object based on id
     * @param id
     * @return
     */
    @Override
    public User get(Long id) {
        return users.get(id);
    }

    /**
     * Gets an User (definition) object based on name user
     * @param name
     * @return
     */
    public User get(String name) {
        return usersBaseOnName.get(name.trim().toUpperCase());
    }

    /**
     * Retrieves all User (definition) objects
     * @return
     */
    @Override
    public List<User> getAll() {
        return new ArrayList(users.values());
    }
}
