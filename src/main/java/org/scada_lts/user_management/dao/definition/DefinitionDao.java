package org.scada_lts.user_management.dao.definition;

import java.util.List;

public interface DefinitionDao<T> {

    T create(T elm);

    void update(T elm);

    void delete(T elm);

    T get(Long id);

    List<T> getAll();
}
