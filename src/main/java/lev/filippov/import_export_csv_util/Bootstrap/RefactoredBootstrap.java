package lev.filippov.import_export_csv_util.Bootstrap;

import lev.filippov.importexportcsvutil.model.Computer;
import lev.filippov.importexportcsvutil.model.FormFactor;
import lev.filippov.importexportcsvutil.model.Manufacturer;
import lev.filippov.importexportcsvutil.repository.EntityCrudInterfaces.ComputerCrudService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Profile("custom")
public class RefactoredBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final ComputerCrudService computerCrudService;

    public RefactoredBootstrap(ComputerCrudService computerCrudService) {
        this.computerCrudService = computerCrudService;
    }

    private void initData(){
        Computer computer= new Computer();
        computer.setFormFactor(FormFactor.MONOBLOCK);
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("IBM");
        manufacturer.getHardwares().add(computer);
        computer.setName("computer");
        computer.setPrice(15999d);
        computer.setQuantity(3);
        computer.setSerialNumber("333SN555");
        computer.setManufacturer(manufacturer);
        manufacturer.getHardwares().add(computer);
        computerCrudService.save(computer);

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }
}
