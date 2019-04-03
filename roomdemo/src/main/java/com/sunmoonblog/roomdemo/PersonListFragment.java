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

import java.util.List;

public class PersonListFragment extends Fragment {

    private static final String TAG = "PersonListFragment";

    private PersonViewModel mPersonViewModel;
    private PersonListAdapter2 mPersonListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_person_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final RecyclerView rv = view.findViewById(R.id.rv);

        ItemTouchHelper helper = new ItemTouchHelper(
                new SwipeToDeleteCallback(requireContext()) {
                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                       if (i == ItemTouchHelper.LEFT) {
                           mPersonViewModel.delete( ((PersonListAdapter.PersonViewHolder) viewHolder).getPerson());
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

        mPersonListAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                if (positionStart == 0) {
                    rv.post(new Runnable() {
                        @Override
                        public void run() {
                            rv.smoothScrollToPosition(0);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PersonDao dao = ((App) requireContext().getApplicationContext()).getAppDatabase().getPersonDao();
        mPersonViewModel = ViewModelProviders.of(this).get(PersonViewModel.class);
        mPersonViewModel.setPersonDao(dao);
        mPersonListAdapter = new PersonListAdapter2(requireContext());
    }

    @Override
    public void onStart() {
        super.onStart();
        observe();
    }

    protected void observe() {
        mPersonViewModel.getAllPerson2().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(@Nullable List<Person> people) {
                if (people != null) {
                    Log.i(TAG, "onChanged: " + people.size());
                    mPersonListAdapter.submitList(people);
                }
            }
        });
    }

    public PersonViewModel getPersonViewModel() {
        return mPersonViewModel;
    }

    public PersonListAdapter2 getPersonListAdapter() {
        return mPersonListAdapter;
    }
}
