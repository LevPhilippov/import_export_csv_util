package lev.filippov.import_export_csv_util.repository;

import lev.filippov.importexportcsvutil.model.Laptop;

import java.util.Optional;

public interface LaptopRepository extends HardwareRepository<Laptop, Long> {
    @Override
    default Optional<Laptop> findByParameters(Laptop object) {
        return findBySerialNumberAndManufacturer_NameAndDisplaySize(object.getSerialNumber(),
                object.getManufacturer().getName(), object.getDisplaySize());
    }

    Optional<Laptop> findBySerialNumberAndManufacturer_NameAndDisplaySize(String serialNumber,
                                                                          String manufacturerName, Integer displaySize);
}
