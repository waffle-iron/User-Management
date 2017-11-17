package org.scada_lts.user_management.service.acl;

import org.scada_lts.user_management.dao.acl.EntryDao;
import org.scada_lts.user_management.model.acl.EntityClass;
import org.scada_lts.user_management.model.acl.EntityIdentity;
import org.scada_lts.user_management.model.acl.Entry;
import org.scada_lts.user_management.model.acl.Sid;
import org.scada_lts.user_management.model.security.Permission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionEvaluatorService {

    @Resource
    private EntryDao entryDao;

    public List<Entry> filterDataBaseOnACL(Sid sid, EntityClass entityClass, Permission permision) {
        //TODO rewrite to 1.8
        List<Entry> entries = entryDao.getAll();
        List<Entry> result = new ArrayList();
        for (Entry entry : entries) {
            if (entry.getSid().equals(sid)) {
                if (entry.getEntity().getEntityClass().equals(entityClass)) {
                    if (entry.getMask().equals(permision.getMask())) {
                        result.add(entry);
                    }
                }

            }
        }
       return result;
    }

    public boolean hasPermission(Sid sid, EntityIdentity entityIdentity, Permission permision) {
         List<Entry> entries = entryDao.getAll();
         for (Entry entry : entries) {
             if (entry.getSid().equals(sid)) {
                 if (entry.getEntity().getEntityIdentity().equals(entityIdentity)) {
                     if (entry.getMask().equals(permision.getMask())){
                         return true;
                     }
                 }
             }
         }
         return false;
    }

    public boolean hasPermission(String role, EntityIdentity entityIdentity, Permission permission) {
        //TODO
        return false;
    }

    //TODO rewrite (now only to test)
    public void setEntryDao(EntryDao ed) {
        this.entryDao = ed;
    }



}
