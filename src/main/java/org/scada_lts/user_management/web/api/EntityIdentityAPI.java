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
import org.scada_lts.user_management.model.acl.EntityIdentity;
import org.scada_lts.user_management.service.acl.EntityIdentityService;
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
 * Controller for EntityIdentity
 * @see org.scada_lts.user_management.model.acl.EntityIdentity
 */
@RestController
@RequestMapping("/api")
public class EntityIdentityAPI {

    private static final Log LOG = LogFactory.getLog(EntityIdentityAPI.class);

    @Resource
    private EntityIdentityService entityIdentityService;

    @RequestMapping(value = "/entityIdentity/", method = RequestMethod.GET)
    public ResponseEntity<List<EntityIdentity>> listAllEntityIdentity() {

        LOG.info("GET /api/entityIdentity/");

        List<EntityIdentity> entityIdentities = entityIdentityService.getAll();
        if (entityIdentities.isEmpty()) {
            LOG.warn("entityIdentities is empty");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<EntityIdentity>>(entityIdentities, HttpStatus.OK);

    }

    @RequestMapping(value = "/entityIdentity/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getEntityIdentity(@PathVariable("id") long id) {

        LOG.info("GET /api/entityIdentity/{id} id:"+id);

        EntityIdentity entityIdentity = entityIdentityService.getEntityIdentity(id);
        if (entityIdentity == null) {
            LOG.warn("entityIdentity id:"+id+" is not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<EntityIdentity>(entityIdentity, HttpStatus.OK);
    }

    @RequestMapping(value = "entityIdentity/", method = RequestMethod.POST)
    public ResponseEntity<?> createEntityIdentity(@RequestBody EntityIdentity entityIdentity, UriComponentsBuilder ucBuilder) {

        LOG.info("POST /entityIdentity/ entityIdentity:"+ entityIdentity);

        if (entityIdentityService.getEntityIdentity(entityIdentity.getId()) != null) {
            LOG.warn("entityIdentity is exist");
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        entityIdentityService.add(entityIdentity);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/entityIdentity/{id}").buildAndExpand(entityIdentity.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/entityIdentity/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateEntityIdentity(@PathVariable("id") long id, @RequestBody EntityIdentity entityIdentity) {

        LOG.info("PUT /entityIdentity/{id} id:"+id);

        EntityIdentity currentEntityIdentity = entityIdentityService.getEntityIdentity(id);

        if (currentEntityIdentity == null) {
            LOG.warn("entityIdentity id:"+id+" is not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        currentEntityIdentity.setEntityIdentity(entityIdentity);
        entityIdentityService.update(currentEntityIdentity);

        return new ResponseEntity<EntityIdentity>(currentEntityIdentity, HttpStatus.OK);

    }

    @RequestMapping(value = "/entityIdentity/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEntityIdentity(@PathVariable("id") long id) {

        LOG.info("DELETE /entityIdentity/{id} id:"+id);

        EntityIdentity entityIdentity = entityIdentityService.getEntityIdentity(id);
        if (entityIdentity == null) {
            LOG.warn("entityIdentity id:"+id+" is not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        entityIdentityService.del(entityIdentity);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/entityIdentity/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAllEntityIdentities() {

        LOG.info("DELETE /entityIdentity/");

        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }
}
