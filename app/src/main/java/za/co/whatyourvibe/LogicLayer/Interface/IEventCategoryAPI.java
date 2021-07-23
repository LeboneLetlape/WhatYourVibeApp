package za.co.whatyourvibe.LogicLayer.Interface;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import za.co.whatyourvibe.LogicLayer.Models.Event;
import za.co.whatyourvibe.LogicLayer.Models.EventCategory;

public interface IEventCategoryAPI{


    @GET("Category")
    Call<List<EventCategory>> GetEventCategory();

}
