package za.co.whatyourvibe.PresentationLayer.Activities.LoginActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import za.co.whatyourvibe.LogicLayer.Interface.IUserAPI;
import za.co.whatyourvibe.LogicLayer.Models.ApplicationModel;
import za.co.whatyourvibe.LogicLayer.Models.RetrofitClient;
import za.co.whatyourvibe.LogicLayer.Models.User;
import za.co.whatyourvibe.PresentationLayer.Activities.MainMenuMVP.MainMenuActivity;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.ActivityLoginBinding;

public class LoginActivityView extends AppCompatActivity implements View.OnClickListener, Callback<User> {

    ActivityLoginBinding mActivityLoginBinding;
    LoginActivityViewModel mLoginActivityViewModel;
    private FirebaseAuth mAuth;
    FirebaseUser mFirebaseUser;

    GoogleSignInClient mGoogleSignInClient;
    private GoogleApiClient googleApiClient;

    private static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        mLoginActivityViewModel =  new ViewModelProvider(this).get(LoginActivityViewModel.class);
        mLoginActivityViewModel.onSetActivity(this);


        if(mLoginActivityViewModel.onGetLoginGoogleUser() != null)
        {
            Toast.makeText(this, "User Exists", Toast.LENGTH_SHORT).show();
        }

        mActivityLoginBinding.mButtonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        try {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);


            String _StringEmail,_StringPassword;

            _StringEmail = mActivityLoginBinding.mEditTextEmail.getText().toString().trim();
            _StringPassword = mActivityLoginBinding.mEditTextPassword.getText().toString().trim();

            if(!_StringEmail.isEmpty() && !_StringPassword.isEmpty())
            {
                IUserAPI api = RetrofitClient.RetrofitClient().create(IUserAPI.class);
                api.GetLoginUser(_StringEmail,_StringPassword).enqueue(this);
            }
            else
            {
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        ApplicationModel.user = response.body();
        startActivity(new Intent(this, MainMenuActivity.class));
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        Toast.makeText(this, t.toString(), Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {

            //  updateUI();
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                handleSignInResult(task);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    private void handleSignInResult(Task completedTask) throws Throwable {
        try {
            GoogleSignInAccount account = (GoogleSignInAccount) completedTask.getResult(ApiException.class);

            Toast.makeText(this, "" + account.getId(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "" + account.getDisplayName(), Toast.LENGTH_SHORT).show();


            // Signed in successfully, show authenticated UI.
            //updateUI();

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Warning", "signInResult:failed code=" + e.getStatusCode());

        }
    }

    public void updateUI(){
        //Intent intent = new Intent(MainActivity.this, UserActivity.class);
        //startActivity(intent);
    }
}

class LoginActivityViewModel extends ViewModel {



    Activity mActivity;
    LoginActivityModel mLoginActivityModel = new LoginActivityModel();


    public void onSetActivity(Activity mActivity){
        if(mActivity == null)
            this.mActivity = mActivity;
    }

    public GoogleSignInAccount onGetLoginGoogleUser()
    {
        return GoogleSignIn.getLastSignedInAccount(mActivity);
    }

    public GoogleSignInClient GetGoogleSignInClient(){
        return mLoginActivityModel.mSetGoogleAccount(mActivity);
    }

}


class LoginActivityModel {

    GoogleSignInOptions mGoogleSignInOptions;
    GoogleSignInClient mGoogleSignInClient;

    public GoogleSignInClient mSetGoogleAccount(Activity activity) {
        mGoogleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(activity, mGoogleSignInOptions);
        return mGoogleSignInClient;
    }

    public void onSignInResult(Task completedTask, IAsyncCallBack<GoogleSignInAccount> asyncCallBack) throws Throwable {
        try {
            GoogleSignInAccount account = (GoogleSignInAccount) completedTask.getResult(ApiException.class);
            asyncCallBack.onResponse(account);
        } catch (ApiException e) {
            asyncCallBack.onException("Warning:signInResult:failed code=" + e.getStatusCode());
        }
    }

    interface IAsyncCallBack<T> {
        void onResponse(T response);

        void onException(String error);
    }
}