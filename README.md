# User-Management

[![GitHub license](https://img.shields.io/github/license/SCADA-LTS/User-Management.svg)](https://github.com/SCADA-LTS/User-Management/blob/master/LICENSE)
[![Build Status](https://travis-ci.org/SCADA-LTS/User-Management.svg?branch=develop)](https://travis-ci.org/SCADA-LTS/User-Management)

User management for Scada-LTS base on ACL

## Quick start with InteliJ

1. Import project by embedded IntelIJ VCS plugin.
2. Open terminal in the project main directory and type:
 ```gradlew build clean```
3. Run project from .jar file by command:

   Windows: ```java -jar build\libs\user-management-0.0.1.jar```

   Linux:```java -jar build/libs/user-management-0.0.1.jar```
 
 4. Application should be available at address:  ```http://localhost:8080/```
 
 ## Swagger and Postman integration
Swagger UI API documentation is available at address: ```http://localhost:8080/swagger-ui.html#/```

If you want to import API docs from Swagger to Postman: 
1. Select "Import from link" at Postman app.
2. Type URL: ```http://localhost:8080/v2/api-docs```

## Creators 

**Grzesiek Bylica**

* <https://github.com/grzesiekb>

**Arkadiusz Parafiniuk**

* <https://github.com/ArkadiuszParafiniuk>

## Copyright and license

Code released under [the GPL 3.0 license](https://github.com/SCADA-LTS/User-Management/blob/master/LICENSE). 
