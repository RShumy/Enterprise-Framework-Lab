package ro.ubb.domain;

import java.io.Serializable;

public class Rating extends BaseEntity<Integer> implements Serializable {
    private Integer idUser;
    private Integer idMedic;
    private String reviewMessage;

    public Rating(int idEntity) {
        super(idEntity);
    }

    public Rating(){}

    public Rating(Integer idEntity, Integer idUser, Integer idMedic, String reviewMessage) {
        super(idEntity);
        this.idUser = idUser;
        this.idMedic = idMedic;
        this.reviewMessage = reviewMessage;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdMedic() {
        return idMedic;
    }

    public void setIdMedic(int idMedic) {
        this.idMedic = idMedic;
    }

    public String getReviewMessage() {
        return reviewMessage;
    }

    public void setReviewMessage(String comment) {
        this.reviewMessage = reviewMessage;
    }

    @Override
    public String toString() {
        return "Comment{" +
                " idEntity=`" + idEntity + '`' +
                ", idUser=`" + idUser + '`' +
                ", idMedic=`" + idMedic + '`' +
                ", reviewMessage=`" + reviewMessage + '`' +
                "}";
    }
}
