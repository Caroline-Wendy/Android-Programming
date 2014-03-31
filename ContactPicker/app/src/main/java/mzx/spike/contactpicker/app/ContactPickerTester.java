package mzx.spike.contactpicker.app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 14-3-20.
 */
public class ContactPickerTester extends Activity {

    public static final int PICK_CONTACT = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.contactpickertester);
        Button button = (Button)findViewById(R.id.pick_contact_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts/"));
                startActivityForResult(intent, PICK_CONTACT);
            }
        });
    }

    @Override
    public void onActivityResult(int reqCode, int resCode, Intent data) {
        super.onActivityResult(reqCode, resCode, data);

        switch (reqCode) {
            case (PICK_CONTACT) : {
                if (resCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c = getContentResolver().query(contactData,null, null, null, null);
                    c.moveToFirst();
                    String name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
                    c.close();
                    TextView tv = (TextView)findViewById(R.id.selected_contact_textview);
                    tv.setText(name);
                }
            }
            default: break;
        }
    }

}
