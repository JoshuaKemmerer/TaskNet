package joshuakemmerer.tasknet;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CreateTaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener
{
    final String FROMDATEPICKERTAG = "fromDatePicker";// also used for DUE BY DATE
    final String FROMTIMEPICKERTAG = "fromTimePicker";// also used for DUE BY TIME
    final String TODATEPICKERTAG = "toDatePicker";
    final String TOTIMEPICKERTAG = "toTimePicker";
    public static final String DATEKEY = "date";
    public static final String TIMEKEY = "time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        Spinner spinner = (Spinner) findViewById(R.id.spnTaskTypes);
        spinner.setOnItemSelectedListener(this);

        loadInitialValues();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.create_task_actions, menu);
        return true;
    }

    // action bar button clicks will be handled here
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_create_task:
                createTask();
                break;
            default:
                finish();// only item that could have been tapped is the back button
                break;
        }

        return true;
    }

    // when Date fields are pressed, show the date picker dialog with the time that is currently
    // in the date field being pressed
    public void showDatePickerDialog(View v)
    {
        DialogFragment newFragment = new DatePickerFragment();
        Bundle args = new Bundle();

        // allow for FROM DATE and DUE BY DATE to be handled in sync (switching task type = good)
        if(v.getId() == R.id.txtFromDate || v.getId() == R.id.txtDueByDate)
        {
            // save the date stored in the view that was pressed
            args.putSerializable(DATEKEY, getViewDate(v.getId()));
            newFragment.setArguments(args);
            newFragment.show(getFragmentManager(), FROMDATEPICKERTAG);
        }
        else if (v.getId() == R.id.txtToDate)
        {
            args.putSerializable(DATEKEY, getViewDate(v.getId()));
            newFragment.setArguments(args);
            newFragment.show(getFragmentManager(), TODATEPICKERTAG);
        }
    }

    private Calendar getViewDate(int viewId)
    {
        TextView tv = (TextView)findViewById(viewId);
        Calendar cal = Calendar.getInstance();
        try
        {
            cal.setTime(DateTimeFormatter.dateFormat.parse(tv.getText().toString()));
            return cal;
        }
        catch(java.text.ParseException ex)
        {
            return cal;
        }
    }

    public void showTimePickerDialog(View v)
    {
        DialogFragment newFragment = new TimePickerFragment();
        Bundle args = new Bundle();

        // allow for FROM TIME and DUE BY TIME to be handled in sync (switching task type = good)
        if(v.getId() == R.id.txtFromTime || v.getId() == R.id.txtDueByTime)
        {
            // save the TIME stored in the view that was pressed
            args.putSerializable(TIMEKEY, getViewTime(v.getId()));
            newFragment.setArguments(args);
            newFragment.show(getFragmentManager(), FROMTIMEPICKERTAG);
        }
        else if(v.getId() == R.id.txtToTime)
        {
            args.putSerializable(TIMEKEY, getViewTime(v.getId()));
            newFragment.setArguments(args);
            newFragment.show(getFragmentManager(), TOTIMEPICKERTAG);
        }
    }

    private Calendar getViewTime(int viewId)
    {
        TextView tv = (TextView)findViewById(viewId);
        Calendar cal = Calendar.getInstance();

        try
        {
            cal.setTime(DateTimeFormatter.timeFormat.parse(tv.getText().toString()));
            return cal;
        }
        catch(ParseException ex)
        {
            return cal;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear,
                          int dayOfMonth)
    {
        FragmentManager fragmentManager = getFragmentManager();
        Calendar cal = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        String date = DateTimeFormatter.formatDate(cal.getTime());

        if(fragmentManager.findFragmentByTag(FROMDATEPICKERTAG) != null)
        {// remember when FROMDATEPICKERTAG was set in showDatePickerDialog()?
            // now we need to set both the txtFromDate and txtDueByDate views
            ((TextView) findViewById(R.id.txtFromDate)).setText(date);
            ((TextView)findViewById(R.id.txtDueByDate)).setText(date);
        }
        else
            ((TextView) findViewById(R.id.txtToDate)).setText(date);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
    {
        FragmentManager fragmentManager = getFragmentManager();
        String time = DateTimeFormatter.format24HrTime(hourOfDay, minute);

        if(fragmentManager.findFragmentByTag(FROMTIMEPICKERTAG) != null)
        {// remember when FROMTIMEPICKERTAG was set in showDatePickerDialog()?
            // now we need to set both the txtFromDate and txtDueByDate views
            ((TextView) findViewById(R.id.txtFromTime)).setText(time);
            ((TextView) findViewById(R.id.txtDueByTime)).setText(time);
        }
        else
            ((TextView)findViewById(R.id.txtToTime)).setText(time);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
//        String[] taskTypes = getResources().getStringArray(R.array.spinnerItems_taskTypes);
        if(isDeadlineTaskString((int) id))
        {// code for Deadline task
            showOngoingEventControls(false, false);
            showDeadlineControls(true);
        }
        else if(isOngoingTaskString((int)id))
        {// code for Habit task
            showOngoingEventControls(true, true);
            showDeadlineControls(false);
        }
        else if(isEventTaskString((int)id))
        {// code for Event task
            showOngoingEventControls(true, false);
            showDeadlineControls(false);
        }
    }

    protected void showOngoingEventControls(boolean doShow, boolean isOngoing)
    {
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.rlOngoingEventControls);
        if(doShow)
        {
            rl.setVisibility(View.VISIBLE);
        }
        else
            rl.setVisibility(View.GONE);
    }

    protected void showDeadlineControls(boolean doShow)
    {
        RelativeLayout rl = (RelativeLayout)findViewById(R.id.rlDeadlineEventControls);
        if(doShow)
        {
            rl.setVisibility(View.VISIBLE);
        }
        else
            rl.setVisibility(View.GONE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }

    public static class DatePickerFragment extends DialogFragment
    {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {
            // Use the date already in the TextView as the default date in the picker
            Calendar cal = ((Calendar)getArguments().getSerializable(CreateTaskActivity.DATEKEY));

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener)getActivity(), cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        }
    }

    public static class TimePickerFragment extends DialogFragment
    {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Use the time already in the TextView as the default time in the picker
            Calendar cal = ((Calendar)getArguments().getSerializable(CreateTaskActivity.TIMEKEY));

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener)getActivity(), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),
                    DateFormat.is24HourFormat(getActivity()));
        }
    }

    protected void loadInitialValues()
    {
        Calendar c = Calendar.getInstance();
        String formattedDate = DateTimeFormatter.formatDate(c.getTime());
        String formattedTime = DateTimeFormatter.format24HrTime(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));


        /**** SETTING VALUES FOR ONGOING AND EVENT TASKS ****/
        // set "from text" to current date
        TextView tv = (TextView)findViewById(R.id.txtFromDate);
        tv.setText(formattedDate);
        // set "to date" to current date
        tv = (TextView)findViewById(R.id.txtToDate);
        tv.setText(formattedDate);
        // set "from time" to current time
        tv = (TextView)findViewById(R.id.txtFromTime);
        tv.setText(formattedTime);
        // set "to time" to current time
        tv = (TextView)findViewById(R.id.txtToTime);
        tv.setText(formattedTime);

        /**** SETTING VALUES FOR DEADLINE TASK ****/
        tv = (TextView)findViewById(R.id.txtDueByDate);
        tv.setText(formattedDate);
        tv = (TextView)findViewById(R.id.txtDueByTime);
        tv.setText(formattedTime);

    }

    protected void createTask()
    {
        String title = getTaskTitle();
        String description = getDescription();

        if(title.length() < 1 || description.length() < 1)
        {
            Toast.makeText(this, "Enter title and description.", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        Toast.makeText(this, "Saving task...", Toast.LENGTH_SHORT)
                .show();

        // determine what kind of task is being saved
        Spinner spinner = (Spinner)findViewById(R.id.spnTaskTypes);
        int spinnerSelection = spinner.getSelectedItemPosition();

        if(isDeadlineTaskString(spinnerSelection))
        {
            Log.v("and here", "found you!");
            // NOTE: We can use getFromDate() and getFromTime() because:
            // the text in txtDueByDate = txtFromDate and same for time
            Deadline dl = new Deadline(getPriority(), getTaskTitle(), getDescription(), getFromDate(), getFromTime(), true);
            dl.saveToStorage(this);
        }
        else if(isOngoingTaskString(spinnerSelection))
        {
            Habit task = new Habit(getPriority(), getTaskTitle(), getDescription(), getFromDate(), getFromTime(), getToDate(), getToTime(), true);
            task.saveToStorage(this);
        }
        else if(isEventTaskString(spinnerSelection))
        {
            Log.v("createTask()", "saving event");
            Event task = new Event(getPriority(), getTaskTitle(), getDescription(), getFromDate(), getFromTime(), getToDate(), getToTime(), true);
            task.saveToStorage(this);
        }
    }

    protected boolean isDeadlineTaskString(int id)
    {
        return id == 0;
    }

    protected boolean isOngoingTaskString(int id)
    {
        return id == 1;
    }

    protected boolean isEventTaskString(int id)
    {
        return id ==2;
    }

    protected int getPriority()
    {
        return 0;
    }

    protected String getTaskTitle()
    {
        return ((TextView)findViewById(R.id.txtTitle)).getText().toString();
    }

    protected String getDescription()
    {
        return ((TextView)findViewById(R.id.txtDescription)).getText().toString();
    }

    protected String getFromDate()
    {
        return ((TextView)findViewById(R.id.txtFromDate)).getText().toString();
    }

    protected String getFromTime()
    {
        return ((TextView)findViewById(R.id.txtFromTime)).getText().toString();
    }

    protected String getToDate()
    {
        return ((TextView)findViewById(R.id.txtToDate)).getText().toString();
    }

    protected String getToTime()
    {
        return ((TextView)findViewById(R.id.txtToTime)).getText().toString();
    }

}
