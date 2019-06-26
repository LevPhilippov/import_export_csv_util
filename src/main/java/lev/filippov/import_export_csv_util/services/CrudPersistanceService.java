package lev.filippov.import_export_csv_util.services;

import lev.filippov.importexportcsvutil.model.Computer;
import lev.filippov.importexportcsvutil.model.Hardware;
import lev.filippov.importexportcsvutil.repository.CrudCustomServices.HarwareServices.ComputerJPAService;
import lev.filippov.importexportcsvutil.repository.CrudCustomServices.HarwareServices.HddJPAService;
import lev.filippov.importexportcsvutil.repository.CrudCustomServices.HarwareServices.LaptopJPACService;
import lev.filippov.importexportcsvutil.repository.CrudCustomServices.HarwareServices.MonitorJPAService;
import lev.filippov.importexportcsvutil.repository.EntityCrudInterfaces.CrudServices;
import lev.filippov.importexportcsvutil.services.interfaces.PersistanceService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@Profile("custom")
public class CrudPersistanceService implements PersistanceService<Hardware> {

    private final ComputerJPAService computerJPAService;
    private final LaptopJPACService laptopJPACService;
    private final HddJPAService hddJPAService;
    private final MonitorJPAService monitorJPAService;

    public CrudPersistanceService(ComputerJPAService computerJPAService, LaptopJPACService laptopJPACService, HddJPAService hddJPAService, MonitorJPAService monitorJPAService) {
        this.computerJPAService = computerJPAService;
        this.laptopJPACService = laptopJPACService;
        this.hddJPAService = hddJPAService;
        this.monitorJPAService = monitorJPAService;
    }

    @Override
    public Hardware saveOrUpdate(Hardware object) {
        CrudServices<Hardware, Long> crudServices = getCrudService(object);
        if(crudServices != null){
            return crudServices.save(object);
        }
        return null;
    }

    @Override
    public Set<CrudServices<? extends Hardware, ? extends Long>> getAllCrudServices() {
        Set<CrudServices<? extends Hardware, ? extends Long>> crudServices = new HashSet<>();
        crudServices.addAll(Arrays.asList(computerJPAService, monitorJPAService, hddJPAService, laptopJPACService));
        return crudServices;
    }

    private CrudServices getCrudService(Hardware object) {
        switch (object.getName().toLowerCase()){
            case ("computer"): return computerJPAService;
            case ("laptop"): return laptopJPACService;
            case("monitor"): return monitorJPAService;
            case("hdd"): return hddJPAService;
            default: return null;
        }
    }

}
