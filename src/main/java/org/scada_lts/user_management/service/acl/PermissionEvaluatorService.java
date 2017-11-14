package org.scada_lts.user_management.service.acl;

import org.scada_lts.user_management.model.security.Authentication;
import org.scada_lts.user_management.model.security.Permission;
import org.scada_lts.user_management.model.acl.EntityClass;

public class PermissionEvaluatorService {

    public boolean hasPermission(Authentication auth, EntityClass entityClass, Permission permision) {
        return false;
    }

    public boolean hasPermission(String role, EntityClass entityClass, Permission permission) {
        return false;
    }

}
