package za.co.whatyourvibe.PresentationLayer.Activities.EventActivities.EventContactMVP;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import za.co.whatyourvibe.LogicLayer.Models.ApplicationModel;
import za.co.whatyourvibe.LogicLayer.Models.EventContact;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.ActivityEventContactBinding;

public class EventContactActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityEventContactBinding activityEventContactBinding;
    List<EventContact> _EventContactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEventContactBinding = DataBindingUtil.setContentView(this,R.layout.activity_event_contact);

        ActionBar mActionBar = getSupportActionBar();

        mActionBar.setTitle("Event Contact");
        mActionBar.setDisplayHomeAsUpEnabled(true);


        _EventContactList = ApplicationModel.getEvent().getEventContacts();
        activityEventContactBinding.mButtonDone.setOnClickListener(this);

        onSetView();
    }

    private void onSetView()
    {
        for(EventContact eventContact : _EventContactList)
        {
            if(eventContact.contact.getName().equalsIgnoreCase("Telephone"))
            {
                if(eventContact.getInfo() != null)
                {
                    activityEventContactBinding.mEditTextTelephone.setText(eventContact.getInfo());
                }
            }

            if(eventContact.contact.getName().equalsIgnoreCase("Message"))
            {
                if(eventContact.getInfo() != null)
                {
                    activityEventContactBinding.mEditTextMessage.setText(eventContact.getInfo());
                }
            }

            if(eventContact.contact.getName().equalsIgnoreCase("Email"))
            {
                if(eventContact.getInfo() != null)
                {
                    activityEventContactBinding.mEditTextEmail.setText(eventContact.getInfo());
                }
            }
        }

        Toast.makeText(this, "Set View :"+_EventContactList.size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {


        if (activityEventContactBinding.mButtonDone.getId() == view.getId()) {
            for (EventContact eventContact : _EventContactList) {
                if (eventContact.contact.getName().equalsIgnoreCase("Telephone")) {
                    eventContact.setInfo(activityEventContactBinding.mEditTextTelephone.getText().toString().trim());
                }

                if (eventContact.contact.getName().equalsIgnoreCase("Message")) {
                    eventContact.setInfo(activityEventContactBinding.mEditTextMessage.getText().toString().trim());
                }

                if (eventContact.contact.getName().equalsIgnoreCase("Email")) {
                    eventContact.setInfo(activityEventContactBinding.mEditTextEmail.getText().toString().trim());
                }
            }
            ApplicationModel.getEventValidation().IsContactValid = true;
            ApplicationModel.event.setEventContacts(_EventContactList);
            finish();
        }
    }
}