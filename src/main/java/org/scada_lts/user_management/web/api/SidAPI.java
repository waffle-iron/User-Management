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
import org.scada_lts.user_management.model.acl.Sid;
import org.scada_lts.user_management.service.acl.SidService;
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
 * Controller for Sid
 * @see org.scada_lts.user_management.model.acl.Sid
 */
@RestController
@RequestMapping("/api")
public class SidAPI {

    private static final Log LOG = LogFactory.getLog(SidAPI.class);

    @Resource
    private SidService sidService;

    @RequestMapping(value = "/sid/", method = RequestMethod.GET)
    public ResponseEntity<List<Sid>> listAllSids() {

        LOG.info("GET /api/sid/");

        List<Sid> sids = sidService.getAll();
        if (sids.isEmpty()) {
            LOG.warn("sids is empty");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Sid>>(sids, HttpStatus.OK);

    }

    @RequestMapping(value = "/sid/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getSid(@PathVariable("id") long id) {

        LOG.info("GET /api/sid/{id} id:"+id);

        Sid sid = sidService.getSid(id);
        if (sid == null) {
            LOG.warn("sid id:"+id+" is not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Sid>(sid, HttpStatus.OK);
    }

    @RequestMapping(value = "/sid/", method = RequestMethod.POST)
    public ResponseEntity<?> createSid(@RequestBody Sid sid, UriComponentsBuilder ucBuilder) {

        LOG.info("POST /sid/ sid:"+ sid);

        if (sidService.getSid(sid.getId()) != null) {
            LOG.warn("sid is exist");
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        sidService.add(sid);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/sid/{id}").buildAndExpand(sid.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/sid/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateSid(@PathVariable("id") long id, @RequestBody Sid sid) {

        LOG.info("PUT /sid/{id} id:"+id);

        Sid oldSid = sidService.getSid(id);

        if (oldSid == null) {
            LOG.warn("sid id:"+id+" is not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if (sid.getId()!=id) {
            LOG.warn("id conflict");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        sidService.update(sid);

        return new ResponseEntity<Sid>(sid, HttpStatus.OK);

    }

    @RequestMapping(value = "/sid/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteSid(@PathVariable("id") long id) {

        LOG.info("DELETE /sid/{id} id:"+id);

        Sid sid = sidService.getSid(id);
        if (sid == null) {
            LOG.warn("sid id:"+id+" is not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        sidService.delete(sid);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/sid/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAllSids() {

        LOG.info("DELETE /sid/");

        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }

}
