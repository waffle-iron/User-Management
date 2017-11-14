package org.scada_lts.user_management.dao.acl;

import org.scada_lts.user_management.model.acl.Sid;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("sidDao")
public class SidDao implements AclDao<Sid> {

    private Map<Long, Sid> sids = new HashMap<>();
    //TODO dao
    private long incrementId = 0L;
    private long generateId() {
        incrementId = incrementId++;
        return incrementId;
    }

    @Override
    public Sid create(Sid elm) {

        //TODO DAO
        elm.setId(generateId());
        sids.put(incrementId, elm);
        return elm;
        //
    }

    @Override
    public void update(Sid elm) {
        sids.put(elm.getId(), elm);
    }

    @Override
    public void delete(Sid elm) {
        sids.remove(elm.getId());
    }

    @Override
    public Sid get(Long id) {
        return sids.get(id);
    }

    @Override
    public List<Sid> getAll() {
       return new ArrayList<Sid>(sids.values());
    }

}
