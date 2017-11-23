package org.scada_lts.user_management.service.definition;

import org.scada_lts.user_management.dao.definition.UserDao;
import org.scada_lts.user_management.model.definition.User;
import org.scada_lts.user_management.model.dto.UserDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServiceImp implements UsersService {

    @Resource
    private UserDao userDao;

    @Override
    public List<UserDto> getAll() {
        List<User> users = userDao.getAll();
        return users.stream()
                .map(user -> convertToDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto add(String username, String password) {
        return convertToDto(userDao.create(new User(username, password)));
    }

    @Override
    public void del(UserDto userDto) {
        userDao.delete(convertToEntity(userDto));
    }

    @Override
    public UserDto getUser(Long id) {
        return convertToDto(userDao.get(id));
    }

    @Override
    public UserDto getUser(String name) {
        return convertToDto(userDao.get(name));
    }

    @Override
    public void update(UserDto userDto) {
       userDao.update(convertToEntity(userDto));
    }

    private UserDto convertToDto(User user) {
        if (user != null) {
            UserDto userDto = new UserDto(user.getName());
            userDto.setId(user.getId());
            return userDto;
        }
        return null;
    }

    private User convertToEntity(UserDto userDto) {
        if (userDto != null) {
            User user = new User(userDto.getName(), "");
            user.setId(userDto.getId());
            return user;
        }
        return null;
    }
}
