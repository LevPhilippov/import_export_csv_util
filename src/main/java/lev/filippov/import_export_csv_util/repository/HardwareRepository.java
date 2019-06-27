package lev.filippov.import_export_csv_util.repository;


import lev.filippov.import_export_csv_util.model.Hardware;
import lev.filippov.import_export_csv_util.model.Manufacturer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HardwareRepository extends CrudRepository<Hardware, Long> {

    default Optional<Hardware> findByParameters(Hardware hardware){
        if(hardware!= null){
            return findByNameAndSerialNumberAndManufacturer_NameAndPrice(hardware.getName(), hardware.getSerialNumber(),
                    hardware.getManufacturer().getName(), hardware.getPrice());
        }
        return null;
    }

    default Integer updateQuantityByParameters (Hardware savedHardware, Hardware newHardware){
        return updateByParameters(savedHardware.getQuantity()+newHardware.getQuantity(),
                savedHardware.getName(), savedHardware.getSerialNumber(),
                savedHardware.getManufacturer(), savedHardware.getPrice());
    }


    Optional<Hardware> findByNameAndSerialNumberAndManufacturer_NameAndPrice(String name, String serialNumber,
                        String manuf_name, Double price);

    //Method is not utilized but tested.
    @Modifying
    @Query("update Hardware h set h.quantity = :quantity where h.name = :name " +
            "and  h.serialNumber = :serialNumber and h.manufacturer = :manufacturer and h.price = :price")
    Integer updateByParameters(@Param("quantity") Integer quantity, @Param("name") String name,
                                          @Param("serialNumber") String serialNumber,@Param("manufacturer") Manufacturer man,
                                          @Param("price") Double price);

}
