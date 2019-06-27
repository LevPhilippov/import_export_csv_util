package lev.filippov.import_export_csv_util.services;

import lev.filippov.import_export_csv_util.model.Hardware;
import lev.filippov.import_export_csv_util.repository.EntityCrudInterfaces.HardwareCrudService;
import lev.filippov.import_export_csv_util.services.interfaces.CsvHandlerService;
import lev.filippov.import_export_csv_util.services.interfaces.CsvParserService;
import lev.filippov.import_export_csv_util.services.interfaces.WriterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CsvHandlerServiceImplTest {
    @Mock
    CsvParserService csvParserService;
    @Mock
    WriterService writerService;
    @Mock
    HardwareCrudService hardwareCrudService;
    @InjectMocks
    CsvHandlerServiceImpl csvHandlerService;
    Set<Hardware> set;

    @BeforeEach
    void setUp(){
        set = new HashSet<>();
        Hardware hardware = new Hardware();
        hardware.setId(1L);
        Hardware hardware1 = new Hardware();
        hardware1.setId(2L);
        set.add(hardware);
        set.add(hardware1);

    }
    @Test
    void importData() {
        when(csvParserService.parse(any())).thenReturn(set);
        csvHandlerService.importData(new File("dasda"));
        verify(csvParserService).parse(any());
        verify(hardwareCrudService, times(set.size())).save(any());
    }

    @Test
    void exportData() {
        when(hardwareCrudService.findAll()).thenReturn(set);

        csvHandlerService.exportData(new File("dsadsa"));

        verify(hardwareCrudService).findAll();
        try {
            verify(writerService).writeIntoFile(any(), any());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}