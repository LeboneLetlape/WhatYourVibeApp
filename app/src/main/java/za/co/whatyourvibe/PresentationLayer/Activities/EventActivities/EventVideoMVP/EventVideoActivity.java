package za.co.whatyourvibe.PresentationLayer.Activities.EventActivities.EventVideoMVP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import za.co.whatyourvibe.LogicLayer.Models.ApplicationModel;
import za.co.whatyourvibe.LogicLayer.Models.Event;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.ActivityEventVideoBinding;

public class EventVideoActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityEventVideoBinding activityEventVideoBinding;
    Event _Event;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityEventVideoBinding = DataBindingUtil.setContentView(this,R.layout.activity_event_video);
        ActionBar mActionBar = getSupportActionBar();

        mActionBar.setTitle("Event Video");
        mActionBar.setDisplayHomeAsUpEnabled(true);

        _Event = ApplicationModel.getEvent();

        if(_Event.getVideoUrl() != null)
        {
            if(!_Event.getVideoUrl().equalsIgnoreCase(""))
            {
                activityEventVideoBinding.mEditTextVideoLink.setText(_Event.getVideoUrl());
            }
        }

        activityEventVideoBinding.mButtonValidate.setOnClickListener(this);
        activityEventVideoBinding.mButtonDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String _VideoLink = activityEventVideoBinding.mEditTextVideoLink.getText().toString().trim();
        if (activityEventVideoBinding.mButtonValidate.getId() == view.getId())
        {
            if (!_VideoLink.isEmpty())
            {
                if(Patterns.WEB_URL.matcher(_VideoLink).matches())
                {
                    String _StringVideoID = GetVideoID(_VideoLink);
                    if(!_StringVideoID.equalsIgnoreCase(""))
                    {
                        getLifecycle().addObserver(activityEventVideoBinding.mVideoView);
                        activityEventVideoBinding.mVideoView.setEnableAutomaticInitialization(false);
                        activityEventVideoBinding.mVideoView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                            @Override
                            public void onReady(@NonNull YouTubePlayer mYouTubePlayer) {
                                mYouTubePlayer.loadVideo(_StringVideoID, 0);
                                mYouTubePlayer.play();
                            }
                        });
                        activityEventVideoBinding.mButtonValidate.setVisibility(View.GONE);
                        activityEventVideoBinding.mButtonDone.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        Toast.makeText(this, "Invalid Link", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(this, "Invalid Link", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this, "Invalid Link", Toast.LENGTH_SHORT).show();
            }
        }

        if (activityEventVideoBinding.mButtonDone.getId() == view.getId())
        {
            ApplicationModel.getEventValidation().IsVideoValid = true;
            _Event.setVideoUrl(_VideoLink);
            ApplicationModel.setEvent(_Event);
            finish();
        }


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private String GetVideoID(String _URL)
    {
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(_URL); //url is youtube url for which you want to extract the id.
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }
}