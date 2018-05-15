package com.mt2.meilenstein3.hci_mobiles_tagebuch_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //private String TAG = getLocalClassName();

    private DiaryDbHandler diaryDbHandler;
    private DiaryDbAdapter diaryDbAdapter;
    private ListView entryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        diaryDbHandler = new DiaryDbHandler(this);
        diaryDbAdapter = new DiaryDbAdapter(this);
        entryListView = findViewById(R.id.listview);

        entryListView.setAdapter(diaryDbAdapter);
        updateListView();

        entryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long uid) {

                Intent i = new Intent(getApplicationContext(), ViewEntry.class);
                i.putExtra("_id", diaryDbAdapter.getItemId(position)); //id);
                startActivity(i);

                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    long id = extras.getLong("_id");
                }
            }
        });

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(view.getContext(), EntryEdit.class));
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateListView(){
        diaryDbAdapter.changeCursor(diaryDbHandler.queryEntries());
    }

    @Override
    protected void onResume(){
        super.onResume();
        updateListView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        diaryDbHandler.close();
    }
}
