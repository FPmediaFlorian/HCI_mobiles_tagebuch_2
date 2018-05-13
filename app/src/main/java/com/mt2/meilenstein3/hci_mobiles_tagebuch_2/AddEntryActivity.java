package com.mt2.meilenstein3.hci_mobiles_tagebuch_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/*
 * Template for following functionality:
 * Add a new diary entry.
 * change parameters with the right String values
 *
 * What's missing?
 * TextField for Users to enter Text, Title, Date, ect...
 * What the fuck is feeling Parameter?? -> We have to discuss this
 */
public class AddEntryActivity extends AppCompatActivity {

    private DiaryDbHandler diaryDbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        diaryDbHandler = new DiaryDbHandler(this);

        final Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaryDbHandler.insertEntry("Test Title", "Test Text", "1-1-2018",
                        "/test", 10);
            }
        });

        final Button changeTitle = findViewById(R.id.change_title);
        changeTitle.setOnClickListener((view) -> {
            diaryDbHandler.changeTitle(1, "Changed Text Title");
        });

        final Button deleteEntry = findViewById(R.id.delete_entry);
        deleteEntry.setOnClickListener((view) -> {
            diaryDbHandler.deleteEntry(5);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        diaryDbHandler.close();
    }
}
