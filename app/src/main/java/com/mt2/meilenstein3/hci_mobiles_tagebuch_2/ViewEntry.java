package com.mt2.meilenstein3.hci_mobiles_tagebuch_2;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jakob on 13.05.2018.
 */

public class ViewEntry extends AppCompatActivity {

    private DiaryDbHandler diaryDbHandler;
    private DiaryDbAdapter diaryDbAdapter;
    private long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entryview);

        diaryDbHandler = new DiaryDbHandler(this);
        diaryDbAdapter = new DiaryDbAdapter(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getLong("id");
            Cursor cursor = diaryDbHandler.getEntry(id);
            EditText titel = findViewById(R.id.txt_titel);
            EditText text = findViewById(R.id.txt_text);
            EditText date = findViewById(R.id.txt_date);
            ImageButton img = findViewById(R.id.btn_image);


            titel.setText(cursor.getString(cursor.getColumnIndex("title")), TextView.BufferType.EDITABLE);
            text.setText(cursor.getString(cursor.getColumnIndex("text")), TextView.BufferType.EDITABLE);
            date.setText(cursor.getString(cursor.getColumnIndex("date")), TextView.BufferType.EDITABLE);
            Uri imguri = Uri.parse(cursor.getString(cursor.getColumnIndex("imgLink")));
            img.setImageURI(imguri);

        }

        FloatingActionButton fabEdit = (FloatingActionButton) findViewById(R.id.btn_edit);
        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ViewEntry.class);
                i.putExtra("id",id);
                startActivity(i);
            }
        });

        FloatingActionButton fabDelete = (FloatingActionButton) findViewById(R.id.btn_delete);
        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaryDbHandler.deleteEntry(id);
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        diaryDbHandler.close();
    }
}
