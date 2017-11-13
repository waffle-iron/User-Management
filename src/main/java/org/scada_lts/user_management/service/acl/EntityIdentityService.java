package org.scada_lts.user_management.service.acl;

import org.scada_lts.user_management.model.acl.EntityIdentity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityIdentityService implements AclService<EntityIdentity> {

    private Map<Long, EntityIdentity> entity = new HashMap<>();
    //TODO dao
    private long incrementId = 0L;
    private long generateId() {
        incrementId = incrementId++;
        return incrementId;
    }

    @Override
    public EntityIdentity create(EntityIdentity elm) {
        elm.setId(generateId());
        entity.put(incrementId, elm);
        return elm;
    }

    @Override
    public void update(EntityIdentity elm) {
        entity.put(elm.getId(), elm);
    }

    @Override
    public void delete(EntityIdentity elm) {
        entity.remove(elm.getId());

    }

    @Override
    public EntityIdentity get(Long id) {
        return entity.get(id);
    }

    @Override
    public List<EntityIdentity> getAll() {
        return new ArrayList<EntityIdentity>(entity.values());
    }
}
