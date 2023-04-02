package ro.ubb.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule extends BaseEntity<Integer> implements Serializable {
    private Integer idPatient;
    private Integer idMedic;
    private Integer idLocation;
    private Integer idInvestigation;
    private LocalDate scheduleDate;
    private LocalTime scheduleTime;


    public Schedule(int idEntity) {
        super(idEntity);
    }

    public Schedule(){}

    public Schedule(Integer idEntity, LocalDate scheduleDate, LocalTime scheduleTime) {
        super(idEntity);
        this.scheduleDate = scheduleDate;
        this.scheduleTime = scheduleTime;

    }

    public Schedule(Integer idEntity, Integer idPatient, Integer idMedic, Integer idLocation, Integer idInvestigation, LocalDate scheduleDate, LocalTime scheduleTime) {
        super(idEntity);
        this.idPatient = idPatient;
        this.idMedic = idMedic;
        this.idLocation = idLocation;
        this.idInvestigation = idInvestigation;
        this.scheduleDate = scheduleDate;
        this.scheduleTime = scheduleTime;
    }

    public Schedule(Integer idPatient, Integer idMedic, Integer idLocation, Integer idInvestigation, LocalDate scheduleDate, LocalTime scheduleTime) {
        this.idPatient = idPatient;
        this.idMedic = idMedic;
        this.idLocation = idLocation;
        this.idInvestigation = idInvestigation;
        this.scheduleDate = scheduleDate;
        this.scheduleTime = scheduleTime;
    }

    public Integer getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Integer idPatient) {
        this.idPatient = idPatient;
    }

    public Integer getIdMedic() {
        return idMedic;
    }

    public void setIdMedic(Integer idMedic) {
        this.idMedic = idMedic;
    }

    public Integer getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Integer idLocation) {
        this.idLocation = idLocation;
    }

    public Integer getIdInvestigation() {
        return idInvestigation;
    }

    public void setIdInvestigation(Integer idInvestigation) {
        this.idInvestigation = idInvestigation;
    }

    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public LocalTime getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(LocalTime scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "idEntity=" + idEntity +
                ", idPatient=" + idPatient +
                ", idMedic=" + idMedic +
                ", idLocation=" + idLocation +
                ", idInvestigation=" + idInvestigation +
                ", scheduleDate=" + scheduleDate +
                ", scheduleTime=" + scheduleTime +
                '}';
    }
}
