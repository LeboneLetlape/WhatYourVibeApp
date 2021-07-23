package za.co.whatyourvibe.PresentationLayer.Activities.EventActivities.EventSocialMediaMVP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import za.co.whatyourvibe.LogicLayer.Interface.IView;
import za.co.whatyourvibe.LogicLayer.Models.ApplicationModel;
import za.co.whatyourvibe.LogicLayer.Models.EventSocialMedia;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.ActivityEventSocialBinding;

public class EventSocialActivity extends AppCompatActivity implements View.OnClickListener, IView {

    ActivityEventSocialBinding activityEventSocialBinding;
    String _TweeterLink,_FacebookLink,_InstagramLink;
    List<EventSocialMedia> _SocialMediaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEventSocialBinding = DataBindingUtil.setContentView(this,R.layout.activity_event_social);

        getSupportActionBar().setTitle("Event Social");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        _SocialMediaList = ApplicationModel.event.getEventSocialMedia();


        if(_SocialMediaList == null)
        {
            _SocialMediaList = new ArrayList<>();
            _SocialMediaList.add(new EventSocialMedia(1,"Facebook"));
            _SocialMediaList.add(new EventSocialMedia(2,"Tweeter"));
            _SocialMediaList.add(new EventSocialMedia(3,"Instagram"));
        }

        activityEventSocialBinding.mButtonDone.setOnClickListener(this);
        onSetView();
    }

    private void onSetView()
    {
        for (EventSocialMedia eventSocialMedia : _SocialMediaList)
        {
            if(eventSocialMedia.socialMedia.getName().equalsIgnoreCase("Facebook"))
            {
                if (eventSocialMedia.getLink() != null)
                {
                    activityEventSocialBinding.mTextViewFacebookLink.setText(eventSocialMedia.getLink());
                }
            }

            if(eventSocialMedia.socialMedia.getName().equalsIgnoreCase("Tweeter"))
            {
                if (eventSocialMedia.getLink() != null)
                {
                    activityEventSocialBinding.mTextViewTweeterLink.setText(eventSocialMedia.getLink());
                }
            }

            if(eventSocialMedia.socialMedia.getName().equalsIgnoreCase("Instagram"))
            {
                if (eventSocialMedia.getLink() != null)
                {
                    activityEventSocialBinding.mTextViewInstagramLink.setText(eventSocialMedia.getLink());
                }
            }
        }
    }

    @Override
    public void onClick(View view) {

        _TweeterLink = GetString(activityEventSocialBinding.mTextViewTweeterLink);
        _FacebookLink = GetString(activityEventSocialBinding.mTextViewFacebookLink);
        _InstagramLink = GetString(activityEventSocialBinding.mTextViewInstagramLink);

        if(!_FacebookLink.isEmpty() && !_TweeterLink.isEmpty() && !_InstagramLink.isEmpty())
        {

            _SocialMediaList.get(0).setLink(_FacebookLink);
            _SocialMediaList.get(1).setLink(_TweeterLink);
            _SocialMediaList.get(2).setLink(_InstagramLink);

            ApplicationModel.getEventValidation().IsSocialMediaValid = true;
            ApplicationModel.event.setEventSocialMedia(_SocialMediaList);

            showMessage("Saved successfully");
            finish();
        }
        else {
            String _validationMessage = "Please enter your "
                    + ((_TweeterLink.isEmpty()) ? " Tweeter Link " : "")
                    + ((_FacebookLink.isEmpty()) ? " Facebook Link " : "")
                    + ((_InstagramLink.isEmpty()) ? " Instagram Link " : "");

            showMessage(_validationMessage);
        }
    }


    private String GetString(EditText mEditText)
    {
        return mEditText.getText().toString().trim();
    }

    @Override
    public void showMessage(String _message) {
        Toast.makeText(this, _message, Toast.LENGTH_SHORT).show();
    }
}