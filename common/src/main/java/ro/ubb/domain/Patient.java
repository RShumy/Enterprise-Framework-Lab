package ro.ubb.domain;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Patient extends User implements Serializable {
    private LocalDate dateOfBirth;
    private Boolean hired;
    private Integer cardNumber;

    public Patient(){}

    public Patient(Integer idEntity){
        super(idEntity);
    }

    public Patient(Integer idEntity, String name, String userName, String password, String email,
                   LocalDate dateOfBirth, Boolean hired, Integer cardNumber) {
        super(idEntity, name, userName, password, email);
        this.dateOfBirth = dateOfBirth;
        this.hired = hired;
        this.cardNumber = cardNumber;
    }

    public Patient(String name, String userName, String password, String email,
                   LocalDate dateOfBirth, Boolean hired, Integer cardNumber) {
        super(name, userName, password, email);
        this.dateOfBirth = dateOfBirth;
        this.hired = hired;
        this.cardNumber = cardNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getHired() {
        return hired;
    }

    public void setHired(Boolean hired) {
        this.hired = hired;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "dateOfBirth=" + dateOfBirth +
                ", hired='" + hired + '\'' +
                ", cardNumber=" + cardNumber +
                ", name=`" + super.getName() + '`' +
                ", userName=`" + super.getUserName() + '`' +
                ", password=`" + super.getPassword() + '`' +
                ", email=`" + super.getEmail() + '`' +
                '}';
    }
}
