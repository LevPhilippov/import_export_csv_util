package lev.filippov.import_export_csv_util.services;

import lev.filippov.importexportcsvutil.model.Hardware;
import lev.filippov.importexportcsvutil.repository.EntityCrudInterfaces.CrudServices;
import lev.filippov.importexportcsvutil.services.interfaces.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("custom")
public class CsvHandlerServiceCrudImpl implements CsvHandlerService {

    private final CsvParserService csvParserService;
    private final EntityAssemblerService entityAssemblerService;
    private final WriterService writerService;
    private final PersistanceService<Hardware> persistanceService;

    public CsvHandlerServiceCrudImpl(CsvParserService csvParserService, EntityAssemblerService entityAssemblerService, WriterService writerService, PersistanceService<Hardware> persistanceService) {
        this.csvParserService = csvParserService;
        this.entityAssemblerService = entityAssemblerService;
        this.writerService = writerService;
        this.persistanceService = persistanceService;
    }


    @Override
    public void importData(File link) {
        List<RawObject> rawObjects = csvParserService.parse(link);
        for (RawObject rawObject : rawObjects) {
            entityAssemblerService.assembleEntity(rawObject);
        }
    }

    @Override
    public void exportData(File link) {
        Set<CrudServices<? extends Hardware, ? extends Long>> crudServices = persistanceService.getAllCrudServices();
        for (CrudServices crudService : crudServices) {
            Set<Hardware> hardwares = new HashSet<>();
            crudService.findAll().forEach((h) -> hardwares.add((Hardware) h));

            try {
                writerService.writeIntoFile(hardwares, link);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
