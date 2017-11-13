package org.scada_lts.user_management.service.acl;

import java.util.List;

public interface AclService<T> {

    T create(T elm);

    void update(T elm);

    void delete(T elm);

    T get(Long id);

    List<T> getAll();
}
