package com.hellbilling.kapitola19;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends ListActivity {
    private TextView selection;
    private Field[] fields;
    private ArrayList<String> activitiesList = new ArrayList<>();

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);

        // Najdi hodnoty vsetky title_activity_activity a uloz do pola activitiesList
        fields = R.string.class.getFields();

        //for (int  i =0; i < fields.length; i++) {
        for(Field field : fields)  {
            int resId = getResources().getIdentifier(field.getName(), "string", getPackageName());
            String fieldName = field.getName();
            String fieldValue = "";
            if (resId != 0) {
                fieldValue = getResources().getString(resId);
            }
            if (fieldName.contains("title_activity_activity")) {
                activitiesList.add(fieldValue);
            }
        }

        Collections.sort(activitiesList);
        // Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();

        // Automaticky podla vsetkych stringov co obsahuju title_activity_activity v strings.xml
        // vytvor vybery vsetkych aktivity
        //
        // este by bolo dobre zistit ako automaticky spustat aktivity v onListItemClick
        setListAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                activitiesList));

        selection=(TextView)findViewById(R.id.selection);
    }

    public void onListItemClick(ListView parent, View v, int position, long id) {
        selection.setText(activitiesList.get(position));
        Intent intent;
        switch(position) {
            case 0:
                intent = new Intent(this, Activity1.class);
                startActivity(intent);
                break;

        }
    }
}