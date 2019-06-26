package lev.filippov.import_export_csv_util.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
//@DiscriminatorValue("COMPUTER")
public class Computer extends Hardware {

    @Enumerated(EnumType.STRING)
    private FormFactor formFactor;

    @Override
    public String toString() {
        return super.toString()+ ", formFactor=" + formFactor;
    }
}
