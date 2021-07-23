package za.co.whatyourvibe.PresentationLayer.Activities.EventActivities.EventRestrictionMVP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import za.co.whatyourvibe.LogicLayer.Models.ApplicationModel;
import za.co.whatyourvibe.LogicLayer.Models.EventRestriction;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.ActivityEventRestrictionBinding;

public class EventRestrictionActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    ActivityEventRestrictionBinding activityEventRestrictionBinding;
    List<EventRestriction> _RestrictionList;
    List<String> _StringListSmoking,_StringListAlcohol,_StringListAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEventRestrictionBinding = DataBindingUtil.setContentView(this,R.layout.activity_event_restriction);

        getSupportActionBar().setTitle("Event Restriction");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        _RestrictionList = ApplicationModel.event.getEventRestrictions();

        if(_RestrictionList == null)
        {
            _RestrictionList = new ArrayList<>();

            _RestrictionList.add(new EventRestriction(1,"Smoking"));
            _RestrictionList.add(new EventRestriction(2,"Alcohol"));
            _RestrictionList.add(new EventRestriction(3,"Age"));
        }

        _StringListSmoking = new ArrayList<>();
        _StringListSmoking.add("Allowed");
        _StringListSmoking.add("Not Allowed");


        _StringListAlcohol = new ArrayList<>();
        _StringListAlcohol.add("Allowed");
        _StringListAlcohol.add("Not Allowed");
        _StringListAlcohol.add("No Wines");


        _StringListAge = new ArrayList<>();
        _StringListAge.add("All");
        _StringListAge.add("Greater Then");

        activityEventRestrictionBinding.mSpinnerSmoking.setAdapter(StringArrayAdapter(_StringListSmoking));
        activityEventRestrictionBinding.mSpinnerAlcohol.setAdapter(StringArrayAdapter(_StringListAlcohol));

        activityEventRestrictionBinding.mSpinnerAge.setAdapter(StringArrayAdapter(_StringListAge));
        activityEventRestrictionBinding.mButtonDone.setOnClickListener(this);

        activityEventRestrictionBinding.mEditTextAge.setEnabled(false);
        activityEventRestrictionBinding.mSpinnerAge.setOnItemSelectedListener(this);

        onSetViewDetails();
    }

    private void onSetViewDetails()
    {
        for (EventRestriction eventRestriction : _RestrictionList)
        {
            if(eventRestriction.getRestrictions().getName().equalsIgnoreCase("Smoking"))
            {
                if(eventRestriction.getInfo() != null)
                {
                    activityEventRestrictionBinding.mSpinnerSmoking.setSelection(_StringListSmoking.indexOf(eventRestriction.getInfo()));
                }
            }

            if(eventRestriction.getRestrictions().getName().equalsIgnoreCase("Alcohol"))
            {
                if(eventRestriction.getInfo() != null)
                {
                    activityEventRestrictionBinding.mSpinnerAlcohol.setSelection(_StringListAlcohol.indexOf(eventRestriction.getInfo()));
                }
            }

            if(eventRestriction.getRestrictions().getName().equalsIgnoreCase("Age"))
            {
                if(eventRestriction.getInfo() != null)
                {
                    if(eventRestriction.getInfo().contains("+"))
                    {
                        activityEventRestrictionBinding.mSpinnerAge.setSelection(_StringListAge.indexOf("Greater Then"));
                        activityEventRestrictionBinding.mEditTextAge.setText(eventRestriction.getInfo().replace("+",""));
                    }
                    else
                    {
                        activityEventRestrictionBinding.mSpinnerAge.setSelection(_StringListAge.indexOf(eventRestriction.getInfo()));
                    }

                }
            }
        }
    }

    public ArrayAdapter<String> StringArrayAdapter(List<String> list)
    {
        return new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list);
    }

    @Override
    public void onClick(View view) {


        try {

            for (EventRestriction restriction : _RestrictionList)
            {
                if(restriction.getRestrictions().getName().equalsIgnoreCase("Alcohol"))
                {
                    restriction.setInfo((String) activityEventRestrictionBinding.mSpinnerAlcohol.getSelectedItem());
                }

                if(restriction.getRestrictions().getName().equalsIgnoreCase("Age"))
                {
                    if(activityEventRestrictionBinding.mSpinnerAge.getSelectedItemPosition() > 0)
                    {
                        restriction.setInfo((String) activityEventRestrictionBinding.mEditTextAge.getText().toString().trim() + "+");
                    }
                    else
                    {
                        restriction.setInfo((String) activityEventRestrictionBinding.mSpinnerAge.getSelectedItem());
                    }
                }

                if(restriction.getRestrictions().getName().equalsIgnoreCase("Smoking"))
                {
                    restriction.setInfo((String) activityEventRestrictionBinding.mSpinnerAlcohol.getSelectedItem());
                }
            }

            ApplicationModel.getEventValidation().IsRestrictionValid = true;
            ApplicationModel.event.setEventRestrictions(_RestrictionList);

            finish();

        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, "Position:"+i +" "+l, Toast.LENGTH_SHORT).show();
        activityEventRestrictionBinding.mEditTextAge.setEnabled(!(i == 0));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}