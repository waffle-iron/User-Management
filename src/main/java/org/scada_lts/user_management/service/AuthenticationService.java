package org.scada_lts.user_management.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.scada_lts.user_management.model.dto.User;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationService {

  private Map<Long, String> tokens = new HashMap<Long,String>();

  @Resource
  UsersService usersService;

  @Resource
  SecurityJwtService securityJwtService;

  public String auth(String username, String password) {
      User user = usersService.getUser(username);
      String result="";
      if (user.getPasswordMd5().equals(DigestUtils.md5Hex(password).toUpperCase())) {
          String token = tokens.get(user.getId());
          if (securityJwtService.checkJwt(token)) {
              result = token;
          } else {
              result = securityJwtService.generateToken(username);
              tokens.put(user.getId(), result);
          }
      }

      return result;
  }

  public boolean auth(String token) {
      //TODO
      return false;
  }







}
