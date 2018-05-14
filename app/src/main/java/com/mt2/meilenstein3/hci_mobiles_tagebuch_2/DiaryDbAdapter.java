package com.mt2.meilenstein3.hci_mobiles_tagebuch_2;

import android.content.Context;
import android.database.Cursor;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DiaryDbAdapter extends CursorAdapter {

    private LayoutInflater layoutInflater;


    DiaryDbAdapter(Context context){
        super(context, null, 0);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        /*
         * first parameter in inflate() has to be changes with corresponding layout file
         * XML file.
         * E.g.: layoutInflater.inflate(R.layout.EXAMPLE_NAME, null);
         * MainActivity Layout file or ListActivity / ListView Layout file...don't know ^^
         */
        return layoutInflater.inflate(R.layout.diary_entries_list,null);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        int titleIndex = cursor.getColumnIndex(DiaryDbHandler.getTITLE());
        int textIndex = cursor.getColumnIndex(DiaryDbHandler.getTEXT());
        int dateIndex = cursor.getColumnIndex(DiaryDbHandler.getDATE());
        int imgLink = cursor.getColumnIndex(DiaryDbHandler.getImgLink());


        String titleContent = cursor.getString(titleIndex);
        String textContent = cursor.getString(textIndex);
        String dateContent = cursor.getString(dateIndex);
        String imgLinkContent = cursor.getString(imgLink);


        /*
         * Change parameter in view.findViewById() to corresponding R.id.EXAMPLE_NAME
         * Example implmenetation: diary_entries_list.xml provides the list_titel_text ID
         */

        TextView titleView = view.findViewById(R.id.list_titel_text);
        TextView textView = view.findViewById(R.id.txtStory);
        TextView dateView = view.findViewById(R.id.txtDate);
        ConstraintLayout layout = view.findViewById(R.id.constLayout);
        //ImageView imgLinkView = view.findViewById(R.id.imgEntry);

        titleView.setText(titleContent);
        textView.setText(textContent);
        dateView.setText(dateContent);

        //imgLinkView.setText(imgLinkContent);
        //imgLinkView.se
    }


}
