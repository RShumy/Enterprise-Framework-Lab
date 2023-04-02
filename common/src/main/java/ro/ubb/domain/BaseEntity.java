package ro.ubb.domain;

import java.io.Serializable;

public abstract class BaseEntity<ID> implements Serializable{
    public ID idEntity;

    public BaseEntity(ID idEntity){ this.idEntity = (ID) idEntity; }

    public BaseEntity(){}

    public void setIdEntity(ID idEntity) { this.idEntity = idEntity; }


    public ID getIdEntity() { return idEntity; }

    @Override
    public String toString() {
        return "Entity{" +
                "idEntity=" + idEntity +
                '}';
    }
}
