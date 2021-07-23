package za.co.whatyourvibe.PresentationLayer.Fragments.EventMVP;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import za.co.whatyourvibe.LogicLayer.Models.Event;

public class EventsFragViewModel extends ViewModel {

    private  MutableLiveData<List<Event>> events;

    public LiveData<List<Event>> GetEvents()
    {
        if(events == null){
            events = onGetEvents();
        }
        return events;
    }

    private MutableLiveData<List<Event>> onGetEvents()
    {
        MutableLiveData<List<Event>> user = new MutableLiveData<>();
        user.setValue(null);
        return user;
    }


}
