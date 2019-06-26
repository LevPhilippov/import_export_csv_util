package lev.filippov.import_export_csv_util.services;

import lev.filippov.import_export_csv_util.model.Hardware;
import lev.filippov.import_export_csv_util.repository.EntityCrudInterfaces.HardwareCrudService;
import lev.filippov.import_export_csv_util.services.interfaces.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

@Service
public class CsvHandlerServiceCrudImpl implements CsvHandlerService {

    private final CsvParserService csvParserService;
    private final WriterService writerService;
    private final HardwareCrudService hardwareCrudService;

    public CsvHandlerServiceCrudImpl(CsvParserService csvParserService, WriterService writerService, HardwareCrudService hardwareCrudService) {
        this.csvParserService = csvParserService;
        this.writerService = writerService;
        this.hardwareCrudService = hardwareCrudService;
    }


    @Override
    public void importData(File link) {
        Set<Hardware> hardwares = csvParserService.parse(link);
        for (Hardware hardware : hardwares) {
            System.out.println(hardware);
            hardwareCrudService.save(hardware);
        }
    }

    @Override
    public void exportData(File link) {
        Set<Hardware> hardwares = hardwareCrudService.findAll();
            try {
                writerService.writeIntoFile(hardwares, link);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
