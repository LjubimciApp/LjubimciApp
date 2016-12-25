package com.grum_i_lendvaj.ljubimciapp.fragment;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.grum_i_lendvaj.ljubimciapp.PetDetailActivity;
import com.grum_i_lendvaj.ljubimciapp.R;
import com.grum_i_lendvaj.ljubimciapp.database.AccountDataHelper;

public class PetListFragment extends ListFragment {

    AccountDataHelper helper;

    private boolean dualPane;
    private int currentPosition = -1;
    private int currentIndex = -1;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        helper = new AccountDataHelper(getActivity());

        View detailsFrame = getActivity().findViewById(R.id.detail_frame);
        dualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            // Restore last state for checked position.
            currentPosition = savedInstanceState.getInt("currentPosition", -1);
            currentIndex = savedInstanceState.getInt("currentIndex", -1);
        }

        if (dualPane) {
            // In dual-pane mode, the list view highlights the selected item.
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            // Make sure our UI is in the correct state.
            if (currentIndex >= 0)
                showDetails(currentPosition, currentIndex);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pet_list, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        setListAdapter(new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1,
                helper.getReadableDatabase().query("pets", new String[]{"_id, name"}, null, null, null, null, null),
                new String[]{}, new int[]{}, 0));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentPosition", currentPosition);
        outState.putInt("currentIndex", currentIndex);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position, (int) id);
    }

    private void showDetails(int position, int index) {

        currentPosition = position;
        currentIndex = index;

        if (dualPane) {
            if (position >= 0)
                getListView().setItemChecked(position, true);

            PetDetailFragment details = (PetDetailFragment) getFragmentManager()
                    .findFragmentById(R.id.detail_frame);
            if (details == null || details.getShownIndex() != index) {
                details = PetDetailFragment.newInstance((int) index);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.detail_frame, details, "details");
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        } else {
            Intent intent = new Intent(getActivity(), PetDetailActivity.class);
            intent.putExtra("index", index);
            startActivity(intent);
        }
    }
}
