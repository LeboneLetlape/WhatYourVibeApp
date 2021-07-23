package za.co.whatyourvibe.PresentationLayer.Activities.EventActivities.EventDateMVP;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import za.co.whatyourvibe.LogicLayer.Helper.DateTimeHelper;
import za.co.whatyourvibe.LogicLayer.Models.ApplicationModel;
import za.co.whatyourvibe.LogicLayer.Models.Event;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.ActivityEventDateBinding;

public class EventDateActivity extends AppCompatActivity implements View.OnClickListener,DatePickerDialog.OnDateSetListener {

    ActivityEventDateBinding activityEventDateBinding;
    DatePickerDialog mDatePickerDialog;
    boolean isDateFrom;
    Date _SelectedDateFrom,_SelectedDateTo;
    Event event;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEventDateBinding = DataBindingUtil.setContentView(this,R.layout.activity_event_date);
        ActionBar mActionBar = getSupportActionBar();

        mActionBar.setTitle("Event Date");
        mActionBar.setDisplayHomeAsUpEnabled(true);

        event = ApplicationModel.event;

        mDatePickerDialog = new DatePickerDialog(this);
        mDatePickerDialog.setOnDateSetListener(this);
        activityEventDateBinding.mButtonSetDateFrom.setOnClickListener(this);
        activityEventDateBinding.mButtonSetDateTo.setOnClickListener(this);
        activityEventDateBinding.mButtonSave.setOnClickListener(this);

        if(event != null)
        {
            onSetView(event);
        }

    }

    private void onSetView(Event _Event)
    {
        if(_Event.getToDate() != null && _Event.getFromDate() != null){

            _SelectedDateFrom = _Event.getFromDate();_SelectedDateTo = _Event.getToDate();
            activityEventDateBinding.mTextViewDateFrom.setText(DateTimeHelper.GetFormatedDate(_Event.getFromDate()));
            activityEventDateBinding.mTextViewDateTo.setText(DateTimeHelper.GetFormatedDate(_Event.getToDate()));
        }
    }

    @Override
    public void onClick(View view) {

        if (activityEventDateBinding.mButtonSetDateFrom.getId() == view.getId()) {

            Calendar calendar = DateTimeHelper.GetDateFromCalendar(_SelectedDateFrom);

            if(calendar.get(Calendar.YEAR) > 2000) {
                mDatePickerDialog.updateDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH) + 1);
            }
            mDatePickerDialog.show();
            isDateFrom = true;

        }
        if (activityEventDateBinding.mButtonSetDateTo.getId() == view.getId()) {

            Calendar calendar = DateTimeHelper.GetDateFromCalendar(_SelectedDateTo);
            if(calendar.get(Calendar.YEAR) > 2000)
            {
                mDatePickerDialog.updateDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH) + 1);
            }
            mDatePickerDialog.show();
            isDateFrom = false;

        }
        if (activityEventDateBinding.mButtonSave.getId() == view.getId()) {

            if(_SelectedDateTo != null && _SelectedDateFrom != null){

                event.setFromDate(_SelectedDateFrom);
                event.setToDate(_SelectedDateTo);

                ApplicationModel.getEventValidation().IsDateValid = true;
                ApplicationModel.event = event;
                finish();
            }
            else
            {
                Toast.makeText(this, "Please make sure that you have chosen all the dates", Toast.LENGTH_SHORT).show();
            }
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

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {


        Calendar newDate = Calendar.getInstance();
        newDate.set(i, i1, i2);

        _SelectedDateFrom = (isDateFrom) ? newDate.getTime() : _SelectedDateFrom;
        _SelectedDateTo = (!isDateFrom) ? newDate.getTime() : _SelectedDateTo;

        if(isDateFrom)
        {
            _SelectedDateFrom = newDate.getTime();
            activityEventDateBinding.mTextViewDateFrom.setText(DateTimeHelper.GetFormatedDate(_SelectedDateFrom));

            Toast.makeText(this, "Date From Configured Successfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            _SelectedDateTo = newDate.getTime();
            activityEventDateBinding.mTextViewDateTo.setText(DateTimeHelper.GetFormatedDate(_SelectedDateTo));

            Toast.makeText(this, "Date To Configured Successfully", Toast.LENGTH_SHORT).show();
        }

    }
}
