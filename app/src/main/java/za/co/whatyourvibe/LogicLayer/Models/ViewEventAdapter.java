package za.co.whatyourvibe.LogicLayer.Models;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import za.co.whatyourvibe.PresentationLayer.Activities.AddEventMVP.AddEventActivity;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.ViewEventItemBinding;

public class ViewEventAdapter extends RecyclerView.Adapter<ViewEventAdapter.ViewHolderModel>
{
    Activity activity;
    List<Event> mData;

    public ViewEventAdapter(Activity activity, List<Event> mData) {
        this.activity = activity;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewEventAdapter.ViewHolderModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ViewEventItemBinding viewEventItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.view_event_item,parent,false);
        return new ViewEventAdapter.ViewHolderModel(viewEventItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewEventAdapter.ViewHolderModel holder, int position) {
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
                    view.getContext().startActivity(new Intent(view.getContext(), AddEventActivity.class));
                }
            });
        }
    }
}
