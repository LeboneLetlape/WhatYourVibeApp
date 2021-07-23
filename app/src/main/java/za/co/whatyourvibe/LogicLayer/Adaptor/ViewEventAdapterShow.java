package za.co.whatyourvibe.LogicLayer.Adaptor;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.logging.Handler;

import za.co.whatyourvibe.LogicLayer.Models.ApplicationModel;
import za.co.whatyourvibe.LogicLayer.Models.Event;
import za.co.whatyourvibe.LogicLayer.Models.EventImage;
import za.co.whatyourvibe.LogicLayer.Models.RetrofitClient;
import za.co.whatyourvibe.PresentationLayer.Activities.EventDetailMVP.EventDetailActivity;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.ViewEventItemBinding;

public class ViewEventAdapterShow extends RecyclerView.Adapter<ViewEventAdapterShow.ViewHolderModel>
{
    Activity activity;
    List<Event> mData;

    public ViewEventAdapterShow(Activity activity, List<Event> mData) {
        this.activity = activity;
        this.mData = mData;
        Toast.makeText(activity, "Size:"+mData.size(), Toast.LENGTH_SHORT).show();
    }

    @NonNull
    @Override
    public ViewEventAdapterShow.ViewHolderModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ViewEventItemBinding viewEventItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.view_event_item,parent,false);
        return new ViewEventAdapterShow.ViewHolderModel(viewEventItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewEventAdapterShow.ViewHolderModel holder, int position) {
        holder.Event(mData.get(position));
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

        public void Event(Event event)
        {
            EventImage _EventImage = new EventImage();
            viewEventItemBinding.mTextViewTitle.setText(event.getTitle());
            viewEventItemBinding.mTextViewDescription.setText(event.getDescription());

            for (EventImage eventImage:event.getEventImages()) {
                if(eventImage.isMain())
                {
                    _EventImage = eventImage;
                }
            }

            if(_EventImage.getUrl() != null)
            {
                Glide.with(viewEventItemBinding.mTextViewTitle.getContext())
                        .load(RetrofitClient._StringWebUrl + _EventImage.getUrl())
                        .into(viewEventItemBinding.mImageViewCoverImage);
            }
            viewEventItemBinding.mTextViewTimeFrom.setText(event.getFromTime());
            viewEventItemBinding.mTextViewTimeTo.setText(event.getToTime());


            if(event.getEventCategory() != null)
            {
                viewEventItemBinding.mTextViewCategory.setText(event.getEventCategory().getName());
            }


            viewEventItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ApplicationModel.event = event;
                    view.getContext().startActivity(new Intent(view.getContext(), EventDetailActivity.class));
                }
            });
        }
    }
}
