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
public class EventTaskRepo
{
    private DBHelper dbHelper;

    public EventTaskRepo(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    public void insertFakes(Task task)
    {

    }

    public int insert(Event task)
    {
        // open connection
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Event.PRIORITY, task.getPriority());
        values.put(Event.TITLE, task.getTitle());
        values.put(Event.DESCRIPTION, task.getDescription());
        values.put(Event.BEGINDATE, task.getDate());
        values.put(Event.BEGINTIME, task.getTime());
        values.put(Event.ENDDATE, task.getEndDate());
        values.put(Event.ENDTIME, task.getEndTime());
        values.put(Event.ISALLDAY, task.isAllDay());
        values.put(Event.ISACTIVE, task.isActive());

        long taskId = db.insert(Event.TABLE_NAME, null, values);
        db.close();
        Log.v("Event insert value: ", Long.toString(taskId));
        return (int)taskId;
    }

    public List<Event> getAllActive()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String select = "SELECT " +
                Event.ID + ", " +
                Event.PRIORITY + ", " +
                Event.TITLE + ", " +
                Event.DESCRIPTION + ", " +
                Event.ISALLDAY + ", " +
                Event.BEGINDATE + ", " +
                Event.BEGINTIME + ", " +
                Event.ENDDATE + ", " +
                Event.ENDTIME +
                " FROM " + Event.TABLE_NAME + " WHERE " + Event.ISACTIVE + " = 1";

        List<Event> tasks = new ArrayList<>();

        Cursor cursor = db.rawQuery(select, null);
        if(cursor.moveToFirst())
        {
            int colId = cursor.getColumnIndex(Event.ID);
            int colPriority = cursor.getColumnIndex(Event.PRIORITY);
            int colTitle = cursor.getColumnIndex(Event.TITLE);
            int colDescrip = cursor.getColumnIndex(Event.DESCRIPTION);
            int colIsAllDay = cursor.getColumnIndex(Event.ISALLDAY);
            int colBeginDate = cursor.getColumnIndex(Event.BEGINDATE);
            int colBeginTime = cursor.getColumnIndex(Event.BEGINTIME);
            int colEndDate = cursor.getColumnIndex(Event.ENDDATE);
            int colEndTime = cursor.getColumnIndex(Event.ENDTIME);

            do
            {
                tasks.add(new Event(cursor.getInt(colId),
                        cursor.getInt(colPriority),
                        cursor.getString(colTitle),
                        cursor.getString(colDescrip),
                        cursor.getString(colBeginDate),
                        cursor.getString(colBeginTime),
                        cursor.getString(colEndDate),
                        cursor.getString(colEndTime),
                        (cursor.getInt(colIsAllDay) == 1)
                ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return tasks;
    }
}
