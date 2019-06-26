package lev.filippov.import_export_csv_util.repository.EntityCrudInterfaces;

import lev.filippov.importexportcsvutil.model.Hardware;

public interface ComputerCrudService<T extends Hardware, ID extends Long> extends CrudServices<T, ID> {
}
