package mzx.spike.todolist.app;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 14-3-17.
 */
public class ToDoItem {
    String task;
    Date created;

    public String getTask() {
        return task;
    }

    public Date getCreated() {
        return created;
    }

    public ToDoItem(String _task) {
        this(_task, new Date(java.lang.System.currentTimeMillis()));
    }

    public ToDoItem(String _task, Date _created) {
        task = _task;
        created = _created;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yy");
        String dateString = sdf.format(created);
        return "(" + dateString + ") " + task;
    }
}
