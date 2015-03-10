package com.hellbilling.kapitola19;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.view.View;
import android.widget.Button;
import android.util.Log;

public class Activity1 extends Activity {
    static final int PICK_REQUEST=1337;
    Button viewButton=null;
    Uri contact=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity1);

        viewButton=(Button)findViewById(R.id.view);
        restoreMe(savedInstanceState);

        viewButton.setEnabled(contact!=null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode==PICK_REQUEST) {
            if (resultCode==RESULT_OK) {
                contact=data.getData();
                viewButton.setEnabled(true);
            }
        }
    }

       // toto vola tlacitko pick
    public void pickContact(View v) {
        // do intentu volame akciu (ACTION_PICK)ktora je definovana Contacts.CONTENT_URI
        Intent i=new Intent(Intent.ACTION_PICK,
                Contacts.CONTENT_URI);
        // identifikator requestu, asi volne urceny
        startActivityForResult(i, PICK_REQUEST);
    }

    public void viewContact(View v) {
        startActivity(new Intent(Intent.ACTION_VIEW, contact));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (contact!=null) {
            outState.putString("contact", contact.toString());
        }
    }

    private void restoreMe(Bundle state) {
        contact=null;

        if (state!=null) {
            String contactUri=state.getString("contact");

            if (contactUri!=null) {
                contact=Uri.parse(contactUri);
            }
        }
    }
}
