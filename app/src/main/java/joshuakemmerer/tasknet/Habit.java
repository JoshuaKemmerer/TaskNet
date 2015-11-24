package joshuakemmerer.tasknet;

import android.content.Context;

import java.util.List;

/**
 * Created by temp on 10/16/2015.
 */
public class Habit extends Task
{
    private String endDate;
    private String endTime;
    private int interval;// the time interval (in days) of how often the habit should be completed
    private boolean[] completions;// each element in the array is one completion of the specified time interval

    public static final String TABLE_NAME = "habits";
    public static final String BEGINDATE = "beginDate";
    public static final String BEGINTIME = "beginTime";
    public static final String ENDDATE = "endDate";
    public static final String ENDTIME = "endTime";

    public Habit(int id, int priority, String title, String description, String beginDate, String beginTime, String endDate, String endTime)
    {
        super(id, priority, title, description, beginDate, beginTime);
        setEndDate(endDate);
        setEndTime(endTime);
    }

    public Habit(int priority, String title, String description, String beginDate, String beginTime, String endDate, String endTime, boolean isActive)
    {
        setPriority(priority);
        setTitle(title);
        setDescription(description);
        setDate(beginDate);
        setTime(beginTime);
        setEndDate(endDate);
        setEndTime(endTime);
        setIsActive(isActive);
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public String getEndTime()
    {
        return endTime;
    }

    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }

    public boolean[] getCompletions()
    {
        return completions;
    }

    public void setCompletions(boolean[] completions)
    {
        this.completions = completions;
    }

    public int getInterval()
    {
        return interval;
    }

    public void setInterval(int interval)
    {
        this.interval = interval;
    }

    @Override
    public int saveToStorage(Context activityContext)
    {
        HabitRepo repo = new HabitRepo(activityContext);
        return repo.insert(this);
    }

    public static List<Habit> getFromStorage(Context activityContext)
    {
        HabitRepo repo = new HabitRepo(activityContext);
        return repo.getAllActive();
    }

    public static String getCanonicalName()
    {
        return Habit.class.getCanonicalName();
    }
}
