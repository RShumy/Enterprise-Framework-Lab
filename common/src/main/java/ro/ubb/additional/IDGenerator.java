package ro.ubb.additional;

import ro.ubb.domain.BaseEntity;
import ro.ubb.domain.Rating;
import ro.ubb.domain.Schedule;
import ro.ubb.domain.User;

public class IDGenerator<Type extends BaseEntity> {
    public static final String USER = "user";
    public static final String SCHEDULE = "schedule";
    public static final String REVIEW = "review";

    private String entityClassType;

    private static Integer userMaxId = 1;
    private static Integer scheduleMaxId = 1;
    private static Integer reviewMaxId = 1;



    public static Integer generateUserId(Iterable<User> idSet) {
        if (!idSet.iterator().hasNext())
            return userMaxId;
        else {
            idSet.forEach(user -> {
                if(user.getIdEntity() > userMaxId)
                    userMaxId = user.getIdEntity();
            });
            return userMaxId+1;
        }
    }

    public static Integer generateScheduleId(Iterable<Schedule> idSet) {
        if (!idSet.iterator().hasNext())
            return scheduleMaxId;
        else {
            idSet.forEach(schedule -> {
                if(schedule.getIdEntity() > scheduleMaxId)
                    scheduleMaxId = schedule.getIdEntity();
            });
            return scheduleMaxId+1;
        }
    }

    public static Integer generateReviewId(Iterable<Rating> idSet) {
        if (!idSet.iterator().hasNext())
            return reviewMaxId;
        else {
            idSet.forEach(review -> {
                if(review.getIdEntity() > reviewMaxId)
                    reviewMaxId = review.getIdEntity();
            });
            return reviewMaxId+1;
        }
    }
}
