package lev.filippov.import_export_csv_util.repository;

import lev.filippov.importexportcsvutil.model.Monitor;

import java.util.Optional;

public interface MonitorRepository extends HardwareRepository<Monitor, Long> {
    @Override
    default Optional<Monitor> findByParameters(Monitor object) {
        return findBySerialNumberAndManufacturer_NameAndDiagonal(object.getSerialNumber(),
                object.getManufacturer().getName(), object.getDiagonal());
    }
    Optional<Monitor> findBySerialNumberAndManufacturer_NameAndDiagonal(String serialNumber, String manufacturerName,
                                                                        Integer diagonal);
}
