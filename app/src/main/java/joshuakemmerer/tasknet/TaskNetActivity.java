package joshuakemmerer.tasknet;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TaskNetActivity extends ListActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_net);

        ArrayList<Task> list = new ArrayList<>();
//        list.add(new Ongoing(0, "Here is my title", "This is some super awesome description that I just thought of", "Oct. 12, 2015", "6:00 PM", "Oct. 15, 2015", "6:23 AM", true));
//        list.add(new Ongoing(1, "Here is my other title", "Here is another awesome description!", "Oct. 14, 2015", "2:00 PM", "Oct. 15, 2015", "9:23 AM", true));

        List<Ongoing> ongoingTasks = Ongoing.getFromStorage(this);
        for(Ongoing t : ongoingTasks)
            list.add(t);

        // add all deadline tasks to allTasks
        List<Deadline> deadlines = Deadline.getFromStorage(this);
        for(Deadline d : deadlines)
            list.add(d);

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
        List<Ongoing> ongoings = Ongoing.getFromStorage(this);
        for(Ongoing ongoing : ongoings)
            allTasks.add(ongoing);

        // add all deadline tasks to allTasks
        List<Deadline> deadlines = Deadline.getFromStorage(this);
        for(Deadline d : deadlines)
            allTasks.add(d);
        Log.v("deadlines", Integer.toString(deadlines.size()));

        TaskAdapter adapter = new TaskAdapter(this, R.layout.task_layout, allTasks);
        setListAdapter(adapter);
    }

}
