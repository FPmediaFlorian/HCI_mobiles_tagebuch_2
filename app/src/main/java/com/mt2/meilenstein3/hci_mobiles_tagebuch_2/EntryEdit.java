package com.mt2.meilenstein3.hci_mobiles_tagebuch_2;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by jakob on 13.05.2018.
 */

public class EntryEdit extends AppCompatActivity {


    private DiaryDbHandler diaryDbHandler;
    private Uri imageLink;
    Calendar myCalendar = Calendar.getInstance();
    private static final int PICK_PHOTO = 0;
    //EditText edittext= findViewById(R.id.txt_date);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_edit);

        MyEditTextDatePicker datepicker = new MyEditTextDatePicker(this, R.id.txt_date);
        diaryDbHandler = new DiaryDbHandler(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btn_submit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText titel = findViewById(R.id.txt_titel);
                EditText text = findViewById(R.id.txt_text);
                EditText date = findViewById(R.id.txt_date);
                diaryDbHandler.insertEntry(titel.getText().toString(), text.getText().toString(), date.toString(),
                        imageLink.toString(), 10);
                Toast.makeText(view.getContext(), titel.getText().toString() + text.getText().toString(), Toast.LENGTH_SHORT);

            }
        });
        final ImageButton imgButton = findViewById(R.id.btn_image);
        imgButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                pickImage();
                imgButton.setImageURI(imageLink);
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


}
