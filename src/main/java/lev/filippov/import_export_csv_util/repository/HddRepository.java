package lev.filippov.import_export_csv_util.repository;

import lev.filippov.importexportcsvutil.model.Hdd;

import java.util.Optional;

public interface HddRepository extends HardwareRepository<Hdd, Long> {
    @Override
    default Optional<Hdd> findByParameters(Hdd object) {
        return findBySerialNumberAndManufacturer_NameAndAndVolume(object.getSerialNumber(),
                object.getManufacturer().getName(), object.getVolume());
    }
    Optional<Hdd> findBySerialNumberAndManufacturer_NameAndAndVolume(String serialNumber,
                                                                     String manufacturerName, Integer volume);
}
