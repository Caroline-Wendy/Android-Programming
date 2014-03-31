package mzx.spike.download.app;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

    private static final String TAG = "Download";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonDownload = (Button)findViewById(R.id.download_button);
        buttonDownload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myDownload();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    private void myDownload() {

        String serviceString = Context.DOWNLOAD_SERVICE;
        DownloadManager downloadManager;
        downloadManager = (DownloadManager) getSystemService(serviceString);

        Uri uri = Uri.parse("http://i8.topit.me/8/8b/ff/1121867145ee1ff8b8l.jpg");
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setDestinationInExternalPublicDir("/Download", "file.jpg");
        request.setVisibleInDownloadsUi(true);
        long reference = downloadManager.enqueue(request);

        Log.d(TAG, "Download Reference: " + reference);

        Cursor c = downloadManager.query(new DownloadManager.Query().setFilterById(reference));
        if (c == null) {
            Toast.makeText(this, "Download not found!", Toast.LENGTH_LONG).show();
        } else {
            c.moveToFirst();
            Log.d(getClass().getName(), "Column_id : " +
                    c.getLong(c.getColumnIndex(DownloadManager.COLUMN_ID)));
            Log.d(getClass().getName(), "Column_bytes_downloaded so far : " +
                    c.getLong(c.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR)));
            Log.d(getClass().getName(), "Column last modified timestamp : " +
                    c.getLong(c.getColumnIndex(DownloadManager.COLUMN_LAST_MODIFIED_TIMESTAMP)));
            Log.d(getClass().getName(), "Column local uri : " +
                    c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI)));
            Log.d(getClass().getName(), "Column statue : " +
                    c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS)));
            Log.d(getClass().getName(), "Column reason : " +
                    c.getInt(c.getColumnIndex(DownloadManager.COLUMN_REASON)));

            Toast.makeText(this, statusMessage(c), Toast.LENGTH_LONG).show();
        }

        startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));
    }


    private String statusMessage(Cursor c){
        switch(c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS))){
            case DownloadManager.STATUS_FAILED:
                return "Download failed";
            case DownloadManager.STATUS_PAUSED:
                return "Download paused";
            case DownloadManager.STATUS_PENDING:
                return "Download pending";
            case DownloadManager.STATUS_RUNNING:
                return "Download in progress!";
            case DownloadManager.STATUS_SUCCESSFUL:
                return "Download finished";
            default:
                return "Unknown Information";
        }
    }

}
