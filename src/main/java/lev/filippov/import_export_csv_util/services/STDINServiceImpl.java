package lev.filippov.import_export_csv_util.services;

import lev.filippov.importexportcsvutil.services.interfaces.CsvHandlerService;
import lev.filippov.importexportcsvutil.services.interfaces.STDInService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

@Service
public class STDINServiceImpl implements STDInService {

    private final CsvHandlerService csvHandlerService;

    public STDINServiceImpl(CsvHandlerService csvHandlerService) {
        this.csvHandlerService = csvHandlerService;
    }

    @Override
    public void runSTDIN(){
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNextLine()) {
                String command = scanner.nextLine().trim();
                File file = null;
                System.out.println(command);
                if (command.startsWith("import")) {
                    String link = command.replace("import", "").trim();
                    file = new File(link);
                    if(file.exists()){
                        System.out.println("Importing data!");
                        csvHandlerService.importData(file);
                    } else {
                        System.out.println("File is not found!");
                        continue;
                    }
                } else if (command.startsWith("export")) {
                    String link = command.replace("export", "").trim();
                    file = new File(link);
                    if(file.exists()){
                        System.out.println("File already exist! Do you want to rewrite? Y/N");
                        String ans = scanner.nextLine();
                        if ("Y".equals(ans)) {
                            file.delete();
                            try {
                                file.createNewFile();
                                System.out.println("File was recreated!");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else continue;
                    } else {
                        try {
                            file.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Exporting data!");
                    csvHandlerService.exportData(file);
                } else if(command.startsWith("end") || command.startsWith("exit")) {
                    System.exit(1);
                } else if (command.startsWith("help")) {
                    System.out.println("Use commands:\n import [absolute or local link to .csv for reading file]\n " +
                            "export [absolute or local link to .csv for reading file] for exporting data\n" +
                            "Note that if your export lint points at unexisting folder/s they will be created automatically!\n" +
                            "exit or end to terminate the app.");
                } else {
                    System.out.println(command + " <- Unknown command, please use command: help");
                }
            }

        }).start();
    }


    //TODO refactor to Threadpools
}
