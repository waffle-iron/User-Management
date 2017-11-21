package org.scada_lts.user_management.web.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.scada_lts.user_management.model.acl.Entry;
import org.scada_lts.user_management.service.acl.EntryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Arkadiusz Parafiniuk arkadiusz.parafiniuk@gmail.com
 *
 * Controller for Entry
 * @see Entry
 */
@RestController
@RequestMapping("/api")
public class EntryAPI {

    private static final Log LOG = LogFactory.getLog(EntryAPI.class);

    @Resource
    private EntryService entryService;

    @RequestMapping(value = "/entry/", method = RequestMethod.GET)
    public ResponseEntity<List<Entry>> listAllEntityIdentity() {

        LOG.info("GET /api/entry/");

        List<Entry> entries = entryService.getAll();
        if (entries.isEmpty()) {
            LOG.warn("entries is empty");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Entry>>(entries, HttpStatus.OK);

    }
}
