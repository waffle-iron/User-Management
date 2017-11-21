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
import org.scada_lts.user_management.model.acl.Entry;
import org.scada_lts.user_management.model.dto.InputFilterAcl;
import org.scada_lts.user_management.model.dto.InputHasPermission;
import org.scada_lts.user_management.service.acl.PermissionEvaluatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Grzegorz Bylica grzegorz.bylica@gmail.com
 **/
@RestController
@RequestMapping("/api")
public class PermissionEvaluatorAPI {

    private static final Log LOG = LogFactory.getLog(PermissionEvaluatorAPI.class);

    @Resource
    private PermissionEvaluatorService permissionEvaluatorService;

    @RequestMapping(value = "/permission/filter", method = RequestMethod.POST)
    public ResponseEntity<List<Entry>> filter(@RequestBody InputFilterAcl inputFilterAcl) {

        LOG.info("POST /api/permission/filter inputFilterAcl:"+inputFilterAcl.toString());
        try {
            List<Entry> entries = permissionEvaluatorService.filterDataBaseOnACL(inputFilterAcl.getSid(), inputFilterAcl.getEntityClass(), inputFilterAcl.getPermision());
            return new ResponseEntity<List<Entry>>(entries, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/permission/hasPermission", method = RequestMethod.POST)
    public ResponseEntity<Boolean> hasPermission(@RequestBody InputHasPermission inputHasPermission) {

        LOG.info("POST /api/permission/hasPermission inputHasPermission:"+inputHasPermission.toString());
        try {
            Boolean hasPermission = permissionEvaluatorService.hasPermission(inputHasPermission.getSid(), inputHasPermission.getEntityIdentity(), inputHasPermission.getPermission());
            return new ResponseEntity<Boolean>(hasPermission, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
