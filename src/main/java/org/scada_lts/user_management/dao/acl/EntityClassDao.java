package org.scada_lts.user_management.dao.acl;

import org.scada_lts.user_management.model.acl.EntityClass;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("entityClassDao")
public class EntityClassDao implements AclDao<EntityClass> {

    private Map<Long, EntityClass> entity = new HashMap<>();
    //TODO dao

    @Override
    public EntityClass create(EntityClass elm) {
        long id = entity.size()+1;
        elm.setId(id);
        entity.put(id, elm);
        return elm;
    }

    @Override
    public void update(EntityClass elm) {
        entity.put(elm.getId(),elm);
    }

    @Override
    public void delete(EntityClass elm) {
        entity.remove(elm.getId());
    }

    @Override
    public EntityClass get(Long id) {
        return entity.get(id);
    }

    @Override
    public List<EntityClass> getAll() {
        return new ArrayList<EntityClass>(entity.values());
    }
}
