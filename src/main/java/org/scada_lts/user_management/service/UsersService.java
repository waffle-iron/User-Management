package org.scada_lts.user_management.service;

import org.scada_lts.user_management.model.dto.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsersService {

    private Map<Long, User> users = new HashMap<Long, User>();
    private Map<String, User> userNames = new HashMap<String, User>();
    private Long counter = 0L;

    public UsersService() {
        //
    }

    public List<User> getAll() {
        return new ArrayList<User>(users.values());
    }

    public User add(User user) {
        user.setId(counter++);
        users.put(user.getId(),user);
        userNames.put(user.getName().trim().toUpperCase(),user);
        return user;
    }

    public void dell(User user) {
      User userToRemove =  users.get(user.getId());
      userNames.remove(userToRemove);
      users.remove(userToRemove);
    }

    public User getUser(long id) {
        return users.get(id);
    }

    public User getUser(String name) {
        return userNames.get(name.trim().toUpperCase());
    }

}
