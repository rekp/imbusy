package com.patrickrek.imbusy;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Amux on 6/16/2016.
 */
public class LogActivity extends AppCompatActivity {

    private ArrayList<String> smsList = new ArrayList<String>();
    private ListView mListView;
    private ArrayAdapter<String> adapter;

    private static final String INBOX_URI = "content://sms/inbox";
    private static final String SENT_URI = "content://sms/sent";

    private static LogActivity activity;
    public static LogActivity instance() {
        return activity;
    }

    @Override
    public void onStart() {
        super.onStart();
        activity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, smsList);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(MyItemClickListener);

        readSMS();

    }



    public void updateList(final String newSms) {
        adapter.insert(newSms, 0);
        adapter.notifyDataSetChanged();
    }

    private AdapterView.OnItemClickListener MyItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
            try {
                Toast.makeText(getApplicationContext(), adapter.getItem(pos), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    };

    public void readSMS() {
        ContentResolver contentResolver = getContentResolver();
        Cursor smsInboxCursor = contentResolver.query(Uri.parse(INBOX_URI), null, null, null, null);

        int senderIndex = smsInboxCursor.getColumnIndex("address");
        int messageIndex = smsInboxCursor.getColumnIndex("body");

        if (messageIndex < 0 || !smsInboxCursor.moveToFirst()) return;

        do {

            String sender = smsInboxCursor.getString(senderIndex);
            String message = smsInboxCursor.getString(messageIndex);

            String formattedText = String.format(getResources().getString(R.string.sms_message), sender, message);

        } while (smsInboxCursor.moveToNext());
    }

    public Boolean checkIfDuplicate(String number, String message){

        String[] projection = {"address", "body"};
        String selection = number;


        Cursor cursor1 = getContentResolver().query(Uri.parse(SENT_URI),
                projection,
                selection ,
                null,
                null);

        if (cursor1 == null) {
            return false;
        }

        return true;
    }
}
