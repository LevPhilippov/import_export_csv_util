package lev.filippov.import_export_csv_util.services;



import com.opencsv.CSVReader;
import lev.filippov.import_export_csv_util.model.Hardware;
import lev.filippov.import_export_csv_util.model.Manufacturer;
import lev.filippov.import_export_csv_util.model.Parameter;
import lev.filippov.import_export_csv_util.repository.ManufacturerRepository;
import lev.filippov.import_export_csv_util.services.interfaces.CsvParserService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class CsvParserServiceImpl implements CsvParserService {

    private final ManufacturerRepository manufacturerRepository;

    public CsvParserServiceImpl( ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public Set<Hardware> parse(File link){
        Set <Hardware> hardwares = new HashSet<>();
        List<String[]> myEntries;

        myEntries = readFile(link);

        if(myEntries!= null) {
            for (String[] myEntry : myEntries) {
                hardwares.add(buildHardware(myEntry));
            }
        } else {
            System.out.println("Пустой файл");
            return null;
        }
        return hardwares;
    }

    private List<String[]> readFile(File file){
        List<String[]> myEntries=null;
        try(FileReader fileReader = new FileReader(file)) {
            CSVReader reader = new CSVReader(fileReader, ';');
            myEntries = reader.readAll();
        } catch (IOException e){
            e.printStackTrace();
        }
        return myEntries;
    }

    private Map<String, String> getParamsMap(String[] params ) {
        Map<String, String> paramsMap = new HashMap<>();
        for (String param : params) {
            if(param.contains(":")){
                paramsMap.put(param.split(":")[0].trim(),param.split(":")[1].trim());
            }
        }
        return paramsMap;
    }

    private Hardware buildHardware(String[] myEntry) {
        Map<String, String> paramsMap = getParamsMap(myEntry);
        Hardware hardware = new Hardware();

        hardware.setName(myEntry[0]);
        hardware.setSerialNumber(paramsMap.remove("serial_number"));

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(paramsMap.remove("manufacturer"));
        hardware.setManufacturer(manufacturer);

        hardware.setPrice(Double.valueOf(paramsMap.remove("price")));
        hardware.setQuantity(Integer.valueOf(myEntry[1]));

        for (String key : paramsMap.keySet()) {
            Parameter parameter = new Parameter();
            parameter.setName(key.toLowerCase());
            parameter.setValue(paramsMap.get(key));
            parameter.setHardware(hardware);
            hardware.getParameters().add(parameter);
        }
        return hardware;
    }





}
