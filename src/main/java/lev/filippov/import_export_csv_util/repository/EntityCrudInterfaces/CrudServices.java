package lev.filippov.import_export_csv_util.repository.EntityCrudInterfaces;

import java.util.Set;

public interface CrudServices<T, ID> {

    T findById(ID id);

    T save(T object);

    Set<T> findAll();

    void deleteByID(ID id);

    void delete(T object);
}
