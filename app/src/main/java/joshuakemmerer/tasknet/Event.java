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

    public static final String TABLE_NAME = "eventTasks";
    public static final String BEGINDATE = "beginDate";
    public static final String BEGINTIME = "beginTime";
    public static final String ENDDATE = "endDate";
    public static final String ENDTIME = "endTime";

    public Event(int id, int priority, String title, String description, String beginDate, String beginTime, String endDate, String endTime, boolean isAllDay)
    {
        super(id, priority, title, description, beginDate, beginTime, isAllDay);
        setEndDate(endDate);
        setEndTime(endTime);
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

    @Override
    public int saveToStorage(Context context)
    {
        // need to save TO STORAGE (CREATE TABLE AND ALL THAT SHIT)
        return 0;
    }
}
