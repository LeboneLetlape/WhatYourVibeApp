package za.co.whatyourvibe.LogicLayer.Models;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ProgressBar;

public class ApplicationModel extends Application {

    public static EventValidation _EventValidation = new EventValidation();


    public static Event event = new Event();
    public static User user = new User();
    public static ProgressDialog progressDialog;
    private static Context _ApplicationContext;
    private static Context _ActivityContext;



    public static Event getEvent() {
        return event;
    }

    public static void setEvent(Event event) {
        ApplicationModel.event = event;
    }

    public static EventValidation getEventValidation() {
        return _EventValidation;
    }

    public static void setEventValidation(EventValidation _EventValidation) {
        ApplicationModel._EventValidation = _EventValidation;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public static Context GetApplicationContext()
    {
        return _ApplicationContext;
    }

    public static Context getActivityContext() {
        return _ActivityContext;
    }

    public static void setActivityContext(Context _ActivityContext) {
        ApplicationModel._ActivityContext = _ActivityContext;
        progressDialog = new ProgressDialog(_ActivityContext);
    }
}
