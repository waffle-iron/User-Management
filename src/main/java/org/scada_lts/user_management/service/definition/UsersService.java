package org.scada_lts.user_management.service.definition;

import org.apache.commons.codec.digest.DigestUtils;
import org.scada_lts.user_management.model.definition.User;
import org.scada_lts.user_management.model.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsersService {

    private Map<Long, User> users = new HashMap<>();
    private Map<String, User> userNames = new HashMap<>();
    private Long counter = 0L;

    public UsersService() {
        //
    }

    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    public User add(String username, String password ) {
        User user = new User();
        user.setId(counter++);
        user.setName(username);
        user.setPasswordMd5(DigestUtils.md5Hex(password).toUpperCase());
        users.put(user.getId(),user);
        userNames.put(user.getName().trim().toUpperCase(),user);
        return user;
    }

    public void del(UserDto userDto) {
      User userToRemove =  users.get(userDto.getId());
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
