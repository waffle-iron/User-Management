package org.scada_lts.user_management.dao.acl;

import org.scada_lts.user_management.model.acl.EntityIdentity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("entityIdentityDao")
public class EntityIdentityDao implements AclDao<EntityIdentity> {

    private Map<Long, EntityIdentity> entity = new HashMap<>();
    //TODO dao

    @Override
    public EntityIdentity create(EntityIdentity elm) {
        long id = entity.size()+1;
        elm.setId(id);
        entity.put(id, elm);
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
