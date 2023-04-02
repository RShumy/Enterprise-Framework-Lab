package ro.ubb.validators;

import ro.ubb.domain.BaseEntity;
import ro.ubb.domain.Rating;
import ro.ubb.repository.RepositoryException;


public class RatingValidator implements Validator<Rating> {

    @Override
    public void validate(Rating review) throws ValidatorException {
        if (review.getReviewMessage().length() > 200)
            throw new ValidatorException("Length exceeds 200 characters");
    }

    @Override
    public void validateId (Integer entityId, Iterable<? extends BaseEntity> allEntities) throws RepositoryException {
        boolean contains = false;
        String entityType = "";
        for (BaseEntity entity: allEntities)
        {
            entityType = entity.getClass().getTypeName();
            if (entity.getIdEntity()== entityId) {
                contains = true;
                break;
            }
        }
        if (!contains)
            throw new RepositoryException("Entity ID does not exist in " + entityType);
    }

    @Override
    public Rating checkEmptyBeforeUpdate(Rating review, Rating beforeUpdate) throws ValidatorException {
        return null;
    }

}
