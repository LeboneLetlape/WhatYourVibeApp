package za.co.whatyourvibe.PresentationLayer.Activities.EventActivities.EventPhotoMVP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import za.co.whatyourvibe.LogicLayer.Adaptor.PhotoAdaptor;
import za.co.whatyourvibe.LogicLayer.Helper.ImageHelper;
import za.co.whatyourvibe.LogicLayer.Interface.IDeleteEventImage;
import za.co.whatyourvibe.LogicLayer.Models.ApplicationModel;
import za.co.whatyourvibe.LogicLayer.Models.EventImage;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.ActivityEventPhotosBinding;

public class EventPhotosActivity extends AppCompatActivity implements View.OnClickListener, IDeleteEventImage {

    ActivityEventPhotosBinding activityEventPhotosBinding;
    List<EventImage> _EventImageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEventPhotosBinding = DataBindingUtil.setContentView(this,R.layout.activity_event_photos);

        activityEventPhotosBinding.mButtonAddPhoto.setOnClickListener(this);
        activityEventPhotosBinding.mButtonDone.setOnClickListener(this);

        _EventImageList = ApplicationModel.event.getEventImages();

        if(_EventImageList == null) {
            _EventImageList = new ArrayList<>();
        }

        ReloadRecycleView(_EventImageList);
        activityEventPhotosBinding.mRecyclerViewPhotos.setLayoutManager(new LinearLayoutManager(this));
    }

    private void ReloadRecycleView(List<EventImage> _EventImageList)
    {
        activityEventPhotosBinding.mRecyclerViewPhotos.setAdapter(new PhotoAdaptor(this,_EventImageList));
    }

    @Override
    public void onClick(View view) {

        if(activityEventPhotosBinding.mButtonAddPhoto.getId() == view.getId())
        {
            CropImage.activity().start(this);
        }

        if(activityEventPhotosBinding.mButtonDone.getId() == view.getId())
        {
            if(ApplicationModel.getEventValidation().IsMainPhotoValid)
            {
                if(_EventImageList.size() > 2) {
                    onSaveData();
                }
                else
                {
                    Toast.makeText(this, "Please upload atleast one photo", Toast.LENGTH_SHORT).show();
                }

            }
            else
            {
                if(_EventImageList.size() > 1)
                {
                    onSaveData();
                }
                else
                {
                    Toast.makeText(this, "Please upload atleast one photo", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    private void onSaveData()
    {
        ApplicationModel.getEventValidation().IsPhotoValid = true;
        ApplicationModel.getEvent().setEventImages(_EventImageList);
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);

                    _EventImageList.add(new EventImage(ImageHelper.FileName(resultUri),ImageHelper.ConvertBitmapToByteArray(bitmap),bitmap));

                    ReloadRecycleView(_EventImageList);

                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                }
            }
        } catch (IOException e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void RemoveEventImage(EventImage eventImage) {
        _EventImageList.remove(eventImage);
        ReloadRecycleView(_EventImageList);
    }
}

