package za.co.whatyourvibe.LogicLayer.Interface;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import za.co.whatyourvibe.LogicLayer.Models.ApiResponse;
import za.co.whatyourvibe.LogicLayer.Models.Event;

public interface IEventAPI {

    @GET("GetEventByUserID/{id}")
    Call<List<Event>> GetEventById(@Path("id") Integer id);

    @GET("Event")
    Call<List<Event>> GetEvent();

    @POST("Event")
    Call<ApiResponse> InsertEvent(@Body Event event);

    @GET("Event")
    Call<List<Event>> SelectAll();

    @GET("NewEvent")
    Call<Event> NewEvent();

}


interface IUserImageAPI{}

interface ISocialMediaAPI{}
interface IEventContactAPI{}

interface IEventMainImageAPI{}

interface ICategoryImageAPI{}

interface IEventTypeAPI{}

interface IEventCheckinAPI{}

interface IEventRateAPI{}
