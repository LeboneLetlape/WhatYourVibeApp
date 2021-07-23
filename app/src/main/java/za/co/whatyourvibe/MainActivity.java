package za.co.whatyourvibe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import za.co.whatyourvibe.LogicLayer.Interface.IEventAPI;
import za.co.whatyourvibe.LogicLayer.Interface.IEventCategoryAPI;
import za.co.whatyourvibe.LogicLayer.Models.Event;
import za.co.whatyourvibe.LogicLayer.Models.EventCategory;
import za.co.whatyourvibe.LogicLayer.Models.RetrofitClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {

        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }

        try {
            IEventCategoryAPI eventCategoryAPI = RetrofitClient.RetrofitClient().create(IEventCategoryAPI.class);
            eventCategoryAPI.GetEventCategory().enqueue(new Callback<List<EventCategory>>() {
                @Override
                public void onResponse(Call<List<EventCategory>> call, Response<List<EventCategory>> response) {
                    Toast.makeText(MainActivity.this, " " + response.body().size(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<List<EventCategory>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}