package lev.filippov.import_export_csv_util.services.interfaces;

import java.io.File;

public interface CsvHandlerService {
    void importData(File link);
    void exportData(File link);
}
