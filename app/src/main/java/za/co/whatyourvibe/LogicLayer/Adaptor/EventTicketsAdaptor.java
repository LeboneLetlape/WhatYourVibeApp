package za.co.whatyourvibe.LogicLayer.Adaptor;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import za.co.whatyourvibe.LogicLayer.Helper.NumericHelper;
import za.co.whatyourvibe.LogicLayer.Models.EventTicket;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.ViewTicketItemBinding;

public class EventTicketsAdaptor extends RecyclerView.Adapter<EventTicketsAdaptor.ViewHolder>
{
    Activity activity;
    List<EventTicket> mData;

    public EventTicketsAdaptor(Activity activity, List<EventTicket> mData) {
        this.activity = activity;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewTicketItemBinding viewTicketItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.view_ticket_item,parent,false);
        return new ViewHolder(viewTicketItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EventTicketsAdaptor.ViewHolder holder, int position) {
        holder.onBind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ViewTicketItemBinding viewTicketItemBinding;
        public ViewHolder(@NonNull ViewTicketItemBinding viewTicketItemBinding) {
            super(viewTicketItemBinding.getRoot());
            this.viewTicketItemBinding = viewTicketItemBinding;
        }

        private void onBind(EventTicket eventTicket)
        {
            this.viewTicketItemBinding.mTextViewName.setText(eventTicket.getTicket().getName());
            this.viewTicketItemBinding.mTextViewPrice.setText("R"+ NumericHelper.GetDecimalPlaces(eventTicket.getPrice()));
        }
    }



}

