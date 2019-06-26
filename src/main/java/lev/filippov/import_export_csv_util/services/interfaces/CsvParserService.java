package lev.filippov.import_export_csv_util.services.interfaces;

import lev.filippov.importexportcsvutil.services.RawObject;

import java.io.File;
import java.util.List;

public interface CsvParserService {

    List<RawObject> parse(File link);
}
