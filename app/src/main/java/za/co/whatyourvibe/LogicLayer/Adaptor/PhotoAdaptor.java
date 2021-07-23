package za.co.whatyourvibe.LogicLayer.Adaptor;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import za.co.whatyourvibe.LogicLayer.Interface.IDeleteEventImage;
import za.co.whatyourvibe.LogicLayer.Models.EventImage;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.ViewImageItemBinding;

public class PhotoAdaptor extends RecyclerView.Adapter<PhotoAdaptor.ViewHolder>
{

    Activity activity;
    List<EventImage> _EventImageList;
    IDeleteEventImage deleteEventImage;

    public PhotoAdaptor(Activity activity, List<EventImage> _EventImageList) {
        this.activity = activity;
        this.deleteEventImage = (IDeleteEventImage)activity;
        this._EventImageList = _EventImageList;
    }

    @NonNull
    @Override
    public PhotoAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewImageItemBinding viewImageItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.view_image_item,parent,false);
        return new PhotoAdaptor.ViewHolder(viewImageItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAdaptor.ViewHolder holder, int position) {
        if(!_EventImageList.get(position).isMain())
        {
            holder.onBind(_EventImageList.get(position));

        }
        else
        {
            holder.onVisibleHide();
        }
    }

    @Override
    public int getItemCount() {
        return _EventImageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ViewImageItemBinding viewImageItemBinding;
        EventImage eventImage;

        public ViewHolder(@NonNull ViewImageItemBinding viewImageItemBinding) {
            super(viewImageItemBinding.getRoot());
            this.viewImageItemBinding = viewImageItemBinding;
        }

        public void onBind(EventImage eventImage)
        {
            viewImageItemBinding.getRoot().setVisibility(eventImage.isMain() ? View.GONE : View.VISIBLE);
            this.eventImage = eventImage;
            viewImageItemBinding.mImageViewImages.setImageBitmap(eventImage.getBitmap());
            viewImageItemBinding.mButtonDelete.setOnClickListener(this);
        }

        public void onVisibleHide()
        {
            viewImageItemBinding.getRoot().setVisibility(View.GONE);
            viewImageItemBinding.getRoot().setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            viewImageItemBinding.mCardView.setVisibility(View.GONE);
        }

        @Override
        public void onClick(View view) {
            deleteEventImage.RemoveEventImage(eventImage);
        }
    }
}
