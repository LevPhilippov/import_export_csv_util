package lev.filippov.import_export_csv_util.repository.CrudCustomServices.ProrertyServices;

import lev.filippov.importexportcsvutil.model.Manufacturer;
import lev.filippov.importexportcsvutil.repository.EntityCrudInterfaces.ManufacturerCrudService;
import lev.filippov.importexportcsvutil.repository.ManufacturerRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("custom")
public class ManufacturerJPAService implements ManufacturerCrudService {
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerJPAService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public Manufacturer findById(Long aLong) {
        return manufacturerRepository.findById(aLong).orElse(null);
    }

    @Override
    public Manufacturer save(Manufacturer object) {
        if(object != null) {
            if(object.getId() == null) {
                Optional<Manufacturer> opt = manufacturerRepository.getByName(object.getName());
                if(opt.isPresent()){
                    return opt.get();
                } else {
                    return manufacturerRepository.save(object);
                }
            } else {
                return manufacturerRepository.findById(object.getId()).orElse(null);
            }
        }
        return null;
    }

    @Override
    public Set<Manufacturer> findAll() {
        Set<Manufacturer> manufacturers = new HashSet<>();
        manufacturerRepository.findAll().forEach(manufacturers::add);
        return manufacturers;
    }

    @Override
    public void deleteByID(Long aLong) {
        manufacturerRepository.deleteById(aLong);
    }

    @Override
    public void delete(Manufacturer object) {
        manufacturerRepository.delete(object);
    }
}
