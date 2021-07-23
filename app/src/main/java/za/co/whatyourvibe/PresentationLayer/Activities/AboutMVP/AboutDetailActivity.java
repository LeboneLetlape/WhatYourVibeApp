package za.co.whatyourvibe.PresentationLayer.Activities.AboutMVP;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import za.co.whatyourvibe.LogicLayer.Models.About;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.ActivityAboutDetailBinding;

public class AboutDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAboutDetailBinding aboutDetailBinding = DataBindingUtil.setContentView(this,R.layout.activity_about_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        About about = (About)getIntent().getSerializableExtra("SelectedAbout");
        getSupportActionBar().setTitle(about.getTitle());
        aboutDetailBinding.mTextViewDescription.setText(about.getDescription());

    }
}