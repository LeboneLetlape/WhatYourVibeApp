package za.co.whatyourvibe.PresentationLayer.Fragments.ProfileMVP;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import za.co.whatyourvibe.LogicLayer.Helper.ViewHelper;
import za.co.whatyourvibe.LogicLayer.Interface.IEventAPI;
import za.co.whatyourvibe.LogicLayer.Interface.IUserAPI;
import za.co.whatyourvibe.LogicLayer.Models.ApplicationModel;
import za.co.whatyourvibe.LogicLayer.Models.RetrofitClient;
import za.co.whatyourvibe.LogicLayer.Models.User;
import za.co.whatyourvibe.LogicLayer.Models.UserImage;
import za.co.whatyourvibe.PresentationLayer.Activities.EditProfileMVP.EditProfileActivity;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    FragmentProfileBinding fragmentProfileBinding;
    User _User;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentProfileBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile, container, false);
        _User = ApplicationModel.user;

        if(null != _User)
        {
            onSetUserDetails(_User);
            onSetImage(_User.getUserImage());
        }



        fragmentProfileBinding.mButtonEditProfile.setOnClickListener(this);
        return fragmentProfileBinding.getRoot();
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getContext(), EditProfileActivity.class));
    }


    private void onSetUserDetails(User user)
    {
        if(user != null)
        {
            fragmentProfileBinding.mTextViewEmail.setText(user.getEmail());
            fragmentProfileBinding.mTextViewUsername.setText(user.getUsername());
            fragmentProfileBinding.mTextViewGender.setText(user.getGender().getName());
            fragmentProfileBinding.mTextViewProvince.setText(user.getProvince().getName());
            fragmentProfileBinding.mTextViewTown.setText(user.getTown());
        }
    }

    private void onSetImage(UserImage userImage)
    {
        if(userImage.getUrl() != null)
        {
            Glide.with(this)
                    .load(RetrofitClient._StringWebUrl + userImage.getUrl())
                    .into(fragmentProfileBinding.mCircleImageViewProfile);
        }
        else
        {
            Toast.makeText(getActivity(), "Image not loaded", Toast.LENGTH_SHORT).show();
        }
    }
}