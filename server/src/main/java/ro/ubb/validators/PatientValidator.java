package ro.ubb.validators;

import ro.ubb.domain.BaseEntity;
import ro.ubb.domain.Patient;
import ro.ubb.repository.RepositoryException;

public class PatientValidator implements Validator<Patient>{

    @Override
    public void validate(Patient orice) throws ValidatorException {

    }

    @Override
    public Patient checkEmptyBeforeUpdate(Patient updated, Patient beforeUpdate) throws ValidatorException {
        return null;
    }

    @Override
    public void validateId(Integer id, Iterable allEntities) throws RepositoryException {

    }



}
