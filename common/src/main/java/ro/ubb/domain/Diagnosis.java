package ro.ubb.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Diagnosis extends BaseEntity implements Serializable {
    private Integer idMedic;
    private Integer idPatient;
    private String afflictionName;
    private String description;
    private LocalDate date;
}
