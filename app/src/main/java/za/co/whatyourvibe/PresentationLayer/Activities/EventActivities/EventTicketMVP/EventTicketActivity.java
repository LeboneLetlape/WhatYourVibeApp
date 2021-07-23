package za.co.whatyourvibe.PresentationLayer.Activities.EventActivities.EventTicketMVP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import za.co.whatyourvibe.LogicLayer.Models.ApplicationModel;
import za.co.whatyourvibe.LogicLayer.Models.Event;
import za.co.whatyourvibe.LogicLayer.Models.EventTicket;
import za.co.whatyourvibe.LogicLayer.Models.Ticket;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.ActivityEventTicketBinding;
import za.co.whatyourvibe.databinding.ViewTicketInfoItemBinding;

public class EventTicketActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityEventTicketBinding activityEventTicketBinding;
    List<EventTicket> eventTickets;
    boolean _IsValidated;
    Event _Event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Event Tickets");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        activityEventTicketBinding = DataBindingUtil.setContentView(this,R.layout.activity_event_ticket);


        _Event = ApplicationModel.getEvent();

        eventTickets = _Event.getEventTickets();

        activityEventTicketBinding.mRecyclerViewTickets.setAdapter(new TicketAdaptor(this,eventTickets));
        activityEventTicketBinding.mRecyclerViewTickets.setLayoutManager(new LinearLayoutManager(this));

        activityEventTicketBinding.mButtonDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        for(EventTicket eventTicket : eventTickets)
        {
            if(eventTicket.isActive() == true)
                _IsValidated = true;
        }

        if(_IsValidated)
        {
            ApplicationModel.getEventValidation().IsTicketValid = true;
            ApplicationModel.event.setEventTickets(eventTickets);
            finish();
        }
        else
        {
            Toast.makeText(this, "Please select aleast one ticket", Toast.LENGTH_SHORT).show();
        }
    }
}


class TicketAdaptor extends RecyclerView.Adapter<TicketAdaptor.ViewHolder>
{
    Activity activity;
    List<EventTicket> eventTickets;

    public TicketAdaptor(Activity activity, List<EventTicket> eventTickets) {
        this.activity = activity;
        this.eventTickets = eventTickets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewTicketInfoItemBinding viewTicketInfoItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.view_ticket_info_item,parent,false);
        return new ViewHolder(viewTicketInfoItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(eventTickets.get(position));
    }

    @Override
    public int getItemCount() {
        return eventTickets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements TextWatcher, CompoundButton.OnCheckedChangeListener {

        ViewTicketInfoItemBinding viewTicketInfoItemBinding;
        EventTicket eventTicket;

        public ViewHolder(@NonNull ViewTicketInfoItemBinding viewTicketInfoItemBinding) {
            super(viewTicketInfoItemBinding.getRoot());
            this.viewTicketInfoItemBinding = viewTicketInfoItemBinding;
            viewTicketInfoItemBinding.mTextViewPrice.setEnabled(false);
            viewTicketInfoItemBinding.mTextViewPrice.addTextChangedListener(this);
            viewTicketInfoItemBinding.mCheckBoxActive.setOnCheckedChangeListener(this);
        }

        public void onBind(EventTicket eventTicket)
        {
            this.eventTicket = eventTicket;
            viewTicketInfoItemBinding.mTextViewTicketName.setText(eventTicket.getTicket().getName());
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            this.eventTicket.setPrice(Double.parseDouble(editable.toString()));
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            this.eventTicket.setActive(b);
            viewTicketInfoItemBinding.mTextViewPrice.setEnabled(b);
        }
    }


}