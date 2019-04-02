package com.sunmoonblog.roomdemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

public class PersonListFragment extends Fragment {

    private static final String TAG = "PersonListFragment";

    private PersonViewModel mPersonViewModel;
    private PersonListAdapter mPersonListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_person_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rv = view.findViewById(R.id.rv);

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.START) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                Log.i(TAG, "onSwiped() called with: viewHolder = [" + viewHolder + "], i = [" + i + "]");
                if (i == ItemTouchHelper.START) {
                    mPersonViewModel.delete(((PersonListAdapter.PersonViewHolder) viewHolder).getPerson());
                }
            }
        });
        helper.attachToRecyclerView(rv);
        rv.setAdapter(mPersonListAdapter);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        rv.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        view.findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPersonViewModel.getAllPerson();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PersonDao dao = ((App) requireContext().getApplicationContext()).getAppDatabase().getPersonDao();
        mPersonViewModel = ViewModelProviders.of(this).get(PersonViewModel.class);
        mPersonViewModel.setPersonDao(dao);
        mPersonListAdapter = new PersonListAdapter(requireContext());
    }

    @Override
    public void onStart() {
        super.onStart();
        //        mPersonViewModel.getLiveDataAllPerson().observe(this, new Observer<List<Person>>() {
        mPersonViewModel.getAllPerson(Arrays.asList(15, 16)).observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(@Nullable List<Person> people) {
                if (people != null) {
                    Log.i(TAG, "onChanged: " + people.size());
                    mPersonListAdapter.setAll(people);
                    mPersonListAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
