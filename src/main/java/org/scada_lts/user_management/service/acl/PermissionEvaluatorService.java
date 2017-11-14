package org.scada_lts.user_management.service.acl;

import org.scada_lts.user_management.dao.acl.EntityClassDao;
import org.scada_lts.user_management.dao.acl.EntityIdentityDao;
import org.scada_lts.user_management.dao.acl.EntryDao;
import org.scada_lts.user_management.dao.acl.SidDao;
import org.scada_lts.user_management.model.acl.EntityClass;
import org.scada_lts.user_management.model.acl.Sid;
import org.scada_lts.user_management.model.security.Permission;

import javax.annotation.Resource;
import java.util.List;

public class PermissionEvaluatorService {

    @Resource
    private EntryDao entryDao;

    @Resource
    private EntityIdentityDao entityIdentityDao;

    @Resource
    private EntityClassDao entityClassDao;

    @Resource
    private SidDao sidDao;


    public List filterDataBaseOnACL(Sid sid, EntityClass entityClass, Permission permision) {
       return null;
    }

    public boolean hasPermission(Sid sid, EntityClass entityClass, Permission permision) {
         return false;
    }

    public boolean hasPermission(String role, EntityClass entityClass, Permission permission) {
        return false;
    }

}
