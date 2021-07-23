package za.co.whatyourvibe.PresentationLayer.Activities.EventActivities.EventTitleMVP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import za.co.whatyourvibe.LogicLayer.Models.ApplicationModel;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.ActivityEventTitleBinding;

public class EventTitleActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityEventTitleBinding activityEventTitleBinding;
    String _StringTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEventTitleBinding = DataBindingUtil.setContentView(this,R.layout.activity_event_title);

        ActionBar mActionBar = getSupportActionBar();

        mActionBar.setTitle("Event Title");
        mActionBar.setDisplayHomeAsUpEnabled(true);

        _StringTitle = ApplicationModel.event.getTitle();
        if(_StringTitle  != null)
        {
            activityEventTitleBinding.mEditTextTitle.setText(_StringTitle);
        }
        activityEventTitleBinding.mButtonSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        _StringTitle = activityEventTitleBinding.mEditTextTitle.getText().toString().trim();
        if(!_StringTitle.isEmpty())
        {
            ApplicationModel.getEventValidation().IsTitleValid = true;
            ApplicationModel.event.setTitle(_StringTitle);
            finish();
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