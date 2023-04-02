package ro.ubb.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Medic extends User implements Serializable {
    private Specialty specialty;
    private LocalDate beginningDate;
    private LocalDate dateOfBirth;
    private Boolean isActive;

    public Medic(){}

    public Medic(Integer idEntity){
        super(idEntity);
    }

    public Medic(Integer idEntity, String name, String userName, String password, String email,
                 Specialty specialty, LocalDate beginningDate, LocalDate dateOfBirth, Boolean isActive) {
        super(idEntity, name, userName, password, email);
        this.specialty = specialty;
        this.beginningDate = beginningDate;
        this.dateOfBirth = dateOfBirth;
        this.isActive = isActive;
    }

    public Medic(String name, String userName, String password, String email,
          Specialty specialty, LocalDate beginningDate, LocalDate dateOfBirth, Boolean isActive){
        super(name, userName, password, email);
        this.specialty = specialty;
        this.beginningDate = beginningDate;
        this.dateOfBirth = dateOfBirth;
        this.isActive = isActive;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public LocalDate getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(LocalDate beginningDate) {
        this.beginningDate = beginningDate;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getIsActive() { return isActive; }

    public void setIsActive(Boolean active) { isActive = active; }



    @Override
    public String toString() {
        return "Medic{" +
                "idEntity=" + idEntity +
                ", specialty=" + specialty +
                ", beginningDate=" + beginningDate +
                ", dateOfBirth=" + dateOfBirth +
                ", name=`" + super.getName() + '`' +
                ", userName=`" + super.getUserName() + '`' +
                ", password=`" + super.getPassword() + '`' +
                ", email=`" + super.getEmail() + '`' +
                '}';
    }
}
