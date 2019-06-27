package lev.filippov.import_export_csv_util.repository.CrudCustomServices;

import lev.filippov.import_export_csv_util.model.Hardware;
import lev.filippov.import_export_csv_util.repository.EntityCrudInterfaces.HardwareCrudService;
import lev.filippov.import_export_csv_util.repository.HardwareRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class HardwareJPAService implements HardwareCrudService {

    private final ManufacturerJPAService manufacturerJPAService;
    private final HardwareRepository hardwareRepository;

    public HardwareJPAService(ManufacturerJPAService manufacturerJPAService, HardwareRepository hardwareRepository) {
        this.manufacturerJPAService = manufacturerJPAService;
        this.hardwareRepository = hardwareRepository;
    }

    @Override
    public Hardware findById(Long aLong) {
        return hardwareRepository.findById(aLong).orElse(null);
    }

    @Override
    public Hardware save(Hardware object) {
        if(object != null){
            if(object.getId() == null) {
                Hardware savedHardware = findByParameters(object);
                if(savedHardware != null){
                    log.info("\"Updating!\"");
                    savedHardware.setQuantity(savedHardware.getQuantity() + object.getQuantity());
                    return hardwareRepository.save(savedHardware);
                }
                log.info("\"Saving!\"");
                object.setManufacturer(manufacturerJPAService.save(object.getManufacturer()));
                return hardwareRepository.save(object);
            }
            return object;
        }
         throw new RuntimeException("Hardware should not be null!");
    }

    @Override
    public Set<Hardware> findAll() {
        Set<Hardware> hardwares = new HashSet<>();
        hardwareRepository.findAll().forEach(hardwares::add);
        return hardwares;
    }

    @Override
    public void deleteByID(Long aLong) {
        hardwareRepository.deleteById(aLong);
    }

    @Override
    public void delete(Hardware object) {
        hardwareRepository.delete(object);
    }

    @Override
    public Hardware findByParameters(Hardware object) {
        return hardwareRepository.findByParameters(object).orElse(null);
    }

}
