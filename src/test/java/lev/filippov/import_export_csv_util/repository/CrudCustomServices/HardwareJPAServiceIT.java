package lev.filippov.import_export_csv_util.repository.CrudCustomServices;

import lev.filippov.import_export_csv_util.model.Hardware;
import lev.filippov.import_export_csv_util.model.Manufacturer;
import lev.filippov.import_export_csv_util.model.Parameter;
import lev.filippov.import_export_csv_util.repository.HardwareRepository;
import lev.filippov.import_export_csv_util.repository.ManufacturerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
class HardwareJPAServiceIT {

    @Autowired
    HardwareRepository hardwareRepository;
    @Autowired
    ManufacturerRepository manufacturerRepository;

    HardwareJPAService hardwareJPAService;
    ManufacturerJPAService manufacturerJPAService;

    Hardware testHardware;

    @BeforeEach
    void setUp(){
        manufacturerJPAService = new ManufacturerJPAService(manufacturerRepository);
        hardwareJPAService = new HardwareJPAService(manufacturerJPAService, hardwareRepository);

        testHardware = getHardwareImpl();
    }

    Hardware getHardwareImpl() {

        Hardware newHardware = new Hardware();
        newHardware.setId(1L);
        newHardware.setName("laptop");
        newHardware.setPrice(11D);
        newHardware.setSerialNumber("111222333");
        newHardware.setQuantity(5);

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("J7");
        manufacturer.getHardwares().add(newHardware);
        newHardware.setManufacturer(manufacturer);

        Set<Parameter> parameters = new HashSet<>();
        Parameter parameter = new Parameter();
        parameter.setName("volume");
        parameter.setValue("5");
        Parameter parameter2 = new Parameter();
        parameter2.setName("diagonal");
        parameter2.setValue("15");
        parameters.add(parameter);
        parameters.add(parameter2);

        newHardware.setParameters(parameters);
        return newHardware;
    }

    @Test
    void findById() {
    }

    @Test
    void saveNull() {
        assertThrows(RuntimeException.class, () -> hardwareJPAService.save(null));
    }

    @Test
    void saveWithId() {
        assertNotNull(testHardware.getId());
        Hardware savedHardware = hardwareJPAService.save(testHardware);
        assertEquals(testHardware, savedHardware);
    }
    @Test
    void saveWithOutId() {
        testHardware.setId(null);
        assertNull(testHardware.getId());
        Hardware savedHardware = hardwareJPAService.save(testHardware);
        assertNotNull(savedHardware.getId());
        assertEquals(savedHardware, testHardware);
    }

    @Test
    void findAll() {
    }

    @Test
    void findNotSavedByPapams(){
        assertEquals(null, hardwareJPAService.findByParameters(testHardware));
    }

    @Test
    void incrementHardwareQuantity() {
        testHardware.setId(null);
        assertNull(testHardware.getId());
        hardwareJPAService.save(testHardware);
        int savedQuantity = testHardware.getQuantity();
        assertEquals(5, savedQuantity);

        Hardware newHardware = getHardwareImpl();
        newHardware.setId(null);
        hardwareJPAService.save(newHardware);
        savedQuantity = testHardware.getQuantity();
        assertEquals(10, savedQuantity);

    }
    @Test
    void incrementHardwareQuantityByUpdateMethod() {
        testHardware.setId(null);
        assertNull(testHardware.getId());
        hardwareJPAService.save(testHardware);
        int savedQuantity = testHardware.getQuantity();
        assertEquals(5, savedQuantity);

        Hardware newHardware = getHardwareImpl();
        newHardware.setId(null);
        int rows = hardwareRepository.updateQuantityByParameters(testHardware,newHardware);
        assertEquals(1,rows);

    }

    @Test
    void findByParameters(){
        testHardware.setId(null);
        hardwareJPAService.save(testHardware);
        Hardware newHardware = getHardwareImpl();
        assertEquals(testHardware.getName(), newHardware.getName());
        assertEquals(testHardware.getManufacturer().getName(), newHardware.getManufacturer().getName());
        assertEquals(testHardware.getSerialNumber(), newHardware.getSerialNumber());
        assertEquals(testHardware.getPrice(), newHardware.getPrice());

        Hardware hardware = hardwareJPAService.findByParameters(newHardware);

        assertNotNull(hardware);
    }

}