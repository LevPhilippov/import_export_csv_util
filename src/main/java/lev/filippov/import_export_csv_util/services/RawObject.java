package lev.filippov.import_export_csv_util.services;

import lombok.Getter;

import java.util.Map;

@Getter
public class RawObject {
    String name;
    Integer quantity;
    Map<String, String> params;

    public RawObject(String name, Integer quantity, Map<String, String> params) {
        this.name = name;
        this.quantity = quantity;
        this.params = params;
    }
}
