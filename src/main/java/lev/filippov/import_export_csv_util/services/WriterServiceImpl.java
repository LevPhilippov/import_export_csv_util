package lev.filippov.import_export_csv_util.services;

import lev.filippov.importexportcsvutil.model.Hardware;
import lev.filippov.importexportcsvutil.services.interfaces.WriterService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

@Service
public class WriterServiceImpl implements WriterService {

    @Override
    public void writeIntoFile(Set<Hardware> hardwares, File link) {
        boolean header=false;
                try(Writer writer = new FileWriter(link, true)){
                    for (Hardware hardware : hardwares) {
                        System.out.println("Writing! "+ hardware);
                        if(!header){
                            writer.write("\n#\n" + hardware.getName() + "\n#");
                            header = true;
                        }
                        writer.write("\n\t" + hardware.toString());
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }

    }
}
