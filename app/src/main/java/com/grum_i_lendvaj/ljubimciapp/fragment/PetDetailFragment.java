package com.grum_i_lendvaj.ljubimciapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grum_i_lendvaj.ljubimciapp.R;

import java.util.Locale;

public class PetDetailFragment extends Fragment {

    public static PetDetailFragment newInstance(int index) {
        PetDetailFragment f = new PetDetailFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = (TextView) getActivity().findViewById(R.id.detailText);
        textView.setText(String.format(Locale.getDefault(), "%d", getShownIndex()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pet_detail, container, false);
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }
}
