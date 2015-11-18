package joshuakemmerer.tasknet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

        values.put(Task.PRIORITY, task.getPriority());
        values.put(Task.TITLE, task.getTitle());
        values.put(Task.DESCRIPTION, task.getDescription());
        values.put(Deadline.DUEDATE, task.getDate());
        values.put(Deadline.DUETIME, task.getTime());
        values.put(Deadline.ISALLDAY, task.isAllDay());
        values.put(Deadline.ISACTIVE, task.isActive());

        long taskId = db.insert(Deadline.TABLE_NAME, null, values);
        db.close();
        return (int)taskId;
    }

    public List<Deadline> getAllActive()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String select = "SELECT " +
                Task.ID + ", " +
                Task.PRIORITY + ", " +
                Task.TITLE + ", " +
                Task.DESCRIPTION + ", " +
                Deadline.ISALLDAY + ", " +
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
            int colIsAllDay = cursor.getColumnIndex(Deadline.ISALLDAY);
            int colDueDate = cursor.getColumnIndex(Deadline.DUEDATE);
            int colDueTime = cursor.getColumnIndex(Deadline.DUETIME);

            do
            {
                tasks.add(new Deadline(cursor.getInt(colId),
                        cursor.getInt(colPriority),
                        cursor.getString(colTitle),
                        cursor.getString(colDescrip),
                        cursor.getString(colDueDate),
                        cursor.getString(colDueTime),
                        (cursor.getInt(colIsAllDay) == 1)
                ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return tasks;
    }
}
