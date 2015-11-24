package joshuakemmerer.tasknet;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by temp on 10/15/2015.
 */
public class Deadline extends Task
{
    private boolean isCompleted;
    private String tasknetVisibilityDate;// show deadline in tasknet before it's actually due
    private ArrayList<Task> subTasks;

    public static final String TABLE_NAME = "deadlineTasks";
    public static final String DUEDATE = "dueDate";
    public static final String DUETIME = "dueTime";


    public Deadline(int id, int priority, String title, String description, String dueDate, String dueTime)
    {
        super(id, priority, title, description, dueDate, dueTime);
        setIsCompleted(false);
        setSubTasks(new ArrayList<Task>());
    }

    public Deadline(int priority, String title, String description, String dueDate, String dueTime, boolean isActive)
    {
        setPriority(priority);
        setTitle(title);
        setDescription(description);
        setDate(dueDate);
        setTime(dueTime);
        setIsActive(isActive);
        setIsCompleted(false);
    }

    public boolean isCompleted()
    {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted)
    {
        this.isCompleted = isCompleted;
    }

    public ArrayList<Task> getSubTasks()
    {
        return subTasks;
    }

    public void setSubTasks(ArrayList<Task> subTasks)
    {
        this.subTasks = subTasks;
    }

    @Override
    public int saveToStorage(Context activityContext)
    {
        DeadlineTaskRepo repo = new DeadlineTaskRepo(activityContext);
        return repo.insert(this);
    }

    public static List<Deadline> getFromStorage(Context activityContext)
    {
        DeadlineTaskRepo repo = new DeadlineTaskRepo(activityContext);
        return repo.getAllActive();
    }

    public String getTasknetVisibilityDate()
    {
        return tasknetVisibilityDate;
    }

    public void setTasknetVisibilityDate(String tasknetVisibilityDate)
    {
        this.tasknetVisibilityDate = tasknetVisibilityDate;
    }

    public static String getCanonicalName()
    {
        return Deadline.class.getCanonicalName();
    }

}
