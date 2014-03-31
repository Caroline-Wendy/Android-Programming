package mzx.spike.todolist.app;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by C.L.Wang on 14-3-14.
 */
public class NewItemFragment extends Fragment{

    private  OnNewItemAddedListener onNewItemAddedListener;

    //监听事件
    public interface OnNewItemAddedListener {
        public void onNewItemAdded(String newItem);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.new_item_fragment, container, false);

        final EditText myEditText = (EditText)view.findViewById(R.id.myEditText);

        //监听事件
        myEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN)
                    if ((i == KeyEvent.KEYCODE_DPAD_CENTER) ||
                       (i == KeyEvent.KEYCODE_ENTER)) {
                        String newItem = myEditText.getText().toString();
                        onNewItemAddedListener.onNewItemAdded(newItem);
                        myEditText.setText("");
                        return true;
                    }
                return false;
            }
        });

        return view;
    }

    //绑定到Activity
    public void onAttach(Activity activity) {

        super.onAttach(activity);

        try {
            onNewItemAddedListener = (OnNewItemAddedListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnNewItemAddedListener");
        }
    }

}
