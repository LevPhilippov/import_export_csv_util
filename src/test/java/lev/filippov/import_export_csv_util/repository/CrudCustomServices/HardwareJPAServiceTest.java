package lev.filippov.import_export_csv_util.repository.CrudCustomServices;

import lev.filippov.import_export_csv_util.model.Hardware;
import lev.filippov.import_export_csv_util.model.Manufacturer;
import lev.filippov.import_export_csv_util.model.Parameter;
import lev.filippov.import_export_csv_util.repository.HardwareRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HardwareJPAServiceTest {

    @Mock
    HardwareRepository hardwareRepository;
    @InjectMocks
    HardwareJPAService hardwareJPAService;

    Hardware testHardware;

    @BeforeEach
    void setUp(){
        testHardware = new Hardware();
        testHardware.setId(1L);
        testHardware.setName("laptop");
        testHardware.setPrice(11D);
        testHardware.setSerialNumber("qwfdqwd");

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("fsadas");
        testHardware.setManufacturer(manufacturer);
        testHardware.setManufacturer(manufacturer);

        Set<Parameter> parameters = new HashSet<>();
        Parameter parameter = new Parameter();
        parameter.setName("volume");
        parameter.setValue("5");
        Parameter parameter2 = new Parameter();
        parameter2.setName("diagonal");
        parameter2.setValue("15");
        parameters.add(parameter);
        parameters.add(parameter2);
        testHardware.setParameters(parameters);
    }

    @Test
    void findById() {
        when(hardwareRepository.findById(any())).thenReturn(Optional.of(testHardware));
        Hardware savedHardware = hardwareJPAService.findById(testHardware.getId());

        assertEquals(testHardware.getId(), savedHardware.getId());
        verify(hardwareRepository).findById(testHardware.getId());
    }

    @Test
    void saveNull() {
        when(hardwareRepository.save(null)).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> hardwareRepository.save(null));
    }

    @Test
    void findAll() {
        Set<Hardware> hardwares = new HashSet<>();
        hardwares.add(new Hardware());
        hardwares.add(testHardware);
        when(hardwareRepository.findAll()).thenReturn(hardwares);

        Set<Hardware> savedSet = hardwareJPAService.findAll();

        assertEquals(savedSet.size(), hardwares.size());
        verify(hardwareRepository).findAll();
    }
    @Test
    void findByParameters() {
        when(hardwareRepository.findByParameters(any())).thenReturn(Optional.of(testHardware));

        Hardware savedHard = hardwareJPAService.findByParameters(testHardware);
        assertEquals(testHardware,savedHard);
        verify(hardwareRepository).findByParameters(testHardware);

    }
}