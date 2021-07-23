package za.co.whatyourvibe.PresentationLayer.Fragments.TrendingMVP;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import za.co.whatyourvibe.LogicLayer.Models.Category;
import za.co.whatyourvibe.LogicLayer.Models.Event;
import za.co.whatyourvibe.PresentationLayer.Activities.EventDetailMVP.EventDetailActivity;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.FragmentTrendingEventsBinding;
import za.co.whatyourvibe.databinding.ViewCategoryItemBinding;
import za.co.whatyourvibe.databinding.ViewEventItemBinding;


public class TrendingEventsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentTrendingEventsBinding fragmentTrendingEventsBinding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()),
                R.layout.fragment_trending_events, container, false);
        try {

            fragmentTrendingEventsBinding.mRecyclerViewTrending.setLayoutManager(new LinearLayoutManager(container.getContext()));
            fragmentTrendingEventsBinding.mRecyclerViewTrending.setAdapter(new ViewEventAdapter(getActivity(),Events()));
            fragmentTrendingEventsBinding.mRecyclerViewCategory.setAdapter(new ViewCategoryAdapter(getActivity(),Categories()));
        } catch (Exception e) {
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
        return fragmentTrendingEventsBinding.getRoot();
    }

    private List<Category> Categories()
    {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Sport"));
        categories.add(new Category("Conference"));
        categories.add(new Category("Expos"));
        categories.add(new Category("Concerts"));
        categories.add(new Category("Festivals"));
        categories.add(new Category("Performing Arts"));
        categories.add(new Category("Community"));
        return categories;

    }

    private List<Event> Events()
    {
        List<Event> events = new ArrayList<>();
        events.add(new Event());
        events.add(new Event());
        events.add(new Event());
        return events;

    }
}

class ViewCategoryAdapter extends  RecyclerView.Adapter<ViewCategoryAdapter.ViewHolderModel>
{
    Activity activity;
    List<Category> mData;

    public ViewCategoryAdapter(Activity activity, List<Category> mData) {
        this.activity = activity;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolderModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewCategoryItemBinding categoryItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.view_category_item,parent,false);

        return new ViewHolderModel(categoryItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderModel holder, int position) {
        holder.onBind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolderModel extends RecyclerView.ViewHolder {
        ViewCategoryItemBinding categoryItemBinding;
        public ViewHolderModel(@NonNull ViewCategoryItemBinding categoryItemBinding) {
            super(categoryItemBinding.getRoot());
            this.categoryItemBinding = categoryItemBinding;
        }

        public void onBind(Category category)
        {
            this.categoryItemBinding.mTextViewName.setText(category.getName());
        }
    }
}

class ViewEventAdapter extends RecyclerView.Adapter<ViewEventAdapter.ViewHolderModel>
{
    Activity activity;
    List<Event> mData;

    public ViewEventAdapter(Activity activity, List<Event> mData) {
        this.activity = activity;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolderModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ViewEventItemBinding viewEventItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.view_event_item,parent,false);
        return new ViewHolderModel(viewEventItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderModel holder, int position) {
        holder.Event();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolderModel extends RecyclerView.ViewHolder{
        ViewEventItemBinding viewEventItemBinding;
        public ViewHolderModel(@NonNull ViewEventItemBinding viewEventItemBinding) {
            super(viewEventItemBinding.getRoot());
            this.viewEventItemBinding = viewEventItemBinding;
        }

        public void Event()
        {
            viewEventItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.getContext().startActivity(new Intent(view.getContext(), EventDetailActivity.class));
                }
            });
        }
    }
}