package com.mt2.meilenstein3.hci_mobiles_tagebuch_2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by jakob on 13.05.2018.
 */

public class ViewEntry extends AppCompatActivity {

    private DiaryDbHandler diaryDbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entryview);

        diaryDbHandler = new DiaryDbHandler(this);
    }
}