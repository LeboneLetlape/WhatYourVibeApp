package za.co.whatyourvibe.LogicLayer.Models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import za.co.whatyourvibe.R;

public class RetrofitClient {

    public static String _StringIPAddress = "156.38.171.162";
    public static String _StringAPIUrl = "http://"+_StringIPAddress+"/plesk-site-preview/www.whatsyourvibe.co.za/api/";
    public static String _StringWebUrl = "http://"+_StringIPAddress+"/plesk-site-preview/www.whatsyourvibe.co.za/";



    public static String _StringLocalIPAddress = "192.168.43.68";
    public static String _StringLocalAPIUrl = "http://"+_StringLocalIPAddress+"/WhatsYourVibeWeb/api/";
    public static String _StringLocalWebUrl = "http://"+_StringLocalIPAddress+"/WhatsYourVibeWeb/api/";

    public static Retrofit RetrofitClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.MINUTES).callTimeout(100,TimeUnit.MINUTES)
                .readTimeout(100,TimeUnit.MINUTES).build();

        Gson gson = new GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(_StringLocalAPIUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit;
    }
}
