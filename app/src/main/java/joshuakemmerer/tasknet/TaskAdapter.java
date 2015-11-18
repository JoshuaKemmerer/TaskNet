package joshuakemmerer.tasknet;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by temp on 10/17/2015.
 */
public class TaskAdapter extends ArrayAdapter<Task>
{
    private List<Task> tasks;
    private Activity context;

    static class ViewHolder {
        public TextView txtTitle;
        public TextView txtDescription;
        public TextView txtDateInfo;
    }

    public TaskAdapter(Activity context, int resource, List<Task> objects)
    {
        super(context, resource, objects);
        this.context = context;
        this.tasks = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;
        if(convertView == null)
        {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.task_layout, null);

            // configure View Holder
            viewHolder = new ViewHolder();
            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            viewHolder.txtDescription = (TextView) convertView.findViewById(R.id.txtDescription);
            viewHolder.txtDateInfo = (TextView) convertView.findViewById(R.id.txtDateInfo);

            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder)convertView.getTag();

        Task task = tasks.get(position);
        viewHolder.txtTitle.setText(task.getTitle());
        viewHolder.txtDescription.setText(task.getDescription());
        viewHolder.txtDateInfo.setText(getDateTimeInfo(task));

        return convertView;
    }

    private String getDateTimeInfo(Task task)
    {
        if(task.getClass().getCanonicalName().equals(Ongoing.getCanonicalName()))
            return "Ends on: " + getShortDateTime(task.getDate(), task.getTime());
        return "";
    }

    private String getShortDateTime(String date, String time)
    {
        return date.split(",")[1] + ", " + time.replace(":00", "");
    }
}
