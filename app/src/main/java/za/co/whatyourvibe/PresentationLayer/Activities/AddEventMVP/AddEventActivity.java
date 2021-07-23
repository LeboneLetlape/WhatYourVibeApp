package za.co.whatyourvibe.PresentationLayer.Activities.AddEventMVP;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import za.co.whatyourvibe.LogicLayer.Helper.DateTimeHelper;
import za.co.whatyourvibe.LogicLayer.Helper.DialogHelper;
import za.co.whatyourvibe.LogicLayer.Helper.ImageHelper;
import za.co.whatyourvibe.LogicLayer.Interface.IEventAPI;
import za.co.whatyourvibe.LogicLayer.Models.ApiResponse;
import za.co.whatyourvibe.LogicLayer.Models.ApplicationModel;
import za.co.whatyourvibe.LogicLayer.Models.Event;
import za.co.whatyourvibe.LogicLayer.Models.EventContact;
import za.co.whatyourvibe.LogicLayer.Models.EventImage;
import za.co.whatyourvibe.LogicLayer.Models.EventRestriction;
import za.co.whatyourvibe.LogicLayer.Models.EventSocialMedia;
import za.co.whatyourvibe.LogicLayer.Models.EventTicket;
import za.co.whatyourvibe.LogicLayer.Models.EventValidation;
import za.co.whatyourvibe.LogicLayer.Models.RetrofitClient;
import za.co.whatyourvibe.LogicLayer.Models.SocialMedia;
import za.co.whatyourvibe.PresentationLayer.Activities.EventActivities.EventCategoryMVP.EventCategoryActivity;
import za.co.whatyourvibe.PresentationLayer.Activities.EventActivities.EventContactMVP.EventContactActivity;
import za.co.whatyourvibe.PresentationLayer.Activities.EventActivities.EventDateMVP.EventDateActivity;
import za.co.whatyourvibe.PresentationLayer.Activities.EventActivities.EventDescriptionActivity.EventDescriptionActivity;
import za.co.whatyourvibe.PresentationLayer.Activities.EventActivities.EventLocationMVP.EventLocationActivity;
import za.co.whatyourvibe.PresentationLayer.Activities.EventActivities.EventPhotoMVP.EventPhotosActivity;
import za.co.whatyourvibe.PresentationLayer.Activities.EventActivities.EventRestrictionMVP.EventRestrictionActivity;
import za.co.whatyourvibe.PresentationLayer.Activities.EventActivities.EventSocialMediaMVP.EventSocialActivity;
import za.co.whatyourvibe.PresentationLayer.Activities.EventActivities.EventTicketMVP.EventTicketActivity;
import za.co.whatyourvibe.PresentationLayer.Activities.EventActivities.EventTimeMVP.EventTimeActivity;
import za.co.whatyourvibe.PresentationLayer.Activities.EventActivities.EventTitleMVP.EventTitleActivity;
import za.co.whatyourvibe.PresentationLayer.Activities.EventActivities.EventVideoMVP.EventVideoActivity;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.ActivityAddEventBinding;

public class AddEventActivity extends AppCompatActivity implements View.OnClickListener, Callback<ApiResponse> {

    ActivityAddEventBinding activityAddEventBinding;
    Event event = new Event();
    List<View> ViewOnclickList;
    List<EventImage> _EventImageList;
    IEventAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {

            ApplicationModel.event = new Event();
            ApplicationModel.setActivityContext(this);

            activityAddEventBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_event);

            ActionBar mActionBar = getSupportActionBar();

            mActionBar.setTitle("Add Event");
            mActionBar.setDisplayHomeAsUpEnabled(true);

            api = RetrofitClient.RetrofitClient().create(IEventAPI.class);

            DialogHelper.onShowProgressDialog("Please wait...");
            api.NewEvent().enqueue(new Callback<Event>() {
                @Override
                public void onResponse(Call<Event> call, Response<Event> response) {
                    onLoadView();
                    DialogHelper.onHideProgressDialog();
                    ApplicationModel.setEvent(response.body());
                }

                @Override
                public void onFailure(Call<Event> call, Throwable t) {
                    DialogHelper.onShowMessage(t.getMessage());
                    DialogHelper.onHideProgressDialog();
                }
            });


        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void onLoadView()
    {
        ViewOnclickList = new ArrayList<>();
        _EventImageList = new ArrayList<>();

        ViewOnclickList.add(activityAddEventBinding.mImageViewTitle);
        ViewOnclickList.add(activityAddEventBinding.mImageViewDescription);
        ViewOnclickList.add(activityAddEventBinding.mImageViewLocation);
        ViewOnclickList.add(activityAddEventBinding.mImageViewTime);
        ViewOnclickList.add(activityAddEventBinding.mImageViewContact);
        ViewOnclickList.add(activityAddEventBinding.mImageViewPhotos);
        ViewOnclickList.add(activityAddEventBinding.mImageViewRestrictions);
        ViewOnclickList.add(activityAddEventBinding.mImageViewSocialMedia);
        ViewOnclickList.add(activityAddEventBinding.mImageViewTickets);
        ViewOnclickList.add(activityAddEventBinding.mImageViewVidoes);
        ViewOnclickList.add(activityAddEventBinding.mImageViewDate);
        ViewOnclickList.add(activityAddEventBinding.mImageViewCategory);
        ViewOnclickList.add(activityAddEventBinding.mImageButtonImage);
        ViewOnclickList.add(activityAddEventBinding.mButtonSave);

        SetViewOnClick(ViewOnclickList);
    }


    private void SetViewOnClick(List<View> ViewOnclickList)
    {
        for (int x = 0;x<ViewOnclickList.size();x++)
        {
            ViewOnclickList.get(x).setOnClickListener(this);
        }
    }

    private void StartActivity(Context context,Class aClass)
    {
        startActivity(new Intent(context,aClass));
    }

    @Override
    public void onClick(View view) {
        try {
            if (activityAddEventBinding.mImageViewTitle.getId() == view.getId())
                StartActivity(this, EventTitleActivity.class);

            if (activityAddEventBinding.mImageViewCategory.getId() == view.getId())
                StartActivity(this, EventCategoryActivity.class);

            if (activityAddEventBinding.mImageViewDescription.getId() == view.getId())
                StartActivity(this, EventDescriptionActivity.class);

            if (activityAddEventBinding.mImageViewLocation.getId() == view.getId())
                StartActivity(this, EventLocationActivity.class);

            if (activityAddEventBinding.mImageViewTime.getId() == view.getId())
                StartActivity(this, EventTimeActivity.class);

            if (activityAddEventBinding.mImageViewContact.getId() == view.getId())
                StartActivity(this, EventContactActivity.class);

            if (activityAddEventBinding.mImageViewPhotos.getId() == view.getId())
                StartActivity(this, EventPhotosActivity.class);

            if (activityAddEventBinding.mImageViewRestrictions.getId() == view.getId())
                StartActivity(this, EventRestrictionActivity.class);

            if (activityAddEventBinding.mImageViewSocialMedia.getId() == view.getId())
                StartActivity(this, EventSocialActivity.class);

            if (activityAddEventBinding.mImageViewTickets.getId() == view.getId())
                StartActivity(this, EventTicketActivity.class);

            if (activityAddEventBinding.mImageViewVidoes.getId() == view.getId())
                StartActivity(this, EventVideoActivity.class);

            if (activityAddEventBinding.mImageViewDate.getId() == view.getId())
                StartActivity(this, EventDateActivity.class);

            if (activityAddEventBinding.mImageButtonImage.getId() == view.getId())
                CropImage.activity().start(this);

            if(activityAddEventBinding.mButtonSave.getId() == view.getId())
            {
                if(ApplicationModel.getEventValidation().IsValid())
                {
                    event = ApplicationModel.getEvent();
                    onSaveEvent(event);
                }
                else
                {
                    String _StringValidationMessage =  ApplicationModel._EventValidation.ValidationMessage();
                    Toast.makeText(this, _StringValidationMessage, Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
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

    private void onSaveEvent(Event _Event)
    {
        try {
            IEventAPI api = RetrofitClient.RetrofitClient().create(IEventAPI.class);
            //api.InsertEvent(_Event).enqueue(this);
            new AddEvent(api,_Event,this).execute();
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EventImage eventImage = null;int index = 0;
        try {
            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);

                    if(ApplicationModel.event.getEventImages() == null)
                    {
                        if(_EventImageList == null)
                        {
                            _EventImageList = new ArrayList<>();
                        }
                    }
                    else
                    {
                        _EventImageList = ApplicationModel.event.getEventImages();
                    }

                    for (EventImage eventImageObj : _EventImageList)
                    {
                        if(eventImageObj.isMain())
                        {
                            eventImage = eventImageObj;
                        }
                    }

                    if(eventImage == null)
                    {

                        eventImage = new EventImage(ImageHelper.FileName(resultUri),ImageHelper.ConvertBitmapToByteArray(bitmap) ,bitmap);
                        eventImage.setMain(true);
                        _EventImageList.add(eventImage);
                    }
                    else
                    {
                        _EventImageList.set(index,eventImage);
                    }

                    ApplicationModel._EventValidation.IsMainPhotoValid = true;
                    ApplicationModel.getEvent().setEventImages(_EventImageList);
                    activityAddEventBinding.mImageViewMainImage.setImageBitmap(bitmap);

                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                }
            }

        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
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
        if(event.getTitle() != null)
        {
            activityAddEventBinding.mTextViewTitle.setText(event.getTitle());
        }

        if(event.getFromDate() != null && event.getToDate() != null)
        {
            activityAddEventBinding.mTextViewDate.setText(DateTimeHelper.GetFormatedDate(event.getFromDate()) +" - "+ DateTimeHelper.GetFormatedDate(event.getToDate()));
        }

        if(event.getLocation() != null)
        {
            activityAddEventBinding.mTextViewLocation.setText(event.getLocation());
        }

        if(event.getDescription() != null)
        {
            activityAddEventBinding.mTextViewDescription.setText(event.getDescription());
        }

        if(event.getEventCategory() != null)
        {
            activityAddEventBinding.mTextViewCategory.setText(event.getEventCategory().toString());
        }

        if(event.getEventTickets() != null)
        {
            String _StringTickets = "";
            for (EventTicket eventTicket  : event.getEventTickets())
            {
                if(eventTicket.isActive())
                {
                    _StringTickets += (_StringTickets.isEmpty()) ? "" : " : ";
                    _StringTickets += eventTicket. getTicket().getName();
                }
            }
            activityAddEventBinding.mTextViewTickets.setText(_StringTickets);
        }


        if(event.getFromTime() != null && event.getToTime() != null)
        {
            activityAddEventBinding.mTextViewTime.setText(event.getFromTime()+" - "+event.getToTime());
        }

        if(event.getEventSocialMedia()!= null)
        {
            String _SocialMedia = "";
            for (EventSocialMedia socialMedia : event.getEventSocialMedia())
            {
                if(socialMedia.getLink()!= null)
                    _SocialMedia += socialMedia.getSocialMedia().getName()+ "; ";
            }

            activityAddEventBinding.mTextViewSocialMedia.setText(_SocialMedia);
        }

        if(event.getEventContacts()!= null)
        {
            String _Contact = "";
            for (EventContact eventContact : event.getEventContacts())
            {
                if(eventContact.getInfo()!= null)
                    _Contact += eventContact.getContact().getName()+ "; ";
            }

            activityAddEventBinding.mTextViewContact.setText(_Contact);
        }

        if(event.getEventImages()!= null)
        {
            activityAddEventBinding.mTextViewPhotos.setText("Photos:"+event.getEventImages().size());
        }

        if(event.getEventRestrictions() != null)
        {
            String _Restrictions = "";
            for (EventRestriction restriction : event.getEventRestrictions())
            {
                _Restrictions += restriction.getRestrictions().getName() +":" +restriction.getInfo()+ "; ";
            }
            activityAddEventBinding.mTextViewRestrictions.setText(_Restrictions);
        }

        if(event.getVideoUrl() != null)
        {
            activityAddEventBinding.mTextViewVidoes.setText("Video Link: "+event.getVideoUrl());
        }
    }

    @Override
    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

        if(response.body().isSuccess())
        {
            Toast.makeText(AddEventActivity.this, "Event Saved Successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        {
            Toast.makeText(AddEventActivity.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<ApiResponse> call, Throwable t) {
        Toast.makeText(AddEventActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
    }
}

class AddEvent extends AsyncTask<Void, Void, Void> {

    IEventAPI api;
    Event _Event;
    Activity activity;

    public AddEvent(IEventAPI api, Event _Event, Activity activity) {
        this.api = api;
        this._Event = _Event;
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        api.InsertEvent(_Event).enqueue((Callback<ApiResponse>) activity);
        return null;
    }
}