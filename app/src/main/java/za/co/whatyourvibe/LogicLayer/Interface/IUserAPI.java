package za.co.whatyourvibe.LogicLayer.Interface;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import za.co.whatyourvibe.LogicLayer.Models.Event;
import za.co.whatyourvibe.LogicLayer.Models.User;

public interface IUserAPI{

    @GET("User/{id}")
    Call<User> GetUserById(@Path("id") Integer id);

    @GET("User")
    Call<List<User>> GetUsers();

    @GET("User?")
    Call<User> GetLoginUser(@Query("email") String email, @Query("password") String password);

}
