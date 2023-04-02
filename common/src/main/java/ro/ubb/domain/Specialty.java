package ro.ubb.domain;

public enum Specialty {
    Dermatology(1),
    Cardiology(2),
    Family(3),
    Internal(4),
    Neurology(5),
    Gynecology(6),
    Ophthalmology(7),
    Pathology(8),
    Pediatrics(9),
    Recuperation(10),
    Psychiatry(11),
    Radiology(12),
    Surgery(13),
    Urology(14);

    public Integer number;

    Specialty(Integer nr){
        number=nr;
    }

    public static Specialty valueOfLabel(Integer number) {
        for (Specialty e : values()) {
            if (e.number == number) {
                return e;
            }
        }
        return null;
    }


}
