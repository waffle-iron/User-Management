package org.scada_lts.user_management.service.acl;

import org.scada_lts.user_management.model.acl.Entry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntryService implements AclService<Entry> {

    private Map<Long, Entry> entres = new HashMap<>();
    //TODO dao
    private long incrementId = 0L;
    private long generateId() {
        incrementId = incrementId++;
        return incrementId;
    }

    @Override
    public Entry create(Entry elm) {
        //TODO DAO
        elm.setId(generateId());
        entres.put(incrementId, elm);

        return elm;
    }

    @Override
    public void update(Entry elm) {
        entres.put(elm.getId(), elm);
    }

    @Override
    public void delete(Entry elm) {
        entres.remove(elm.getId());
    }

    @Override
    public Entry get(Long id) {
        return entres.get(id);
    }

    @Override
    public List<Entry> getAll() {
        return new ArrayList<Entry>(entres.values());
    }
}
