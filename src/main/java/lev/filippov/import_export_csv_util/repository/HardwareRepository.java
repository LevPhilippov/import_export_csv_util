package lev.filippov.import_export_csv_util.repository;


import lev.filippov.import_export_csv_util.model.Hardware;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HardwareRepository extends CrudRepository<Hardware, Long> {

    default Optional<Hardware> findByParameters(Hardware hardware){
        if(hardware!=null){
            return findByNameAndSerialNumberAndManufacturer_NameAndPrice(hardware.getName(), hardware.getSerialNumber(),
                    hardware.getManufacturer().getName(), hardware.getPrice());
        }
        return null;
    }

    Optional<Hardware> findByNameAndSerialNumberAndManufacturer_NameAndPrice(String name, String serialNumber,
                        String manuf_name, Double price);
}
