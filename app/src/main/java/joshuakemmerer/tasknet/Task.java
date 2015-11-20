package joshuakemmerer.tasknet;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by temp on 10/9/2015.
 */
public abstract class Task {

    private int id;
    private int priority;
    private String title;
    private String description;
    private String date;
    private String time;
    private boolean isAllDay;
    private boolean isActive;
    private List<Reminder> reminders;

    public static final String TABLE_NAME = "Tasks";
    public static final String ID = "id";
    public static final String PRIORITY = "priority";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String DATE = "date";
    public static final String TIME = "time";
    public static final String ISALLDAY = "isAllDay";
    public static final String ISACTIVE = "isActive";


    public Task()
    {
        setId(0);
        setPriority(0);
        setTitle("");
        setDescription("");
        setDate("");
        setTime("");
        setIsAllDay(false);
        setReminders(new ArrayList<Reminder>());
    }

    public Task(int id, int priority, String title, String description, String date, String time, boolean isAllDay)
    {
        setId(id);
        setPriority(priority);
        setTitle(title);
        setDescription(description);
        setDate(date);
        setTime(time);
        setIsAllDay(isAllDay);
    }

    public Task(int id, int priority, String title, String description)
    {
        setId(id);
        setPriority(priority);
        setTitle(title);
        setDescription(description);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getPriority()
    {
        return priority;
    }

    public void setPriority(int priority)
    {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public List<Reminder> getReminders()
    {
        return reminders;
    }

    public void setReminders(List<Reminder> reminders)
    {
        this.reminders = reminders;
    }

    public boolean isAllDay()
    {
        return isAllDay;
    }

    public void setIsAllDay(boolean isAllDay)
    {
        this.isAllDay = isAllDay;
    }

    public abstract int saveToStorage(Context context);

    public boolean isActive()
    {
        return isActive;
    }

    public void setIsActive(boolean isActive)
    {
        this.isActive = isActive;
    }

}
