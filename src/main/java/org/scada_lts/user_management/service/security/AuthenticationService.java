package org.scada_lts.user_management.service.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.scada_lts.user_management.dao.definition.UserDao;
import org.scada_lts.user_management.model.definition.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

  private Map<Long, String> tokens = new HashMap<>();

  @Resource
  private UserDao userDao;

  @Resource
  private SecurityJwtService securityJwtService;

  public String auth(String username, String password) {
      User user = userDao.get(username);
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
      return securityJwtService.checkJwt(token);
  }

}
