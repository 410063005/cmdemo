package com.sunmoonblog.roomdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.AsyncListDiffer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.sunmoonblog.roomdemo.PersonListAdapter2.DIFF_CALLBACK;

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.PersonViewHolder> {

    private static final boolean USE_DIFF = true;

    private List<Person> mPersonList;
    private final LayoutInflater mLayoutInflater;

    private final AsyncListDiffer<Person> mDiffer = new AsyncListDiffer<>(this, DIFF_CALLBACK);

    public PersonListAdapter(Context context) {
        mPersonList = new ArrayList<>();
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PersonViewHolder(mLayoutInflater.inflate(R.layout.item_person, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder personViewHolder, int i) {
        if (USE_DIFF) {
            personViewHolder.bind(mDiffer.getCurrentList().get(i));
        } else {
            personViewHolder.bind(mPersonList.get(i));
        }
    }

    @Override
    public int getItemCount() {
        if (USE_DIFF) {
            return mDiffer.getCurrentList().size();
        } else {
            return mPersonList.size();
        }
    }

    public void submitList(List<Person> people) {
        if (USE_DIFF) {
            mDiffer.submitList(people);
        } else {
            mPersonList.clear();
            mPersonList.addAll(people);
            notifyDataSetChanged();
        }
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView age;
        private Person person;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView);
            age = itemView.findViewById(R.id.textView2);
        }

        public void bind(Person person) {
            this.person = person;
            name.setText(person.getName());
            age.setText(Integer.toString(person.getAge()));
        }

        public Person getPerson() {
            return person;
        }
    }
}
