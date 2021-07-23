package za.co.whatyourvibe.PresentationLayer.Fragments.AboutMVP;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import za.co.whatyourvibe.LogicLayer.Models.About;
import za.co.whatyourvibe.PresentationLayer.Activities.AboutMVP.AboutDetailActivity;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.FragmentAboutBinding;
import za.co.whatyourvibe.databinding.ViewAboutItemBinding;

public class AboutFragment extends Fragment {

    public AboutFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        FragmentAboutBinding fragmentAboutBinding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()),
                R.layout.fragment_about, container, false);

        fragmentAboutBinding.mRecyclerViewAbouts.setLayoutManager(new LinearLayoutManager(getContext()));

        List<About> abouts = new ArrayList<>();



        About aboutGettingStarted = new About();

        aboutGettingStarted.setTitle("Get Started");
        aboutGettingStarted.setDescription("Android apps can be a great, fun way to get into the world of programming. Officially programmers can use Java, Kotlin, or C++ to develop for Android. Though there may be API restrictions, using certain tools developers can use a large number of languages, including JavaScript, C, or assembly. The possibilities are endless.\n" +
                "\n" +
                "From simple games and utility apps to full-blown music players, there are many opportunities to create something meaningful with Android. The Android developer community is widespread, and the documentation and resources online are easy to find, so that you can tackle any issue you’re facing.\n" +
                "\n" +
                "There is definitely a learning curve to get used to the Android framework. But once you understand the core components that make up the app, the rest will come naturally.\n" +
                "\n" +
                "The learning curve involved in Android has a relatively smaller slope compared to learning other technologies such as NodeJS. It is also relatively easier to understand and make contributions towards AOSP hosted by Google. The project can be found here.");


        About aboutCOVID = new About();
        aboutCOVID.setTitle("What is COVID-19");
        aboutCOVID.setDescription("On 31 December 2019, the World Health Organization (WHO) reported a cluster of pneumonia cases in Wuhan City, China. ‘Severe Acute Respiratory Syndrome Coronavirus 2’ (SARS-CoV-2) was confirmed as the causative agent of what we now know as ‘Coronavirus Disease 2019’ (COVID-19). Since then, the virus has spread to more than 100 countries, including South Africa.");

        abouts.add(aboutGettingStarted);
        abouts.add(aboutCOVID);

        fragmentAboutBinding.mRecyclerViewAbouts.setAdapter(new ViewAboutAdapter(getActivity(),abouts));
        return fragmentAboutBinding.getRoot();
    }
}

class ViewAboutAdapter extends RecyclerView.Adapter<ViewAboutAdapter.ViewHolderModel> {

    Activity activity;
    List<About> mData;

    public ViewAboutAdapter(Activity activity, List<About> mData) {
        this.activity = activity;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolderModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewAboutItemBinding viewAboutItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.view_about_item,parent,false);
        return new ViewHolderModel(viewAboutItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderModel holder, int position) {
        holder.onBind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolderModel extends RecyclerView.ViewHolder{

        ViewAboutItemBinding viewAboutItemBinding;
        public ViewHolderModel(@NonNull ViewAboutItemBinding viewAboutItemBinding) {
            super(viewAboutItemBinding.getRoot());
            this.viewAboutItemBinding = viewAboutItemBinding;
        }

        public void onBind(final About about)
        {
            viewAboutItemBinding.mTextViewTitle.setText(about.getTitle());
            viewAboutItemBinding.mTextViewDescription.setText(about.getDescription());
            viewAboutItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.getContext().startActivity(new Intent(view.getContext(), AboutDetailActivity.class).putExtra("SelectedAbout",about));
                }
            });
        }
    }
}

