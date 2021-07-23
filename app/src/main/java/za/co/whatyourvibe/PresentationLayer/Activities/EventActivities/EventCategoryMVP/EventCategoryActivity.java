package za.co.whatyourvibe.PresentationLayer.Activities.EventActivities.EventCategoryMVP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import za.co.whatyourvibe.LogicLayer.Interface.IEventAPI;
import za.co.whatyourvibe.LogicLayer.Interface.IEventCategoryAPI;
import za.co.whatyourvibe.LogicLayer.Models.ApplicationModel;
import za.co.whatyourvibe.LogicLayer.Models.Event;
import za.co.whatyourvibe.LogicLayer.Models.EventCategory;
import za.co.whatyourvibe.LogicLayer.Models.RetrofitClient;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.ActivityEventCategoryBinding;

public class EventCategoryActivity extends AppCompatActivity implements View.OnClickListener {


    ActivityEventCategoryBinding eventCategoryBinding;
    EventCategory _SelectedEventCategory;
    List<EventCategory> _ListEventCategory;
    Event _Event;
    IEventCategoryAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventCategoryBinding = DataBindingUtil.setContentView(this, R.layout.activity_event_category);

        ActionBar mActionBar = getSupportActionBar();

        mActionBar.setTitle("Event Category");
        mActionBar.setDisplayHomeAsUpEnabled(true);

        _ListEventCategory = new ArrayList<>();

        _Event = ApplicationModel.getEvent();

        onLoadCategory();

        if(_Event.getEventCategory() != null)
        {
            eventCategoryBinding.mSpinnerCategory.setSelection(_ListEventCategory.indexOf(_Event.getEventCategory()));
        }

        eventCategoryBinding.mButtonDone.setOnClickListener(this);
    }

    private void onLoadCategory()
    {
        api = RetrofitClient.RetrofitClient().create(IEventCategoryAPI.class);

        api.GetEventCategory().enqueue(new Callback<List<EventCategory>>() {
            @Override
            public void onResponse(Call<List<EventCategory>> call, Response<List<EventCategory>> response) {

                _ListEventCategory = response.body();
                eventCategoryBinding.mSpinnerCategory.setAdapter(EventCategoryArrayAdapter(_ListEventCategory));
            }

            @Override
            public void onFailure(Call<List<EventCategory>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {

        if(eventCategoryBinding.mSpinnerCategory.getSelectedItem() != null)
        {
            _SelectedEventCategory = (EventCategory)eventCategoryBinding.mSpinnerCategory.getSelectedItem();
            ApplicationModel.event.setEventCategory(_SelectedEventCategory);
            ApplicationModel.getEventValidation().IsCategoryValid = true;
            finish();
        }
        else
        {
            Toast.makeText(this, "Please select a category for your event", Toast.LENGTH_SHORT).show();
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


    private ArrayAdapter<EventCategory> EventCategoryArrayAdapter(List<EventCategory> eventCategories)
    {
        return new ArrayAdapter<EventCategory>(this,android.R.layout.simple_spinner_dropdown_item,eventCategories);
    }
}