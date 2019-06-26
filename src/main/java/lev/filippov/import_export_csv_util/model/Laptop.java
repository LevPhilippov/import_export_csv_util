package lev.filippov.import_export_csv_util.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
//@DiscriminatorValue("LAPTOP")
public class Laptop extends Hardware {

    @Column(name = "display_size")
    private Integer displaySize;

    @Enumerated(EnumType.STRING)
    private UnitOfMeasure unitOfMeasure;

    @Override
    public String toString() {
        return super.toString() +
                ", displaySize=" + displaySize +
                ", unitOfMeasure=" + unitOfMeasure;
    }
}
