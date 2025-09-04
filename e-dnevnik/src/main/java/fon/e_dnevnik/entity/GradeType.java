package fon.e_dnevnik.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GradeType {
    KONTROLNI_ZADATAK("Kontrolni zadatak"),
    PISMENI_ZADATAK("Pismeni zadatak"),
    AKTIVNOST_NA_CASU("Aktivnost na času"),
    DOMACI_RAD("Domaći rad"),
    USMENO_ODGOVARANJE("Usmeno odgovaranje");

    private final String label;

    GradeType(String label) { this.label = label; }

    @JsonValue
    public String getLabel() {
        return label;
    }
}
