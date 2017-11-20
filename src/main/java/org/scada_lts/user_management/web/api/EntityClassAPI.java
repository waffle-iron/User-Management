/*
 * (c) 2017 Abil'I.T. http://abilit.eu/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.scada_lts.user_management.web.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.scada_lts.user_management.model.acl.EntityClass;
import org.scada_lts.user_management.service.acl.EntityClassService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Arkadiusz Parafiniuk arkadiusz.parafiniuk@gmail.com
 *
 * Controller for EntityClass
 * @see EntityClass
 */
@RestController
@RequestMapping("/api")
public class EntityClassAPI {

    private static final Log LOG = LogFactory.getLog(EntityClassAPI.class);

    @Resource
    private EntityClassService entityClassService;

    @RequestMapping(value = "/entityClass/", method = RequestMethod.GET)
    public ResponseEntity<List<EntityClass>> listAllEntityClasses() {

        LOG.info("GET /api/entityClass/");

        List<EntityClass> entityClasses = entityClassService.getAll();
        if (entityClasses.isEmpty()) {
            LOG.warn("entityClasses is empty");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<EntityClass>>(entityClasses, HttpStatus.OK);

    }

    @RequestMapping(value = "/entityClass/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getEntityClass(@PathVariable("id") long id) {

        LOG.info("GET /api/entityClass/{id} id:"+id);

        EntityClass entityClass = entityClassService.getEntityClass(id);
        if (entityClass == null) {
            LOG.warn("entityClass id:"+id+" is not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<EntityClass>(entityClass, HttpStatus.OK);
    }

    @RequestMapping(value = "entityClass/", method = RequestMethod.POST)
    public ResponseEntity<?> createEntityClass(@RequestBody EntityClass entityClass, UriComponentsBuilder ucBuilder) {

        LOG.info("POST /entityClass/ entityClass:"+ entityClass);

        if (entityClassService.getEntityClass(entityClass.getId()) != null) {
            LOG.warn("entityClass is exist");
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        entityClassService.add(entityClass);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/entityClass/{id}").buildAndExpand(entityClass.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/entityClass/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateEntityClass(@PathVariable("id") long id, @RequestBody EntityClass entityClass) {

        LOG.info("PUT /entityClass/{id} id:"+id);

        EntityClass currentEntityClass = entityClassService.getEntityClass(id);

        if (currentEntityClass == null) {
            LOG.warn("entityClass id:"+id+" is not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        currentEntityClass.setClassName(entityClass.getClassName());
        entityClassService.update(currentEntityClass);

        return new ResponseEntity<EntityClass>(currentEntityClass, HttpStatus.OK);

    }

    @RequestMapping(value = "/entityClass/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEntityClass(@PathVariable("id") long id) {

        LOG.info("DELETE /entityClass/{id} id:"+id);

        EntityClass entityClass = entityClassService.getEntityClass(id);
        if (entityClass == null) {
            LOG.warn("entityClass id:"+id+" is not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        entityClassService.del(entityClass);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/entityClass/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAllEntityClasses() {

        LOG.info("DELETE /entityClass/");

        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }

}
