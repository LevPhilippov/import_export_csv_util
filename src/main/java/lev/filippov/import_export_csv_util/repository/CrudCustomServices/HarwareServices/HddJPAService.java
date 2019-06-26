package lev.filippov.import_export_csv_util.repository.CrudCustomServices.HarwareServices;

import lev.filippov.importexportcsvutil.model.Hdd;
import lev.filippov.importexportcsvutil.repository.EntityCrudInterfaces.HddCrudService;
import lev.filippov.importexportcsvutil.repository.EntityCrudInterfaces.ManufacturerCrudService;
import lev.filippov.importexportcsvutil.repository.HddRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
@Profile("custom")
public class HddJPAService implements HddCrudService<Hdd, Long> {
    private final HddRepository hddRepository;
    private final ManufacturerCrudService manufacturerCrudService;

    public HddJPAService(HddRepository hddRepository, ManufacturerCrudService manufacturerCrudService) {
        this.hddRepository = hddRepository;
        this.manufacturerCrudService = manufacturerCrudService;
    }

    @Override
    public Hdd findById(Long aLong) {
        return hddRepository.findById(aLong).orElse(null);
    }

    @Override
    public Hdd save(Hdd object) {
        Hdd savedHdd = hddRepository.findByParameters(object).orElse(null);
        if(savedHdd != null){
            savedHdd.setQuantity(savedHdd.getQuantity()+object.getQuantity());
            object=savedHdd;
        }
        object.setManufacturer(manufacturerCrudService.save(object.getManufacturer()));
        return hddRepository.save(object);
    }

    @Override
    public Set<Hdd> findAll() {
        Set<Hdd> hddSet = new HashSet<>();
        hddRepository.findAll().forEach(hddSet::add);
        return hddSet;
    }

    @Override
    public void deleteByID(Long aLong) {
        hddRepository.deleteById(aLong);
    }

    @Override
    public void delete(Hdd object) {
        hddRepository.delete(object);
    }
}
