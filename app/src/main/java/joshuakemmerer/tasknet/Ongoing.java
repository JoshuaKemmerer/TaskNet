package joshuakemmerer.tasknet;

import android.content.Context;

import java.util.List;

/**
 * Created by temp on 10/16/2015.
 */
public class Ongoing extends Task
{
    private String endDate;
    private String endTime;

    public static final String TABLE_NAME = "ongoingTasks";
    public static final String BEGINDATE = "beginDate";
    public static final String BEGINTIME = "beginTime";
    public static final String ENDDATE = "endDate";
    public static final String ENDTIME = "endTime";

    public Ongoing(int id, int priority, String title, String description, String beginDate, String beginTime, String endDate, String endTime, boolean isAllDay)
    {
        super(id, priority, title, description, beginDate, beginTime, isAllDay);
        setEndDate(endDate);
        setEndTime(endTime);
    }

    public Ongoing(int priority, String title, String description, String beginDate, String beginTime, String endDate, String endTime, boolean isActive)
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
    public int saveToStorage(Context activityContext)
    {
        OngoingTaskRepo repo = new OngoingTaskRepo(activityContext);
        return repo.insert(this);
    }

    public static List<Ongoing> getFromStorage(Context activityContext)
    {
        OngoingTaskRepo repo = new OngoingTaskRepo(activityContext);
        return repo.getAllActive();
    }

    public static String getCanonicalName()
    {
        return Ongoing.class.getCanonicalName();
    }
}
