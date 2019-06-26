package lev.filippov.import_export_csv_util.services;


import com.opencsv.CSVReader;
import lev.filippov.importexportcsvutil.repository.ManufacturerRepository;
import lev.filippov.importexportcsvutil.services.interfaces.CsvParserService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CsvParserServiceImpl implements CsvParserService {

    private final ManufacturerRepository manufacturerRepository;

    public CsvParserServiceImpl( ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<RawObject> parse(File link){
        List<RawObject> rawObjects = new ArrayList<>();
        List<String[]> myEntries;

        if(link.exists()){
            myEntries = readFile(link);
            if(myEntries!= null) {
                for (String[] myEntry : myEntries) {
                    Map<String, String> paramsMap = getParamsMap(myEntry);
                    rawObjects.add(new RawObject(myEntry[0], Integer.valueOf(myEntry[1]), paramsMap));
                }
            } else {
                System.out.println("Пустой файл");
                return null;
            }
        } else {
            System.out.println("Файл не найден!");
        }
        return rawObjects;
    }

    private List<String[]> readFile(File file){

        if(!file.exists()){
            System.out.println("Файл не найден!");
            return null;
        }
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



}
