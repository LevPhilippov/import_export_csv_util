package lev.filippov.import_export_csv_util.services.interfaces;

import lev.filippov.import_export_csv_util.model.Hardware;

import java.io.File;
import java.util.Set;

public interface CsvParserService {

    Set<Hardware> parse(File link);
}
