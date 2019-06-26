package lev.filippov.import_export_csv_util.services;

import lev.filippov.importexportcsvutil.model.*;
import lev.filippov.importexportcsvutil.services.interfaces.EntityAssemblerService;
import lev.filippov.importexportcsvutil.services.interfaces.PersistanceService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

@Service
public class EntityAssemblerServiceImpl implements EntityAssemblerService {

    private final PersistanceService persistanceService;

    public EntityAssemblerServiceImpl(PersistanceService persistanceService) {
        this.persistanceService = persistanceService;
    }

    @Override
    public synchronized void assembleEntity(RawObject rawObject) {
        Map<String, String> params = rawObject.getParams();

        switch (rawObject.getName()) {
            case ("computer"):{
                Computer computer = new Computer();
                computer.setName(rawObject.getName());
                computer.setQuantity(rawObject.getQuantity());
                computer.setPrice(Double.valueOf(params.get("price")));
                computer.setSerialNumber(params.get("serial_number"));
                computer.setManufacturer(getManufacturer(params));
                computer.setFormFactor(getFormFactor(params));
                persistanceService.saveOrUpdate(computer);
                break;
            }
            case ("laptop"): {
                Laptop laptop = new Laptop();
                laptop.setName(rawObject.getName());
                laptop.setQuantity(rawObject.getQuantity());
                laptop.setPrice(Double.valueOf(params.get("price")));
                laptop.setSerialNumber(params.get("serial_number"));
                laptop.setManufacturer(getManufacturer(params));
                laptop.setDisplaySize(Integer.valueOf(params.get("display_size")));
                laptop.setUnitOfMeasure(getUnitOfMeasure(params));
                persistanceService.saveOrUpdate(laptop);
                break;
            }
            case ("hdd"): {
                Hdd hdd = new Hdd();
                hdd.setName(rawObject.getName());
                hdd.setQuantity(rawObject.getQuantity());
                hdd.setPrice(Double.valueOf(params.get("price")));
                hdd.setSerialNumber(params.get("serial_number"));
                hdd.setManufacturer(getManufacturer(params));
                hdd.setVolume(Integer.valueOf(params.get("volume")));
                hdd.setUnitOfMeasure(getUnitOfMeasure(params));
                persistanceService.saveOrUpdate(hdd);
                break;
            }
            case ("monitor"): {
                Monitor monitor = new Monitor();
                monitor.setName(rawObject.getName());
                monitor.setQuantity(rawObject.getQuantity());
                monitor.setPrice(Double.valueOf(params.get("price")));
                monitor.setSerialNumber(params.get("serial_number"));
                monitor.setManufacturer(getManufacturer(params));
                monitor.setDiagonal(Integer.valueOf(params.get("diagonal")));
                monitor.setUnitOfMeasure(getUnitOfMeasure(params));
                persistanceService.saveOrUpdate(monitor);
                break;
            }
            default: {
                System.out.println("Оборудования нет в базе!");
                break;
            }
        }
    }

    private Manufacturer getManufacturer(Map<String, String> parems) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(parems.get("manufacturer"));
        return manufacturer;
    }
    private FormFactor getFormFactor (Map<String, String> parems) {
        return Arrays.stream(FormFactor.class.getEnumConstants()).filter((f) -> f.name().toLowerCase()
                .equals(parems.get("form_factor").toLowerCase())).findAny().orElse(null);
    }
    private UnitOfMeasure getUnitOfMeasure(Map<String, String> parems) {
       return Arrays.stream(UnitOfMeasure.class.getEnumConstants())
                .filter((u)-> u.name().toLowerCase().equals(parems.get("unit_of_measure").toLowerCase()))
               .findAny().orElse(null);

    }

    //TODO with generics

}
