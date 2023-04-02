package ro.ubb.validators;

import ro.ubb.domain.BaseEntity;
import ro.ubb.domain.Location;
import ro.ubb.repository.RepositoryException;

public class LocationValidator implements Validator<Location>{
    @Override
    public void validate(Location orice) throws ValidatorException {

    }

    @Override
    public void validateId(Integer id, Iterable<? extends BaseEntity> allEntities) throws RepositoryException {

    }

    @Override
    public Location checkEmptyBeforeUpdate(Location updated, Location beforeUpdate) throws ValidatorException {
        return null;
    }
}
