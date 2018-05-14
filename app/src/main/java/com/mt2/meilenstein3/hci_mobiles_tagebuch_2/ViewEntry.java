package com.mt2.meilenstein3.hci_mobiles_tagebuch_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by jakob on 13.05.2018.
 */

public class ViewEntry extends AppCompatActivity {

    private DiaryDbHandler diaryDbHandler;
    private DiaryDbAdapter diaryDbAdapter;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entryview);

        diaryDbHandler = new DiaryDbHandler(this);
        diaryDbAdapter = new DiaryDbAdapter(this);
        Intent intent = getIntent();
        id  = intent.getIntExtra("id", -1);
        if(id!=-1){
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        diaryDbHandler.close();
    }
}