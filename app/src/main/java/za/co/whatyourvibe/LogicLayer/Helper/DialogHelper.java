package za.co.whatyourvibe.LogicLayer.Helper;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.google.firestore.v1.TargetOrBuilder;

import za.co.whatyourvibe.LogicLayer.Models.ApplicationModel;

public class DialogHelper {

    public static void onShowMessage(String message)
    {
        Toast.makeText(ApplicationModel.getActivityContext(), message, Toast.LENGTH_LONG).show();
    }

    public static void onShowProgressDialog(String message){
        ApplicationModel.progressDialog.setMessage(message);
        ApplicationModel.progressDialog.show();
    }

    public static void onHideProgressDialog(){
        ApplicationModel.progressDialog.hide();
    }
}
