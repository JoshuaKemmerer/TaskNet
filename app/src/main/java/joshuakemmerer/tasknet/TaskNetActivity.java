package joshuakemmerer.tasknet;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class TaskNetActivity extends ListActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_net);


        ArrayList<Task> list = new ArrayList<>();

        List<Habit> habitTasks = Habit.getFromStorage(this);
        for(Habit t : habitTasks)
            list.add(t);

        // add all deadline tasks to list
        List<Deadline> deadlines = Deadline.getFromStorage(this);
        for(Deadline d : deadlines)
            list.add(d);

        // add all event tasks to list
        List<Event> events = Event.getFromStorage(this);
        for(Event e : events)
            list.add(e);
        Log.v("events", Integer.toString(events.size()));

        TaskAdapter adapter = new TaskAdapter(this, R.layout.task_layout, list);
        setListAdapter(adapter);
        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        */

//        loadTasks();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    protected void loadTasks()
    {
        ArrayList<Task> allTasks = new ArrayList<>();

        // add all ongoing tasks to allTasks
        List<Habit> habits = Habit.getFromStorage(this);
        for(Habit habit : habits)
            allTasks.add(habit);

        // add all deadline tasks to allTasks
        List<Deadline> deadlines = Deadline.getFromStorage(this);
        for(Deadline d : deadlines)
            allTasks.add(d);
        Log.v("deadlines", Integer.toString(deadlines.size()));

        // add all event tasks to allTasks
        List<Event> events = Event.getFromStorage(this);
        for(Event e : events)
            allTasks.add(e);
        Log.v("events", Integer.toString(events.size()));

        TaskAdapter adapter = new TaskAdapter(this, R.layout.task_layout, allTasks);
        setListAdapter(adapter);
    }

}
