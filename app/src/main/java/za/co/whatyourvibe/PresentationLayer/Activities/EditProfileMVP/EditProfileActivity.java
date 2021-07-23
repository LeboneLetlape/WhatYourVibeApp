package za.co.whatyourvibe.PresentationLayer.Activities.EditProfileMVP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleService;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import za.co.whatyourvibe.LogicLayer.Models.Province;
import za.co.whatyourvibe.LogicLayer.Models.User;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.ActivityEditProfileBinding;

public class EditProfileActivity extends AppCompatActivity {

    ActivityEditProfileBinding activityEditProfileBinding;
    List<Province> _ProvinceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEditProfileBinding = DataBindingUtil.setContentView(this,R.layout.activity_edit_profile);

        _ProvinceList = new ArrayList<>();

    }


    public void onSetView(User _user)
    {
        activityEditProfileBinding.mEditTextUsername.setText(_user.getUsername());
        activityEditProfileBinding.mEditTextEmail.setText(_user.getEmail());
        activityEditProfileBinding.mEditTextTown.setText(_user.getTown());

        if(_user.getProvince() != null)
            activityEditProfileBinding.mSpinnerProvince.setSelection(_ProvinceList.indexOf(_user.getProvince()));

        if(_user.getGender() != null)
            activityEditProfileBinding.mSpinnerProvince.setSelection(_ProvinceList.indexOf(_user.getProvince()));
    }
}