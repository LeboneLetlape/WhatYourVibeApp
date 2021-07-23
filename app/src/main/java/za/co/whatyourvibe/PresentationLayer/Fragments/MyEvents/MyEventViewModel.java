package za.co.whatyourvibe.PresentationLayer.Fragments.MyEvents;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import za.co.whatyourvibe.LogicLayer.Models.Event;

public class MyEventViewModel extends ViewModel {
    private MutableLiveData<List<Event>> user;

    public LiveData<List<Event>> GetUsers()
    {
        if(user == null){
            user = onGetEvents();
        }
        return user;
    }

    private MutableLiveData<List<Event>> onGetEvents()
    {
        MutableLiveData<List<Event>> user = new MutableLiveData<>();
        user.setValue(null);
        return user;
    }

}
