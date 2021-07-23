package za.co.whatyourvibe.PresentationLayer.Activities.EventDetailMVP;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import za.co.whatyourvibe.LogicLayer.Adaptor.EventTicketsAdaptor;
import za.co.whatyourvibe.LogicLayer.Helper.DateTimeHelper;
import za.co.whatyourvibe.LogicLayer.Helper.NumericHelper;
import za.co.whatyourvibe.LogicLayer.Helper.VideoHelper;
import za.co.whatyourvibe.LogicLayer.Models.ApplicationModel;
import za.co.whatyourvibe.LogicLayer.Models.Event;
import za.co.whatyourvibe.LogicLayer.Models.EventContact;
import za.co.whatyourvibe.LogicLayer.Models.EventImage;
import za.co.whatyourvibe.LogicLayer.Models.EventRestriction;
import za.co.whatyourvibe.LogicLayer.Models.EventSocialMedia;
import za.co.whatyourvibe.LogicLayer.Models.EventTicket;
import za.co.whatyourvibe.LogicLayer.Models.RetrofitClient;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.ActivityEventDetailBinding;
import za.co.whatyourvibe.databinding.DialogRatingViewBinding;
import za.co.whatyourvibe.databinding.ViewTicketItemBinding;

public class EventDetailActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityEventDetailBinding eventDetailBinding;
    String _stringTimeFrom,_stringTimeTo,
            _stringFacebookLink,_stringTweeterLink,_stringInstagramLink,_stringPhoneInfo,_stringEmailInfo,_stringMessageInfo;
    Event event;
    AlertDialog.Builder alertDialog;
    DialogInterface dialogInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            eventDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_event_detail);


            event = ApplicationModel.event;

            eventDetailBinding.mTextViewTitle.setText(event.getTitle());
            eventDetailBinding.mTextViewDescription.setText(event.getDescription());

            eventDetailBinding.mRecyclerViewTickets.setAdapter(new EventTicketsAdaptor(this, event.getEventTickets()));

            //Date and Time
            eventDetailBinding.mTextViewDateFrom.setText(DateTimeHelper.GetFormatedDate(event.getFromDate()));
            eventDetailBinding.mTextViewDateTo.setText(DateTimeHelper.GetFormatedDate(event.getToDate()));

            _stringTimeFrom = DateTimeHelper.GetFormatedTime(DateTimeHelper.GetDateFromTime(event.getFromTime()));
            _stringTimeTo = DateTimeHelper.GetFormatedTime(DateTimeHelper.GetDateFromTime(event.getToTime()));

            eventDetailBinding.mTextViewTimeFrom.setText(_stringTimeFrom);
            eventDetailBinding.mTextViewTimeTo.setText(_stringTimeTo);

             if(event.getVideoUrl() != null)
             {
                 if(!event.getVideoUrl().isEmpty())
                 {

                     if(Patterns.WEB_URL.matcher(event.getVideoUrl()).matches())
                     {
                         String _StringVideoID = VideoHelper.GetVideoID(event.getVideoUrl());
                         if(!_StringVideoID.equalsIgnoreCase(""))
                         {
                             getLifecycle().addObserver(eventDetailBinding.mVideoViewMedia);
                             eventDetailBinding.mVideoViewMedia.setEnableAutomaticInitialization(false);
                             eventDetailBinding.mVideoViewMedia.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                                 @Override
                                 public void onReady(@NonNull YouTubePlayer mYouTubePlayer) {
                                     mYouTubePlayer.loadVideo(_StringVideoID, 0);
                                     //mYouTubePlayer.play();
                                 }
                             });
                         }
                     }
                 }
             }



            EventRestrictionConfi(event.getEventRestrictions());
            EventImagesConfi(event.getEventImages());
            EventSocialMediaConfi(event.getEventSocialMedia());


            eventDetailBinding.mLinearLayoutFacebook.setOnClickListener(this);
            eventDetailBinding.mLinearLayoutInstagram.setOnClickListener(this);
            eventDetailBinding.mLinearLayoutTweeter.setOnClickListener(this);

            eventDetailBinding.mLinearLayoutEmail.setOnClickListener(this);
            eventDetailBinding.mLinearLayoutMessage.setOnClickListener(this);
            eventDetailBinding.mLinearLayoutPhone.setOnClickListener(this);


            eventDetailBinding.mLinearLayoutVibeRate.setOnClickListener(this);
            eventDetailBinding.mLinearLayoutCheckIn.setOnClickListener(this);

            SupportMapFragment mapFragment = (SupportMapFragment) FragmentManager.findFragment(findViewById(R.id.mMapViewLocation));
            if (mapFragment != null) {
                mapFragment.getMapAsync(callback);
            }

        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng latLng = new LatLng(event.getLat(), event.getLng());
            googleMap.addMarker(new MarkerOptions().position(latLng).title(event.getTitle()));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,16.0f));
        }
    };

    private void EventImagesConfi(List<EventImage> eventImages)
    {
        List<SlideModel> imageList = new ArrayList<>();

        for (EventImage eventImage : eventImages)
        {
            if(!eventImage.isMain())
            {
                imageList.add(new SlideModel(RetrofitClient._StringWebUrl + eventImage.getUrl(), ScaleTypes.FIT));
            }
        }

        eventDetailBinding.mImageSlider.setImageList(imageList);
    }

    private void EventRestrictionConfi(List<EventRestriction> eventRestrictions)
    {
        for (EventRestriction eventRestriction:eventRestrictions)
        {
            if(eventRestriction.restrictions.getName().equalsIgnoreCase("Smoking"))
            {
                eventDetailBinding.mTextViewSmokingInfo.setText(eventRestriction.getInfo());
            }
            else if(eventRestriction.restrictions.getName().equalsIgnoreCase("Alcohol"))
            {
                eventDetailBinding.mTextViewAlcoholInfo.setText(eventRestriction.getInfo());
            }
            else if(eventRestriction.restrictions.getName().equalsIgnoreCase("Age"))
            {
                eventDetailBinding.mTextViewAgeInfo.setText(eventRestriction.getInfo());
            }
        }
    }

    private void EventContactConfi(List<EventContact> eventContacts)
    {
        for (EventContact eventContact :eventContacts)
        {
            if(eventContact.contact.getName().equalsIgnoreCase("Phone"))
            {
                _stringPhoneInfo = eventContact.getInfo();
            }
            if(eventContact.contact.getName().equalsIgnoreCase("Email"))
            {
                _stringEmailInfo = eventContact.getInfo();
            }
            if(eventContact.contact.getName().equalsIgnoreCase("Message"))
            {
                _stringMessageInfo = eventContact.getInfo();
            }
        }
    }

    private void EventSocialMediaConfi(List<EventSocialMedia> eventSocialMedia)
    {
        for (EventSocialMedia SocialMedia :eventSocialMedia)
        {
            if(SocialMedia.socialMedia.getName().equalsIgnoreCase("Facebook"))
            {
                _stringFacebookLink = SocialMedia.getLink();
            }
            if(SocialMedia.socialMedia.getName().equalsIgnoreCase("Tweeter"))
            {
                _stringTweeterLink = SocialMedia.getLink();
            }
            if(SocialMedia.socialMedia.getName().equalsIgnoreCase("Instagram"))
            {
                _stringInstagramLink = SocialMedia.getLink();
            }
        }

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == eventDetailBinding.mLinearLayoutFacebook.getId())
        {
            if(!_stringFacebookLink.isEmpty())
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(_stringFacebookLink)));
            else
                Toast.makeText(this, "Facebook account not available", Toast.LENGTH_SHORT).show();
        }
        if(view.getId() == eventDetailBinding.mLinearLayoutInstagram.getId())
        {
            if(!_stringInstagramLink.isEmpty())
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(_stringInstagramLink)));
            else
                Toast.makeText(this, "Instagram account not available", Toast.LENGTH_SHORT).show();

        }
        if(view.getId() == eventDetailBinding.mLinearLayoutTweeter.getId())
        {
            if(!_stringTweeterLink.isEmpty())
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(_stringTweeterLink)));
            else
                Toast.makeText(this, "Tweeter account not available", Toast.LENGTH_SHORT).show();
        }

        if(view.getId() == eventDetailBinding.mLinearLayoutCheckIn.getId())
        {
            Toast.makeText(this, "You have checked in successfully", Toast.LENGTH_SHORT).show();
        }

        if(view.getId() == eventDetailBinding.mLinearLayoutVibeRate.getId())
        {

            DialogRatingViewBinding dialogRatingViewBinding = DataBindingUtil.inflate(LayoutInflater.from(this),R.layout.dialog_rating_view,null,false);

            alertDialog = new AlertDialog.Builder(this);
            alertDialog.setView(dialogRatingViewBinding.getRoot());
            dialogInterface = alertDialog.show();

            dialogRatingViewBinding.mButtonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogInterface.dismiss();
                }
            });
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


    @Override
    protected void onResume() {
        super.onResume();
        UpdateView(ApplicationModel.event);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    private void UpdateView(Event event)
    {
        if(event.getFromDate() != null && event.getToDate() != null)
        {
            eventDetailBinding.mTextViewDateTo.setText(DateTimeHelper.GetFormatedDate(event.getToDate()));
            eventDetailBinding.mTextViewDateFrom.setText(DateTimeHelper.GetFormatedDate(event.getFromDate()));
        }

    }
}

