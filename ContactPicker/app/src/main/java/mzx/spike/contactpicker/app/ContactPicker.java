package mzx.spike.contactpicker.app;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by C.L.Wang on 14-3-20.
 */
public class ContactPicker extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Cursor c = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null, null
        );

        String[] from = new String[]{ContactsContract.Contacts.DISPLAY_NAME_PRIMARY};
        int[] to = new int[]{R.id.itemTextView};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.listitemlayout, c, from, to, 0);
        ListView lv = (ListView)findViewById(R.id.contactListView);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new ListView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                c.moveToPosition(pos);
                int rowId = c.getInt(c.getColumnIndexOrThrow("_id"));
                Uri outURI = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, rowId);
                Intent outData = new Intent();
                outData.setData(outURI);
                setResult(Activity.RESULT_OK, outData);
                finish();
            }
        });
    }
}
