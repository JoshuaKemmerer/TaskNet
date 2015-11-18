package joshuakemmerer.tasknet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by temp on 10/9/2015.
 */
public class TaskRepo {

    private DBHelper dbHelper;

    public TaskRepo(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    public void insertFakes(Task task)
    {

    }

    public int insert(Task task)
    {
        // open connection
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Task.TITLE, task.getTitle());
        values.put(Task.DESCRIPTION, task.getDescription());
        values.put(Task.DATE, task.getDate());

        long taskId = db.insert(Task.TABLE_NAME, null, values);
        db.close();
        return (int)taskId;
    }

    public void delete(int taskId)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Task.TABLE_NAME, Task.ID + "= ?", new String[]{String.valueOf(taskId)});
        db.close(); // Closing database connection
    }

    public void update(Task task)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Task.TITLE, task.getTitle());
        values.put(Task.DESCRIPTION, task.getDescription());
        values.put(Task.DATE, task.getDate());

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Task.TABLE_NAME, values, task.getId() + "= ?", new String[]{String.valueOf(task.getId())});
        db.close(); // Closing database connection
    }

    /*public ArrayList<Task> getTasks()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String select = "SELECT " +
                Task.ID + ", " +
                Task.TITLE + "," +
                Task.DESCRIPTION + "," +
                Task.DATE +
                " FROM " + Task.TABLE_NAME;

        ArrayList<Task> tasks = new ArrayList<>();

        Cursor cursor = db.rawQuery(select, null);
        if(cursor.moveToFirst())
        {
            int colId = cursor.getColumnIndex(Task.ID);
            int colTitle = cursor.getColumnIndex(Task.TITLE);
            int colDescrip = cursor.getColumnIndex(Task.DESCRIPTION);
            int colDate = cursor.getColumnIndex(Task.DATE);

            do
            {
                tasks.add(new Task(
                        cursor.getInt(colId),
                        cursor.getString(colTitle),
                        cursor.getString(colDescrip),
                        cursor.getInt(colDate)
                ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return tasks;
    }*/
}
