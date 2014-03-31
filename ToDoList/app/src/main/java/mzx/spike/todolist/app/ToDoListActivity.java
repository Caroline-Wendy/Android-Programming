package mzx.spike.todolist.app;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class ToDoListActivity extends ActionBarActivity implements NewItemFragment.OnNewItemAddedListener {

    //使用ToDoItem对象代替String
    private ToDoItemAdapter aa;
    private ArrayList<ToDoItem> toDoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        //获得fragment的引用
        FragmentManager fm = getFragmentManager();
        ToDoListFragment toDoListFragment = (ToDoListFragment)fm.findFragmentById(R.id.ToDoListFragment);

        toDoItems = new ArrayList<ToDoItem>();

        int resID = R.layout.todolist_item;
        //三个参数
        aa = new ToDoItemAdapter(this, resID, toDoItems);

        toDoListFragment.setListAdapter(aa);
    }

    //重写了接口的方法
    public void onNewItemAdded(String newItem) {
        ToDoItem newToDoItem = new ToDoItem(newItem);
        toDoItems.add(0, newToDoItem);
        aa.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.to_do_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
