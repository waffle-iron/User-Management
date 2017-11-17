package org.scada_lts.user_management.service.definition;

import org.scada_lts.user_management.model.dto.UserDto;

import java.util.List;

public interface UsersService {
    List<UserDto> getAll();

    UserDto add(String username, String password);

    void del(UserDto userDto);

    UserDto getUser(Long id);

    UserDto getUser(String name);

    void update(UserDto userDto);
}
