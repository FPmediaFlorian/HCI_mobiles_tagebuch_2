package com.mt2.meilenstein3.hci_mobiles_tagebuch_2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;



public class EntryEdit extends AppCompatActivity {


    private DiaryDbHandler diaryDbHandler;
    private Uri imageLink;
    private long id;
    Calendar myCalendar = Calendar.getInstance();
    private static final int PICK_PHOTO = 0;
    //EditText edittext= findViewById(R.id.txt_date);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_edit);

        MyEditTextDatePicker datepicker = new MyEditTextDatePicker(this, R.id.txt_date);
        diaryDbHandler = new DiaryDbHandler(this);
        Bundle extras = getIntent().getExtras();

        imageLink = Uri.parse("android.resource://com.mt2.meilenstein3.hci_mobiles_tagebuch_2/drawable/background_placeholder.png");

        if (extras != null) {
            id = extras.getLong("_id");
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
        else id=-1;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btn_submit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText titel = findViewById(R.id.txt_titel);
                EditText text = findViewById(R.id.txt_text);
                EditText date = findViewById(R.id.txt_date);
                if(id==-1)
                    diaryDbHandler.insertEntry(titel.getText().toString(), text.getText().toString(), date.getText().toString(),
                            imageLink.toString(), 10);
                else {
                    diaryDbHandler.changeDate(id, date.getText().toString());
                    diaryDbHandler.changeImgLink(id, imageLink.toString());
                    diaryDbHandler.changeText(id, text.getText().toString());
                    diaryDbHandler.changeTitle(id, titel.getText().toString());
                }
                startActivity(new Intent(view.getContext(), MainActivity.class));
            }
        });

        final ImageButton imgButton = findViewById(R.id.btn_image);
        imgButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                pickImage();
                imgButton.setImageURI(imageLink);
                imgButton.refreshDrawableState();
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        EntryEdit.super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PHOTO && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                return;
            }
            imageLink = data.getData();

            //Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap...
        }
    }

    public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO);
    }

    @Override
    public void onResume(){
        super.onResume();
        //recreate();
    }
}

