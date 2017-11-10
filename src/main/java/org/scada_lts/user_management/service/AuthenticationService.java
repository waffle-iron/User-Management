package org.scada_lts.user_management.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.scada_lts.user_management.model.dao.User;
import org.scada_lts.user_management.model.dto.UserDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

  private Map<Long, String> tokens = new HashMap<>();

  @Resource
  private UsersService usersService;

  @Resource
  private SecurityJwtService securityJwtService;

  public String auth(UserDto userDto) {
      User user = usersService.getUser(userDto.getName());
      String result="";
      if (user.getPasswordMd5().equals(DigestUtils.md5Hex(userDto.getPassword()).toUpperCase())) {
          String token = tokens.get(user.getId());
          if (securityJwtService.checkJwt(token)) {
              result = token;
          } else {
              result = securityJwtService.generateToken(userDto.getName());
              tokens.put(user.getId(), result);
          }
      }

      return result;
  }

  public boolean auth(String token) {
      return securityJwtService.checkJwt(token);
  }







}
