package za.co.whatyourvibe.PresentationLayer.Fragments.ContactMVP;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.FragmentContactBinding;
import za.co.whatyourvibe.databinding.ViewContactItemBinding;

public class ContactFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentContactBinding contactFragment = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()),
                R.layout.fragment_contact, container, false);

        contactFragment.mRecyclerViewContact.setLayoutManager(new LinearLayoutManager(getContext()));
        contactFragment.mRecyclerViewContact.setAdapter(new ViewContactAdapter(getActivity(),new ArrayList<Contact>()));

        return contactFragment.getRoot();
    }
}

class ViewContactAdapter extends RecyclerView.Adapter<ViewContactAdapter.ViewHolderModel>{

    Activity activity;
    List<Contact> mData;

    public ViewContactAdapter(Activity activity, List<Contact> mData) {
        this.activity = activity;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewContactAdapter.ViewHolderModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewContactItemBinding contactItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.view_contact_item,parent,false);
        return new ViewHolderModel(contactItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewContactAdapter.ViewHolderModel holder, int position) {
        //holder.onBind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolderModel extends RecyclerView.ViewHolder {

        ViewContactItemBinding contactItemBinding;

        public ViewHolderModel(@NonNull ViewContactItemBinding contactItemBinding) {
            super(contactItemBinding.getRoot());
            this.contactItemBinding = contactItemBinding;
        }

        public void onBind(Contact contact)
        {
            contactItemBinding.mTextViewUser.setText(contact.getUser());
            contactItemBinding.mTextViewEmail.setText(contact.getEmail());
            contactItemBinding.mTextViewCell.setText(contact.getCell());
        }
    }
}

class Contact{

    private int id;
    private String user;
    private String email;
    private String cell;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }
}