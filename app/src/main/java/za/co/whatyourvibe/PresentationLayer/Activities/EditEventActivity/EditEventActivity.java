package za.co.whatyourvibe.PresentationLayer.Activities.EditEventActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import za.co.whatyourvibe.LogicLayer.Models.ApplicationModel;
import za.co.whatyourvibe.LogicLayer.Models.Event;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.ActivityEditEventBinding;

public class EditEventActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityEditEventBinding mActivityEditEventBinding;
    Event _Event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityEditEventBinding = DataBindingUtil.setContentView(this,R.layout.activity_edit_event);
        _Event = ApplicationModel.getEvent();


        onViewSet(_Event);

        //mActivityEditEventBinding.mButtonSave.setOnClickListener(this);
    }

    private void onViewSet(Event _Event)
    {
        mActivityEditEventBinding.mTextViewTitle.setText(_Event.getTitle());
        mActivityEditEventBinding.mTextViewDescription.setText(_Event.getDescription());
        mActivityEditEventBinding.mTextViewCategory.setText(_Event.getEventCategory().getName());
        mActivityEditEventBinding.mTextViewLocation.setText(_Event.getLocation());
        //mActivityEditEventBinding.mTextViewStatus.setText(_Event.get);

        mActivityEditEventBinding.mTextViewTime.setText(_Event.getFromTime() +" - "+_Event.getFromTime());
    }

    @Override
    public void onClick(View view) {

    }
}