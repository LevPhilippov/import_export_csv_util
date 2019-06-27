package lev.filippov.import_export_csv_util.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "hardwares")
public class Hardware implements Comparable<Hardware> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String name;

    @Column(name = "serial_number")
    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Integer quantity;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "hardware")
    Set<Parameter> parameters = new HashSet<>();

    @Override
    public String toString() {
        return "Hardware{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", manufacturer=" + manufacturer +
                ", price=" + price +
                ", quantity=" + quantity +
                ", parameters=" + parameters +
                '}';
    }

    @Override
    public int compareTo(Hardware o) {
        return this.getName().compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hardware hardware = (Hardware) o;
        return Objects.equals(name, hardware.name) &&
                Objects.equals(serialNumber, hardware.serialNumber) &&
                Objects.equals(manufacturer, hardware.manufacturer) &&
                Objects.equals(price, hardware.price) &&
                Objects.equals(parameters, hardware.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, serialNumber, manufacturer, price, parameters);
    }
}
