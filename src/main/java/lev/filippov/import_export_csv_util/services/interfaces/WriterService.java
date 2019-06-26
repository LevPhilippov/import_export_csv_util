package lev.filippov.import_export_csv_util.services.interfaces;

import lev.filippov.import_export_csv_util.model.Hardware;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public interface WriterService {
    void writeIntoFile(Set<Hardware> set, File link) throws IOException;
}
