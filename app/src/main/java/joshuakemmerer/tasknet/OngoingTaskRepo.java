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
public class OngoingTaskRepo
{
    private DBHelper dbHelper;

    public OngoingTaskRepo(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    public void insertFakes(Task task)
    {

    }

    public int insert(Ongoing task)
    {
        // open connection
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Task.PRIORITY, task.getPriority());
        values.put(Task.TITLE, task.getTitle());
        values.put(Task.DESCRIPTION, task.getDescription());
        values.put(Ongoing.BEGINDATE, task.getDate());
        values.put(Ongoing.BEGINTIME, task.getTime());
        values.put(Ongoing.ENDDATE, task.getEndDate());
        values.put(Ongoing.ENDTIME, task.getEndTime());
        values.put(Ongoing.ISALLDAY, task.isAllDay());
        values.put(Ongoing.ISACTIVE, task.isActive());

        long taskId = db.insert(Ongoing.TABLE_NAME, null, values);
        db.close();
        Log.v("ongoing insert value: ", Long.toString(taskId));
        return (int)taskId;
    }

    public List<Ongoing> getAllActive()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String select = "SELECT " +
                Task.ID + ", " +
                Task.PRIORITY + ", " +
                Task.TITLE + ", " +
                Task.DESCRIPTION + ", " +
                Ongoing.ISALLDAY + ", " +
                Ongoing.BEGINDATE + ", " +
                Ongoing.BEGINTIME + ", " +
                Ongoing.ENDDATE + ", " +
                Ongoing.ENDTIME +
                " FROM " + Ongoing.TABLE_NAME + " WHERE " + Ongoing.ISACTIVE + " = 1";

        List<Ongoing> tasks = new ArrayList<>();

        Cursor cursor = db.rawQuery(select, null);
        if(cursor.moveToFirst())
        {
            int colId = cursor.getColumnIndex(Task.ID);
            int colPriority = cursor.getColumnIndex(Task.PRIORITY);
            int colTitle = cursor.getColumnIndex(Task.TITLE);
            int colDescrip = cursor.getColumnIndex(Task.DESCRIPTION);
            int colIsAllDay = cursor.getColumnIndex(Ongoing.ISALLDAY);
            int colBeginDate = cursor.getColumnIndex(Ongoing.BEGINDATE);
            int colBeginTime = cursor.getColumnIndex(Ongoing.BEGINTIME);
            int colEndDate = cursor.getColumnIndex(Ongoing.ENDDATE);
            int colEndTime = cursor.getColumnIndex(Ongoing.ENDTIME);

            do
            {
                tasks.add(new Ongoing(cursor.getInt(colId),
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
