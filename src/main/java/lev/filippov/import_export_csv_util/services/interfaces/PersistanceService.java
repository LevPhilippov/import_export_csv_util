package lev.filippov.import_export_csv_util.services.interfaces;


import lev.filippov.importexportcsvutil.repository.EntityCrudInterfaces.CrudServices;

import java.util.Set;

public interface PersistanceService<T> {

    T saveOrUpdate(T object);

    Set<CrudServices<? extends T, ? extends Long>> getAllCrudServices();


}
