package joshuakemmerer.tasknet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

/**
 * Created by temp on 10/9/2015.
 */
public class ReminderRepo {

    private DBHelper dbHelper;

    public ReminderRepo(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    public int insert(Reminder Reminder)
    {
        // open connection
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Reminder.TIMEDIFFERENCE, Reminder.getTimeDifference());

        long ReminderId = db.insert(Reminder.TABLE_NAME, null, values);
        db.close();
        return (int)ReminderId;
    }

    public void delete(int ReminderId)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Reminder.TABLE_NAME, Reminder.ID + "= ?", new String[]{String.valueOf(ReminderId)});
        db.close(); // Closing database connection
    }

    public void update(Reminder Reminder)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Reminder.TIMEDIFFERENCE, Reminder.getTimeDifference());

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Reminder.TABLE_NAME, values, Reminder.getId() + "= ?", new String[]{String.valueOf(Reminder.getId())});
        db.close(); // Closing database connection
    }

    public ArrayList<Reminder> getReminders()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String select = "SELECT " +
                Reminder.ID + ", " +
                Reminder.TIMEDIFFERENCE +
                " FROM " + Reminder.TABLE_NAME;

        ArrayList<Reminder> Reminders = new ArrayList<>();

        Cursor cursor = db.rawQuery(select, null);
        if(cursor.moveToFirst())
        {
            int colId = cursor.getColumnIndex(Reminder.ID);
            int colTimeDifference = cursor.getColumnIndex(Reminder.TIMEDIFFERENCE);

            do
            {
                Reminders.add(new Reminder(
                        cursor.getInt(colId),
                        cursor.getInt(colTimeDifference)
                ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return Reminders;
    }
}
