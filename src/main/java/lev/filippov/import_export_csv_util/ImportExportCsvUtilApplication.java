package lev.filippov.import_export_csv_util;

import lev.filippov.import_export_csv_util.services.interfaces.STDInService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ImportExportCsvUtilApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(ImportExportCsvUtilApplication.class, args);
        applicationContext.getBean(STDInService.class).runSTDIN();
    }

}
