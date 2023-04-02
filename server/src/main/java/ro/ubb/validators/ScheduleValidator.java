package ro.ubb.validators;

import ro.ubb.domain.BaseEntity;
import ro.ubb.domain.Schedule;
import ro.ubb.repository.RepositoryException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScheduleValidator implements Validator<Schedule> {

    private Pattern PATTERN = Pattern.compile("[^A-Za-z0-9-\\.!?/\\\\'*&+@=%$<>{}\\[]#:`~\"]");

    @Override
    public void validate(Schedule schedule) throws ValidatorException {
//        if (schedule.getScheduleName().isEmpty()) throw new ValidatorException("Schedule Name cannot be NULL(Empty)");
//        Matcher match = PATTERN.matcher(schedule.getScheduleName());
//        if (match.find())
//            throw new ValidatorException("Contains Special characters that are not allowed");
//        if (schedule.getScheduleName().length() > 20)
//            throw new ValidatorException("Schedule name cannot exceed 20 characters");
//        if (schedule.getScheduleDate().isBefore(LocalDate.now()) || (schedule.getScheduleDate().isEqual(LocalDate.now()) && schedule.getScheduleTime().isBefore(LocalTime.now())) )
//            throw new ValidatorException("Cannot create an Schedule with a past date or time");
//        if (schedule.getSchedulePrice() < 0)
//            throw new ValidatorException("Price cannot be lower than 0");
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
    public Schedule checkEmptyBeforeUpdate(Schedule updated, Schedule beforeUpdate) throws ValidatorException {
//        String scheduleName = updated.getScheduleName();
//        Float schedulePrice = updated.getSchedulePrice();
//        if (scheduleName.equals("0") || scheduleName.isEmpty() || scheduleName.matches("\n"))
//            updated.setScheduleName(beforeUpdate.getScheduleName());
//        if (schedulePrice == 0 || schedulePrice == null)
//            updated.setSchedulePrice(beforeUpdate.getSchedulePrice());
        return updated;
    }
}
