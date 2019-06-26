package lev.filippov.import_export_csv_util.repository;

import lev.filippov.importexportcsvutil.model.Computer;
import lev.filippov.importexportcsvutil.model.FormFactor;

import java.util.Optional;


public interface ComputerRepository extends HardwareRepository<Computer, Long> {

    @Override
    default Optional<Computer> findByParameters(Computer computer) {
        return findBySerialNumberAndManufacturer_NameAndFormFactor(computer.getSerialNumber(),
                computer.getManufacturer().getName(), computer.getFormFactor());
    }
    Optional<Computer> findBySerialNumberAndManufacturer_NameAndFormFactor(String serialNumber, String manufacturerName, FormFactor formFactor);
}
