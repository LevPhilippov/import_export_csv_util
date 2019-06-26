package lev.filippov.import_export_csv_util.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
//@DiscriminatorValue("HDD")
public class Hdd extends Hardware {

    @Column(name = "volume")
    private Integer volume;

    @Enumerated(EnumType.STRING)
    private UnitOfMeasure unitOfMeasure;

    @Override
    public String toString() {
        return super.toString() +
                ", volume=" + volume +
                ", unitOfMeasure=" + unitOfMeasure;
    }
}
