package za.co.whatyourvibe.PresentationLayer.Fragments.EventMVP;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import za.co.whatyourvibe.LogicLayer.Interface.IEventCategoryAPI;
import za.co.whatyourvibe.LogicLayer.Models.EventCategory;
import za.co.whatyourvibe.LogicLayer.Models.RetrofitClient;

public class EventsFragModel {

    public void onLoadEvents(){

        IEventCategoryAPI mIEventCategoryAPI = RetrofitClient.RetrofitClient().create(IEventCategoryAPI.class);
        mIEventCategoryAPI.GetEventCategory().enqueue(new Callback<List<EventCategory>>() {
            @Override
            public void onResponse(Call<List<EventCategory>> call, Response<List<EventCategory>> response) {

            }

            @Override
            public void onFailure(Call<List<EventCategory>> call, Throwable t) {

            }
        });

    }
}
