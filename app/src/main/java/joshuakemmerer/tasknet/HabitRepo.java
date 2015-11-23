package joshuakemmerer.tasknet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by temp on 10/16/2015.
 */
public class HabitRepo
{
    private DBHelper dbHelper;

    public HabitRepo(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    public void insertFakes(Task task)
    {

    }

    public int insert(Habit task)
    {
        // open connection
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Task.PRIORITY, task.getPriority());
        values.put(Task.TITLE, task.getTitle());
        values.put(Task.DESCRIPTION, task.getDescription());
        values.put(Habit.BEGINDATE, task.getDate());
        values.put(Habit.BEGINTIME, task.getTime());
        values.put(Habit.ENDDATE, task.getEndDate());
        values.put(Habit.ENDTIME, task.getEndTime());
        values.put(Habit.ISACTIVE, task.isActive());

        long taskId = db.insert(Habit.TABLE_NAME, null, values);
        db.close();
        Log.v("ongoing insert value: ", Long.toString(taskId));
        return (int)taskId;
    }

    public List<Habit> getAllActive()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String select = "SELECT " +
                Task.ID + ", " +
                Task.PRIORITY + ", " +
                Task.TITLE + ", " +
                Task.DESCRIPTION + ", " +
                Habit.BEGINDATE + ", " +
                Habit.BEGINTIME + ", " +
                Habit.ENDDATE + ", " +
                Habit.ENDTIME +
                " FROM " + Habit.TABLE_NAME + " WHERE " + Habit.ISACTIVE + " = 1";

        List<Habit> tasks = new ArrayList<>();

        Cursor cursor = db.rawQuery(select, null);
        if(cursor.moveToFirst())
        {
            int colId = cursor.getColumnIndex(Task.ID);
            int colPriority = cursor.getColumnIndex(Task.PRIORITY);
            int colTitle = cursor.getColumnIndex(Task.TITLE);
            int colDescrip = cursor.getColumnIndex(Task.DESCRIPTION);
            int colBeginDate = cursor.getColumnIndex(Habit.BEGINDATE);
            int colBeginTime = cursor.getColumnIndex(Habit.BEGINTIME);
            int colEndDate = cursor.getColumnIndex(Habit.ENDDATE);
            int colEndTime = cursor.getColumnIndex(Habit.ENDTIME);

            do
            {
                tasks.add(new Habit(cursor.getInt(colId),
                        cursor.getInt(colPriority),
                        cursor.getString(colTitle),
                        cursor.getString(colDescrip),
                        cursor.getString(colBeginDate),
                        cursor.getString(colBeginTime),
                        cursor.getString(colEndDate),
                        cursor.getString(colEndTime)
                ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return tasks;
    }
}
