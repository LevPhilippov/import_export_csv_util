package lev.filippov.import_export_csv_util.repository;

import lev.filippov.import_export_csv_util.model.Manufacturer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ManufacturerRepository extends CrudRepository<Manufacturer, Long> {
    Optional<Manufacturer> getByName(String Name);
}
