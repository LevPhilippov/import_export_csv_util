package lev.filippov.import_export_csv_util.repository.CrudCustomServices.HarwareServices;

import lev.filippov.importexportcsvutil.model.Computer;
import lev.filippov.importexportcsvutil.repository.ComputerRepository;
import lev.filippov.importexportcsvutil.repository.EntityCrudInterfaces.ComputerCrudService;
import lev.filippov.importexportcsvutil.repository.EntityCrudInterfaces.ManufacturerCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("custom")
public class ComputerJPAService implements ComputerCrudService<Computer, Long> {

    private final ComputerRepository computerRepository;
    private final ManufacturerCrudService manufacturerCrudService;
    @Autowired
    public ComputerJPAService(ComputerRepository computerRepository, ManufacturerCrudService manufacturerCrudService) {
        this.computerRepository = computerRepository;
        this.manufacturerCrudService = manufacturerCrudService;
    }

    @Override
    public Computer findById(Long aLong) {
        return computerRepository.findById(aLong).orElse(null);
    }

    @Override
    public Computer save(Computer object) {
        Computer savedComputer= computerRepository.findByParameters(object).orElse(null);
        if(savedComputer != null) {
            savedComputer.setQuantity(savedComputer.getQuantity()+ object.getQuantity());
            object = savedComputer;
        }
        object.setManufacturer(manufacturerCrudService.save(object.getManufacturer()));
        return computerRepository.save(object);
    }

    @Override
    public Set<Computer> findAll() {
        Set<Computer> computers = new HashSet<>();
        computerRepository.findAll().forEach(computers::add);
        return computers;
    }

    @Override
    public void deleteByID(Long aLong) {
        computerRepository.deleteById(aLong);
    }

    @Override
    public void delete(Computer object) {
        computerRepository.delete(object);
    }

}
