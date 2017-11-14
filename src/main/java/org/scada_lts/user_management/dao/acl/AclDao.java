package org.scada_lts.user_management.dao.acl;

import java.util.List;

public interface AclDao<T> {

    T create(T elm);

    void update(T elm);

    void delete(T elm);

    T get(Long id);

    List<T> getAll();
}
