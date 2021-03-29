package ro.pub.cs.systems.eim.lab04.contactsmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class ContactsManagerActivity extends AppCompatActivity {
    private final String TAG = "contactsmanager";
    private  final CallButtonListener listener = new CallButtonListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);

        findViewById(R.id.saveButton).setOnClickListener(listener);
        findViewById(R.id.cancelButton).setOnClickListener(listener);
        findViewById(R.id.auxF_button).setOnClickListener(listener);
    }

    void launchIntent() {
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        EditText nameField = findViewById(R.id.namefield);
        EditText phoneField = findViewById(R.id.phoneNumber);
        EditText emailField = findViewById(R.id.email);
        EditText jobTitleField = findViewById(R.id.jobTitle);
        EditText companyField = findViewById(R.id.Company);
        String name, phone, address=null, email, jobTitle=null, company=null;
        if (nameField.getText() == null)
            name = null;
        else
            name = nameField.getText().toString();

        if (phoneField.getText() == null)
            phone = null;
        else
            phone = phoneField.getText().toString();

        if (emailField.getText() == null)
            email = null;
        else
            email = emailField.getText().toString();

        if (name != null) {
            intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
        }
        if (phone != null) {
            intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
        }
        if (email != null) {
            intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
        }
        if (address != null) {
            intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address);
        }
        if (jobTitle != null) {
            intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle);
        }
        if (company != null) {
            intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company);
        }
        //TODO for others
//        ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
//        if (website != null) {
//            ContentValues websiteRow = new ContentValues();
//            websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
//            websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website);
//            contactData.add(websiteRow);
//        }
//        if (im != null) {
//            ContentValues imRow = new ContentValues();
//            imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
//            imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im);
//            contactData.add(imRow);
//        }
//        intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
        startActivity(intent);
    }

    class CallButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.saveButton) {
                Log.d(TAG, "save pressed launching intent");

                launchIntent();
                return;
            }
            if (v.getId() == R.id.cancelButton) {
                Log.d(TAG, "cancel pressed closing app");
                finish();
                return;
            }
            if (v.getId() == R.id.auxF_button) {
                Log.d(TAG, "aux pressed");
                Button but = findViewById(R.id.auxF_button);
                String text = but.getText().toString();
                if (text.equals(getString(R.string.hide_aux_fields))) {
                    but.setText(R.string.show_aux_fields);
                    findViewById(R.id.additional_container).setVisibility(View.INVISIBLE);
                }
                if (text.equals(getString(R.string.show_aux_fields))) {
                    but.setText(R.string.hide_aux_fields);
                    findViewById(R.id.additional_container).setVisibility(View.VISIBLE);
                }
                return;
            }
            Log.d(TAG, "onClick: with id" + v.getId());
        }
    }
}