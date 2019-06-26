package lev.filippov.import_export_csv_util.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
//@DiscriminatorValue("MONITOR")
public class Monitor extends Hardware {

    @Column(name = "display_diagonal")
    private Integer diagonal;

    @Enumerated(EnumType.STRING)
    private UnitOfMeasure unitOfMeasure;

    @Override
    public String toString() {
        return super.toString() +
                ", diagonal=" + diagonal +
                ", unitOfMeasure=" ;
    }
}
