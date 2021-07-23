package za.co.whatyourvibe.PresentationLayer.Activities.EventActivities.EventTimeMVP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import za.co.whatyourvibe.LogicLayer.Helper.ViewHelper;
import za.co.whatyourvibe.LogicLayer.Models.ApplicationModel;
import za.co.whatyourvibe.LogicLayer.Models.Event;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.ActivityEventTimeBinding;

public class EventTimeActivity extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener
{

    ActivityEventTimeBinding activityEventTimeBinding;
    Event event;
    List<View> ViewOnclickList;
    TimePickerDialog mTimePickerDialog;
    boolean isFrom;
    int _IntHour,_IntMin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityEventTimeBinding = DataBindingUtil.setContentView(this, R.layout.activity_event_time);

        event = ApplicationModel.event;

        ActionBar mActionBar = getSupportActionBar();

        mActionBar.setTitle("Event Time");
        mActionBar.setDisplayHomeAsUpEnabled(true);

        ViewOnclickList = new ArrayList<>();

        final Calendar c = Calendar.getInstance();
        _IntHour = c.get(Calendar.HOUR_OF_DAY);
        _IntMin = c.get(Calendar.MINUTE);

        mTimePickerDialog = new TimePickerDialog(this,this,_IntHour,_IntMin,true) ;

        ViewOnclickList.add(activityEventTimeBinding.mButtonSave);
        ViewOnclickList.add(activityEventTimeBinding.mButtonSetTimeFrom);
        ViewOnclickList.add(activityEventTimeBinding.mButtonSetTimeTo);

        SetViewOnClick(ViewOnclickList);

    }

    private void SetViewOnClick(List<View> ViewOnclickList)
    {
        for (int x = 0;x<ViewOnclickList.size();x++)
        {
            ViewOnclickList.get(x).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {

        Toast.makeText(this, "Show Time", Toast.LENGTH_SHORT).show();
        try {
            if(activityEventTimeBinding.mButtonSave.getId() == view.getId()){


                ApplicationModel.getEventValidation().IsTimeValid = true;
                ApplicationModel.event = event;
                finish();
            }

            if(activityEventTimeBinding.mButtonSetTimeFrom.getId() == view.getId()){
                mTimePickerDialog.show();
                isFrom = true;

                if(event.getFromTime() != null)
                    mTimePickerDialog.updateTime(Integer.valueOf(GetTime(event.getFromTime())[0]),Integer.valueOf(GetTime(event.getFromTime())[1]));
                else
                    mTimePickerDialog.updateTime(_IntHour,_IntMin);
            }

            if(activityEventTimeBinding.mButtonSetTimeTo.getId() == view.getId()){
                mTimePickerDialog.show();
                isFrom = false;
                if(event.getToTime() != null)
                    mTimePickerDialog.updateTime(Integer.valueOf(GetTime(event.getToTime())[0]),Integer.valueOf(GetTime(event.getToTime())[1]));
                else
                    mTimePickerDialog.updateTime(_IntHour,_IntMin);
            }
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private String[] GetTime(String Time)
    {
        return Time.split(":", 2);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

        if(isFrom)
        {
            event.setFromTime(timePicker.getHour() +":"+timePicker.getMinute());
            activityEventTimeBinding.mTextViewDateFrom.setText(timePicker.getHour() +":"+timePicker.getMinute());
        }
        else
        {
            event.setToTime(timePicker.getHour() +":"+timePicker.getMinute());
            activityEventTimeBinding.mTextViewDateTo.setText(timePicker.getHour() +":"+timePicker.getMinute());
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