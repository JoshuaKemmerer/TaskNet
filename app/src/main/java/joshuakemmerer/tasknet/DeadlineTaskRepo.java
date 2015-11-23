package joshuakemmerer.tasknet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by temp on 10/20/2015.
 */
public class DeadlineTaskRepo
{private DBHelper dbHelper;

    public DeadlineTaskRepo(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    public void insertFakes(Task task)
    {

    }

    public int insert(Deadline task)
    {
        // open connection
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Deadline.PRIORITY, task.getPriority());
        values.put(Deadline.TITLE, task.getTitle());
        values.put(Deadline.DESCRIPTION, task.getDescription());
        values.put(Deadline.DUEDATE, task.getDate());
        values.put(Deadline.DUETIME, task.getTime());
        values.put(Deadline.ISACTIVE, task.isActive());

        long taskId = db.insert(Deadline.TABLE_NAME, null, values);
        db.close();
        Log.v("somesing..............", Long.toString(taskId));
        return (int)taskId;
    }

    public List<Deadline> getAllActive()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String select = "SELECT " +
                Deadline.ID + ", " +
                Deadline.PRIORITY + ", " +
                Deadline.TITLE + ", " +
                Deadline.DESCRIPTION + ", " +
                Deadline.DUEDATE + ", " +
                Deadline.DUETIME +
                " FROM " + Deadline.TABLE_NAME + " WHERE " + Deadline.ISACTIVE + " = 1";

        List<Deadline> tasks = new ArrayList<>();

        Cursor cursor = db.rawQuery(select, null);
        if(cursor.moveToFirst())
        {
            int colId = cursor.getColumnIndex(Task.ID);
            int colPriority = cursor.getColumnIndex(Task.PRIORITY);
            int colTitle = cursor.getColumnIndex(Task.TITLE);
            int colDescrip = cursor.getColumnIndex(Task.DESCRIPTION);
            int colDueDate = cursor.getColumnIndex(Deadline.DUEDATE);
            int colDueTime = cursor.getColumnIndex(Deadline.DUETIME);

            do
            {
                tasks.add(new Deadline(cursor.getInt(colId),
                        cursor.getInt(colPriority),
                        cursor.getString(colTitle),
                        cursor.getString(colDescrip),
                        cursor.getString(colDueDate),
                        cursor.getString(colDueTime)
                ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return tasks;
    }
}
