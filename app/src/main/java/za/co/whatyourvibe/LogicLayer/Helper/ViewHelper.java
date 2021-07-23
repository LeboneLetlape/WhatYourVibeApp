package za.co.whatyourvibe.LogicLayer.Helper;

import android.content.Context;
import android.view.View;

import java.util.List;

import za.co.whatyourvibe.PresentationLayer.Activities.EventActivities.EventTimeMVP.EventTimeActivity;

public class ViewHelper {


    public static void SetViewOnClick(View.OnClickListener listener, List<View> ViewOnclickList)
    {
        for (int x = 0;x<ViewOnclickList.size();x++)
        {
            ViewOnclickList.get(x).setOnClickListener(listener);
        }
    }
}
