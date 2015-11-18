package joshuakemmerer.tasknet;

import java.util.List;

/**
 * Created by temp on 10/9/2015.
 */
public class Reminder {

    private int id;
    private int timeDifference;

    public static final String TABLE_NAME = "Reminders";
    public static final String ID = "id";
    public static final String TIMEDIFFERENCE = "timeDifference";

    public Reminder(int id, int timeDifference)
    {
        setId(id);
        setTimeDifference(timeDifference);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimeDifference() {
        return timeDifference;
    }

    public void setTimeDifference(int timeDifference) {
        this.timeDifference = timeDifference;
    }


    public static List<Reminder> getReminderTypes()
    {
        return null;
    }
}
