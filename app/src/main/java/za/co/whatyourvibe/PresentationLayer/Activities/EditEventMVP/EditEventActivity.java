package za.co.whatyourvibe.PresentationLayer.Activities.EditEventMVP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import za.co.whatyourvibe.LogicLayer.Models.ApplicationModel;
import za.co.whatyourvibe.LogicLayer.Models.Event;
import za.co.whatyourvibe.LogicLayer.Models.User;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.ActivityEditEventBinding;

public class EditEventActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityEditEventBinding mActivityEditEventBinding;
    List<View> _ViewOnclickList;
    Event _Event;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityEditEventBinding = DataBindingUtil.setContentView(this,R.layout.activity_edit_event);

        _Event = new Event();
        _ViewOnclickList = new ArrayList<>();
        _Event = ApplicationModel.getEvent();

        _ViewOnclickList.add(mActivityEditEventBinding.mImageViewDate);
        _ViewOnclickList.add(mActivityEditEventBinding.mImageViewTime);
        _ViewOnclickList.add(mActivityEditEventBinding.mImageViewTitle);
        _ViewOnclickList.add(mActivityEditEventBinding.mImageViewCategory);
        _ViewOnclickList.add(mActivityEditEventBinding.mImageViewContact);
        _ViewOnclickList.add(mActivityEditEventBinding.mImageViewDescription);
        _ViewOnclickList.add(mActivityEditEventBinding.mImageViewLocation);
        _ViewOnclickList.add(mActivityEditEventBinding.mImageViewPhotos);
        _ViewOnclickList.add(mActivityEditEventBinding.mImageViewPublish);
        _ViewOnclickList.add(mActivityEditEventBinding.mImageViewStatus);
        _ViewOnclickList.add(mActivityEditEventBinding.mImageViewTickets);
        _ViewOnclickList.add(mActivityEditEventBinding.mImageViewRestrictions);
        _ViewOnclickList.add(mActivityEditEventBinding.mImageViewSocialMedia);
        _ViewOnclickList.add(mActivityEditEventBinding.mTextViewVidoes);

        SetViewOnClick(_ViewOnclickList);

    }

    private void SetViewOnClick(List<View> ViewOnclickList)
    {
        for (int x = 0;x<ViewOnclickList.size();x++)
        {
            ViewOnclickList.get(x).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {

    }
}