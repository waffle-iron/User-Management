package org.scada_lts.user_management.service.definition;

import org.scada_lts.user_management.dao.definition.UserDao;
import org.scada_lts.user_management.model.definition.User;
import org.scada_lts.user_management.model.dto.UserDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersService {

    @Resource
    private UserDao userDao;

    public List<UserDto> getAll() {
        List<User> users = userDao.getAll();
        return users.stream()
                .map(user -> convertToDto(user))
                .collect(Collectors.toList());
    }

    public UserDto add(String username, String password ) {
        return convertToDto(userDao.create(new User(username, password)));
    }

    public void del(UserDto userDto) {
        userDao.delete(convertToEntity(userDto));
    }

    public UserDto getUser(Long id) {
        return convertToDto(userDao.get(id));
    }

    public UserDto getUser(String name) {
        return convertToDto(userDao.get(name));
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto(user.getName());
        userDto.setId(user.getId());
        return userDto;
    }

    private User convertToEntity(UserDto userDto) {
        User user = new User(userDto.getName(),"");
        user.setId(userDto.getId());
        return user;
    }
}
