package lev.filippov.import_export_csv_util.repository.CrudCustomServices.HarwareServices;

import lev.filippov.importexportcsvutil.model.Laptop;
import lev.filippov.importexportcsvutil.repository.EntityCrudInterfaces.LaptopCrudService;
import lev.filippov.importexportcsvutil.repository.EntityCrudInterfaces.ManufacturerCrudService;
import lev.filippov.importexportcsvutil.repository.LaptopRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
@Profile("custom")
public class LaptopJPACService implements LaptopCrudService<Laptop, Long> {

    private final LaptopRepository laptopRepository;
    private final ManufacturerCrudService manufacturerCrudService;


    public LaptopJPACService(LaptopRepository laptopRepository, ManufacturerCrudService manufacturerCrudService) {
        this.laptopRepository = laptopRepository;
        this.manufacturerCrudService = manufacturerCrudService;
    }

    @Override
    public Laptop findById(Long aLong) {
        return laptopRepository.findById(aLong).orElse(null);
    }

    @Override
    public Laptop save(Laptop object) {
        Laptop savedLaptop = laptopRepository.findByParameters(object).orElse(null);
        if(savedLaptop !=null) {
            savedLaptop.setQuantity(savedLaptop.getQuantity()+object.getQuantity());
            object=savedLaptop;
        }
        object.setManufacturer(manufacturerCrudService.save(object.getManufacturer()));
        return laptopRepository.save(object);
    }

    @Override
    public Set<Laptop> findAll() {
        Set<Laptop> laptops = new HashSet<>();
        laptopRepository.findAll().forEach(laptops::add);
        return laptops;
    }

    @Override
    public void deleteByID(Long aLong) {
        laptopRepository.deleteById(aLong);
    }

    @Override
    public void delete(Laptop object) {
        laptopRepository.delete(object);
    }
}
