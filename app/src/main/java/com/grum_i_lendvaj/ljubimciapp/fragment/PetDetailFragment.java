package com.grum_i_lendvaj.ljubimciapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grum_i_lendvaj.ljubimciapp.R;

public class PetDetailFragment extends Fragment {

    public static PetDetailFragment newInstance(int index) {
        PetDetailFragment f = new PetDetailFragment();

        Log.wtf("BITNO", "c" + index);

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("com._grum_i_lendvaj.ljubimciapp.index", index);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.wtf("BITNO", "a" + getShownIndex());

        TextView textView = (TextView) getActivity().findViewById(R.id.detailText);
        Log.wtf("BITNO", String.valueOf(textView));
        textView.setText("test index " + getShownIndex());
        Log.wtf("BITNO", "aa" + getShownIndex());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Log.wtf("BITNO", "b" + getShownIndex());
        return inflater.inflate(R.layout.fragment_pet_detail, container, false);
    }

    public int getShownIndex() {
        Log.wtf("BITNO", "d");
        return getArguments().getInt("com._grum_i_lendvaj.ljubimciapp.index", 0);
    }
}
