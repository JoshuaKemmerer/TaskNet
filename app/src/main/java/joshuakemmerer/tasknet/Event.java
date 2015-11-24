package joshuakemmerer.tasknet;

import android.content.Context;

import java.util.List;

/**
 * Created by temp on 11/20/2015.
 */
public class Event extends Task
{
    private String endDate;
    private String endTime;
    private boolean isAllDay;

    public static final String TABLE_NAME = "eventTasks";
    public static final String BEGINDATE = "beginDate";
    public static final String BEGINTIME = "beginTime";
    public static final String ENDDATE = "endDate";
    public static final String ENDTIME = "endTime";
    public static final String ISALLDAY = "isAllDay";

    public Event(int id, int priority, String title, String description, String beginDate, String beginTime, String endDate, String endTime, boolean isAllDay)
    {
        super(id, priority, title, description, beginDate, beginTime);
        setEndDate(endDate);
        setEndTime(endTime);
        setIsAllDay(isAllDay);
    }

    public Event(int priority, String title, String description, String beginDate, String beginTime, String endDate, String endTime, boolean isActive)
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

    public boolean isAllDay()
    {
        return isAllDay;
    }

    public void setIsAllDay(boolean isAllDay)
    {
        this.isAllDay = isAllDay;
    }

    @Override
    public int saveToStorage(Context context)
    {
        EventTaskRepo repo = new EventTaskRepo(context);
        return repo.insert(this);
    }

    public static List<Event> getFromStorage(Context activityContext)
    {
        EventTaskRepo repo = new EventTaskRepo(activityContext);
        return repo.getAllActive();
    }

    public static String getCanonicalName()
    {
        return Event.class.getCanonicalName();
    }

}
