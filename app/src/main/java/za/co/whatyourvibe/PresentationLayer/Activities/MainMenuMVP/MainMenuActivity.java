package za.co.whatyourvibe.PresentationLayer.Activities.MainMenuMVP;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import za.co.whatyourvibe.LogicLayer.Models.ApplicationModel;
import za.co.whatyourvibe.LogicLayer.Models.RetrofitClient;
import za.co.whatyourvibe.LogicLayer.Models.User;
import za.co.whatyourvibe.LogicLayer.Models.UserImage;
import za.co.whatyourvibe.R;

public class MainMenuActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    ImageView mCircleImageViewProfile;
    TextView mTextViewUsername;
    User _User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_profile,
                R.id.nav_home, R.id.nav_about,R.id.nav_all_events,R.id.nav_contact,R.id.nav_trending_events)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View header = navigationView.getHeaderView(0);
        mCircleImageViewProfile = header.findViewById(R.id.mCircleImageViewProfile);
        mTextViewUsername = header.findViewById(R.id.mTextViewUser);

        _User = ApplicationModel.user;
        onSetImage(_User.getUserImage());

        mTextViewUsername.setText("Lebone Letlape");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void onSetImage(UserImage userImage)
    {
        if(userImage.getUrl() != null)
        {
            Glide.with(this)
                    .load(RetrofitClient._StringWebUrl + userImage.getUrl())
                    .into(mCircleImageViewProfile);
        }
        else
        {
            Toast.makeText(this, "Image not loaded", Toast.LENGTH_SHORT).show();
        }
    }
}