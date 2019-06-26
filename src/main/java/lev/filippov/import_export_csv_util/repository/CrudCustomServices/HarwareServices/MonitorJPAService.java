package lev.filippov.import_export_csv_util.repository.CrudCustomServices.HarwareServices;

import lev.filippov.importexportcsvutil.model.Monitor;
import lev.filippov.importexportcsvutil.repository.EntityCrudInterfaces.ManufacturerCrudService;
import lev.filippov.importexportcsvutil.repository.EntityCrudInterfaces.MonitorCrudService;
import lev.filippov.importexportcsvutil.repository.MonitorRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
@Profile("custom")
public class MonitorJPAService implements MonitorCrudService<Monitor, Long> {

    private final MonitorRepository monitorRepository;
    private final ManufacturerCrudService manufacturerCrudService;


    public MonitorJPAService(MonitorRepository monitorRepository, ManufacturerCrudService manufacturerCrudService) {
        this.monitorRepository = monitorRepository;
        this.manufacturerCrudService = manufacturerCrudService;
    }


    @Override
    public Monitor findById(Long aLong) {
        return monitorRepository.findById(aLong).orElse(null);
    }

    @Override
    public Monitor save(Monitor object) {
        Monitor savedMonitor = monitorRepository.findByParameters(object).orElse(null);
        if(savedMonitor !=null) {
            savedMonitor.setQuantity(savedMonitor.getQuantity()+object.getQuantity());
            object=savedMonitor;
        }
        object.setManufacturer(manufacturerCrudService.save(object.getManufacturer()));
        return monitorRepository.save(object);
    }

    @Override
    public Set<Monitor> findAll() {
        Set<Monitor> monitors = new HashSet<>();
        monitorRepository.findAll().forEach(monitors::add);
        return monitors;
    }

    @Override
    public void deleteByID(Long aLong) {
        monitorRepository.deleteById(aLong);
    }

    @Override
    public void delete(Monitor object) {
        monitorRepository.delete(object);
    }
}
