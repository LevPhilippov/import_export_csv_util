package lev.filippov.import_export_csv_util.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "manufacturer_name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manufacturer")
    private Set<Hardware> hardwares = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Hardware> getHardwares() {
        return hardwares;
    }

    public void setHardwares(Set<Hardware> hardwares) {
        this.hardwares = hardwares;
    }

    @Override
    public String toString() {
        return name;
    }
}