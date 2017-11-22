package org.scada_lts.user_management.web.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.scada_lts.user_management.model.acl.Entry;
import org.scada_lts.user_management.service.acl.EntryService;
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

    @RequestMapping(value = "/entry/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getEntry(@PathVariable("id") long id) {

        LOG.info("GET /api/entry/{id} id:"+id);

        Entry entry = entryService.getEntry(id);
        if (entry == null) {
            LOG.warn("entityIdentity id:"+id+" is not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Entry>(entry, HttpStatus.OK);
    }

    @RequestMapping(value = "/entry/", method = RequestMethod.POST)
    public ResponseEntity<?> createEntry(@RequestBody Entry entry, UriComponentsBuilder ucBuilder) {

        LOG.info("POST /entry/ entry:"+ entry);

        if (entryService.getEntry(entry.getId()) != null) {
            LOG.warn("entry is exist");
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        entryService.add(entry);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/entry/{id}").buildAndExpand(entry.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/entry/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateEntry(@PathVariable("id") long id, @RequestBody Entry entry) {

        LOG.info("PUT /entry/{id} id:"+id);

        Entry oldEntry = entryService.getEntry(id);

        if (oldEntry == null) {
            LOG.warn("entry id:"+id+" is not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        if (entry.getId()!=id) {
            LOG.warn("id conflict");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        entryService.update(entry);

        return new ResponseEntity<Entry>(entry, HttpStatus.OK);

    }

    @RequestMapping(value = "/entry/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEntry(@PathVariable("id") long id) {

        LOG.info("DELETE /entry/{id} id:"+id);

        Entry entry = entryService.getEntry(id);
        if (entry == null) {
            LOG.warn("entry id:"+id+" is not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        entryService.delete(entry);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/entry/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAllEntries() {

        LOG.info("DELETE /entry/");

        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }
}
