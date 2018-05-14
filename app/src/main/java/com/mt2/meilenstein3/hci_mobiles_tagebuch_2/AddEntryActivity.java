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
                diaryDbHandler.insertEntry("Test Title No. 2", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc,", "1-1-2018",
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
