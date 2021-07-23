package za.co.whatyourvibe.PresentationLayer.Fragments.CategoryMVP;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import za.co.whatyourvibe.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventCategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventCategoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_category, container, false);
    }
}

