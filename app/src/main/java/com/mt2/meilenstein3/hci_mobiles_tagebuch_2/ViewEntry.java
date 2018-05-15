package com.mt2.meilenstein3.hci_mobiles_tagebuch_2;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jakob on 13.05.2018.
 */

public class ViewEntry extends AppCompatActivity {
    private static final String TAG = ViewEntry.class.getSimpleName();

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
            id = extras.getLong("_id");
            Log.d(TAG, "Cursor ID = " + id);

            TextView titel = findViewById(R.id.txt_titel);
            TextView text = findViewById(R.id.txt_text);
            TextView date = findViewById(R.id.txt_date);
            ImageButton img = findViewById(R.id.btn_image);

            Cursor cursor = diaryDbHandler.getEntry(id);
            //titel.setText(cursor.getString(cursor.getColumnIndex(DiaryDbHandler.getTITLE())), TextView.BufferType.EDITABLE);

            int titleIndex = cursor.getColumnIndex(DiaryDbHandler.getTITLE());
            int textIndex = cursor.getColumnIndex(DiaryDbHandler.getTEXT());
            int dateIndex = cursor.getColumnIndex(DiaryDbHandler.getDATE());
            int imgLink = cursor.getColumnIndex(DiaryDbHandler.getImgLink());

            String titleContent = cursor.getString(titleIndex);
            String textContent = cursor.getString(textIndex);
            String dateContent = cursor.getString(dateIndex);
            String imgLinkContent = cursor.getString(imgLink);

            /*titel.setText("Titel");
            //text.setText(cursor.getString(cursor.getColumnIndex(DiaryDbHandler.getTEXT())), TextView.BufferType.EDITABLE);
            text.setText("Text");
            //date.setText(cursor.getString(cursor.getColumnIndex(DiaryDbHandler.getDATE())), TextView.BufferType.EDITABLE);
            date.setText("Datum");
            //Uri imguri = Uri.parse(cursor.getString(cursor.getColumnIndex(DiaryDbHandler.getImgLink())));
            //img.setImageURI(imguri);
            */
            titel.setText(titleContent);
            text.setText(textContent);
            date.setText(dateContent);
            Uri imguri = Uri.parse(imgLinkContent);
            img.setImageURI(imguri);
        }

        FloatingActionButton fabEdit = (FloatingActionButton) findViewById(R.id.btn_edit);
        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EntryEdit.class);
                i.putExtra("_id",id);
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
