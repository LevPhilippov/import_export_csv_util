package lev.filippov.import_export_csv_util.services;

import lev.filippov.import_export_csv_util.model.Hardware;
import lev.filippov.import_export_csv_util.services.interfaces.WriterService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

@Service
public class WriterServiceImpl implements WriterService {

    @Override
    public void writeIntoFile(Set<Hardware> unsortedHardwareSet, File link) throws IOException {
        boolean header=false;
        String currentHeader = null;
        TreeSet<Hardware> hardwares = new TreeSet<>(unsortedHardwareSet);

        try(Writer writer = new FileWriter(link, true)){
            for (Hardware hardware : hardwares) {
                System.out.println("Writing! "+ hardware);
                if(!hardware.getName().equals(currentHeader)){
                    header = false;
                }
                if(!header){
                    currentHeader=hardware.getName();
                    header = true;
                    writer.write("\n#\n" + hardware.getName() + "\n#");
                }
                writer.write("\n\t" + hardware.toString());
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
