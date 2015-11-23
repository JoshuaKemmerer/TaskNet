package joshuakemmerer.tasknet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 6;

    // Database Name
    private static final String DATABASE_NAME = "TaskNet.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here

        String CREATE_TABLE_EVENTS = "CREATE TABLE " + Event.TABLE_NAME  + "("
                + Event.ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Event.PRIORITY + " INTEGER, "
                + Event.TITLE + " TEXT, "
                + Event.DESCRIPTION + " TEXT, "
                + Event.BEGINDATE + " TEXT, "
                + Event.BEGINTIME + " TEXT, "
                + Event.ENDDATE + " TEXT, "
                + Event.ENDTIME + " TEXT, "
                + Event.ISACTIVE + " INTEGER, "
                + Event.ISALLDAY + " INTEGER)";

        String CREATE_TABLE_DEADLINETASKS= "CREATE TABLE " + Deadline.TABLE_NAME  + "("
                + Deadline.ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Deadline.PRIORITY + " INTEGER, "
                + Deadline.TITLE + " TEXT, "
                + Deadline.DESCRIPTION + " TEXT, "
                + Deadline.DUEDATE + " TEXT, "
                + Deadline.DUETIME + " TEXT, "
                + Deadline.ISACTIVE + " INTEGER)";

        String CREATE_TABLE_HABITS = "CREATE TABLE " + Habit.TABLE_NAME  + "("
                + Task.ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Task.PRIORITY + " INTEGER, "
                + Task.TITLE + " TEXT, "
                + Task.DESCRIPTION + " TEXT, "
                + Habit.BEGINDATE + " TEXT, "
                + Habit.BEGINTIME + " TEXT, "
                + Habit.ENDDATE + " TEXT, "
                + Habit.ENDTIME + " TEXT, "
                + Habit.ISACTIVE + " INTEGER)";

        String CREATE_TABLE_REMINDERS = "CREATE TABLE " + Reminder.TABLE_NAME + "("
                + Reminder.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Reminder.TIMEDIFFERENCE + " datetime)";

        db.execSQL(CREATE_TABLE_EVENTS);
        db.execSQL(CREATE_TABLE_DEADLINETASKS);
        db.execSQL(CREATE_TABLE_HABITS);
        db.execSQL(CREATE_TABLE_REMINDERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Habit.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Deadline.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Reminder.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Event.TABLE_NAME);

        // Create tables again
        onCreate(db);

    }

}
