package lev.filippov.import_export_csv_util.repository;

import lev.filippov.importexportcsvutil.model.Hardware;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HardwareRepository<T extends Hardware, ID extends Long> extends CrudRepository<T, ID> {
    default Optional<T> findByParameters(T object){
        throw new RuntimeException("Hardware is not a valid entity!");
    }


}
