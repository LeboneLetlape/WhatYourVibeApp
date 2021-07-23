package za.co.whatyourvibe.PresentationLayer.Activities.EventActivities.EventDescriptionActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import za.co.whatyourvibe.LogicLayer.Models.ApplicationModel;
import za.co.whatyourvibe.LogicLayer.Models.Event;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.ActivityEventDescriptionBinding;

public class EventDescriptionActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityEventDescriptionBinding activityEventDescriptionBinding;
    Event event;
    String _StringDescripition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityEventDescriptionBinding = DataBindingUtil.setContentView(this,R.layout.activity_event_description);
        ActionBar mActionBar = getSupportActionBar();

        mActionBar.setTitle("Event Description");
        mActionBar.setDisplayHomeAsUpEnabled(true);

        event = ApplicationModel.event;
        _StringDescripition = event.getDescription();

        if(_StringDescripition != null)
        {
            activityEventDescriptionBinding.mEditTextDescription.setText(_StringDescripition);
        }

        activityEventDescriptionBinding.mButtonSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        _StringDescripition = activityEventDescriptionBinding.mEditTextDescription.getText().toString().trim();

        if(!_StringDescripition.isEmpty())
        {
            ApplicationModel.getEventValidation().IsDescriptionValid = true;
            ApplicationModel.event.setDescription(_StringDescripition);
            finish();
        }
        else
        {
            Toast.makeText(this, "Please fill in the event description", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);

    }
}