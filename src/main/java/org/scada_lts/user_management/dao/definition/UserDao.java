package org.scada_lts.user_management.dao.definition;

import org.scada_lts.user_management.model.definition.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("userDao")
public class UserDao implements DefinitionDao<User> {

    private Map<Long, User> users = new HashMap<>();
    private Map<String, User> usersBaseOnName = new HashMap<>();

    @Override
    public User create(User elm) {
        long id = users.size()+1;
        elm.setId(id);
        users.put(id, elm);
        usersBaseOnName.put(elm.getName(), elm);
        return elm;
    }

    @Override
    public void update(User elm) {
        users.put(elm.getId(),elm);
        usersBaseOnName.put(elm.getName().trim().toUpperCase(),elm);
    }

    @Override
    public void delete(User elm) {
        users.remove(elm.getId());
        //TODO update usersBaseOnName
    }

    @Override
    public User get(Long id) {
        return users.get(id);
    }

    public User get(String name) {
        return usersBaseOnName.get(name.trim().toUpperCase());
    }

    @Override
    public List<User> getAll() {
        return new ArrayList(users.values());
    }
}
