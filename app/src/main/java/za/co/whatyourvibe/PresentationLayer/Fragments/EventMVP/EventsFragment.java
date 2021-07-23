package za.co.whatyourvibe.PresentationLayer.Fragments.EventMVP;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import za.co.whatyourvibe.LogicLayer.Adaptor.ViewEventAdapterShow;
import za.co.whatyourvibe.LogicLayer.Data.CategoryRepo;
import za.co.whatyourvibe.LogicLayer.Data.EventRepo;
import za.co.whatyourvibe.LogicLayer.Helper.DialogHelper;
import za.co.whatyourvibe.LogicLayer.Interface.ICategoryOnClick;
import za.co.whatyourvibe.LogicLayer.Interface.IEventAPI;
import za.co.whatyourvibe.LogicLayer.Interface.IEventCategoryAPI;
import za.co.whatyourvibe.LogicLayer.Models.Category;
import za.co.whatyourvibe.LogicLayer.Models.Event;
import za.co.whatyourvibe.LogicLayer.Models.EventCategory;
import za.co.whatyourvibe.LogicLayer.Models.RetrofitClient;
import za.co.whatyourvibe.R;
import za.co.whatyourvibe.databinding.FragmentTrendingEventsBinding;
import za.co.whatyourvibe.databinding.ViewCategoryItemBinding;

public class EventsFragment extends Fragment implements ICategoryOnClick, Callback<List<Event>> {

    FragmentTrendingEventsBinding fragmentTrendingEventsBinding;
    List<Event> _ListEvents,_ListFilterEvents;
    List<EventCategory> mData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentTrendingEventsBinding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()),
                R.layout.fragment_trending_events, container, false);
        try {

            fragmentTrendingEventsBinding.mRecyclerViewTrending.setLayoutManager(new LinearLayoutManager(container.getContext()));
            _ListEvents = new ArrayList<>();
            _ListFilterEvents = new ArrayList<>();
            mData = new ArrayList<>();

            EventCategory eventCategory = new EventCategory();
            eventCategory.setId(0);
            eventCategory.setName("All");

            mData.add(eventCategory);

            IEventCategoryAPI eventCategoryAPI = RetrofitClient.RetrofitClient().create(IEventCategoryAPI.class);
            eventCategoryAPI.GetEventCategory().enqueue(new Callback<List<EventCategory>>() {
                @Override
                public void onResponse(Call<List<EventCategory>> call, Response<List<EventCategory>> response) {
                    try {
                        mData.addAll(response.body());
                        fragmentTrendingEventsBinding.mRecyclerViewCategory.setAdapter(new ViewCategoryAdapter(EventsFragment.this,mData));
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<EventCategory>> call, Throwable t) {
                    Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            
            IEventAPI eventAPI =  RetrofitClient.RetrofitClient().create(IEventAPI.class);
            eventAPI.GetEvent().enqueue(this);

            fragmentTrendingEventsBinding.mEditTextSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    String _StringSearchValue =  editable.toString().trim();
                    if(!_StringSearchValue.isEmpty())
                    {
                        List<Event> _ListFilterTempEvents = new ArrayList<>();
                        for (Event event: _ListFilterEvents) {
                            if(event.getTitle().contains(_StringSearchValue) || event.getDescription().contains(_StringSearchValue))
                            {
                                _ListFilterTempEvents.add(event);
                            }
                        }
                        fragmentTrendingEventsBinding.mRecyclerViewTrending.setAdapter(new ViewEventAdapterShow(getActivity(),_ListFilterTempEvents));
                    }
                    else
                    {
                        fragmentTrendingEventsBinding.mRecyclerViewTrending.setAdapter(new ViewEventAdapterShow(getActivity(),_ListFilterEvents));
                    }
                }
            });
        } catch (Exception e) {
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
        return fragmentTrendingEventsBinding.getRoot();
    }

    private List<Event> EventFilterByEventCategory(EventCategory category,List<Event> _ListEvents)
    {
        List<Event> _TempEventList  = new ArrayList<>();
        if(category.getId() > 0)
        {
            for (Event event:_ListEvents)
            {
                if(event.getEventCategory().getId() == category.getId())
                    _TempEventList.add(event);
            }
        }
        else
        {
            _TempEventList = _ListEvents;
        }
        return _TempEventList;
    }

    @Override
    public void onCategoryItemClick(EventCategory category) {

        _ListFilterEvents = new ArrayList<>();
        _ListFilterEvents = EventFilterByEventCategory(category,_ListEvents);
        fragmentTrendingEventsBinding.mRecyclerViewTrending.setAdapter(new ViewEventAdapterShow(getActivity(),_ListFilterEvents));
    }

    @Override
    public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
        try {
            _ListEvents = response.body();
            fragmentTrendingEventsBinding.mRecyclerViewTrending.setAdapter(new ViewEventAdapterShow(getActivity(),response.body()));
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<List<Event>> call, Throwable t) {
            Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
    }
}

class ViewCategoryAdapter extends  RecyclerView.Adapter<ViewCategoryAdapter.ViewHolderModel>
{
    ICategoryOnClick categoryOnClick;
    List<EventCategory> mData;

    public ViewCategoryAdapter(ICategoryOnClick categoryOnClick, List<EventCategory> mData) {
        this.categoryOnClick = categoryOnClick;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewCategoryAdapter.ViewHolderModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewCategoryItemBinding categoryItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.view_category_item,parent,false);

        return new ViewCategoryAdapter.ViewHolderModel(categoryItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewCategoryAdapter.ViewHolderModel holder, int position) {
        holder.onBind(categoryOnClick,mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolderModel extends RecyclerView.ViewHolder {
        ViewCategoryItemBinding categoryItemBinding;
        ICategoryOnClick ICategoryOnClick;
        public ViewHolderModel(@NonNull ViewCategoryItemBinding categoryItemBinding) {
            super(categoryItemBinding.getRoot());
            this.categoryItemBinding = categoryItemBinding;
        }

        public void onBind(ICategoryOnClick categoryOnClick, final EventCategory category)
        {
            this.ICategoryOnClick = categoryOnClick;
            this.categoryItemBinding.mTextViewName.setText(category.getName());
            this.categoryItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ICategoryOnClick.onCategoryItemClick(category);
                }
            });
        }
    }
}

