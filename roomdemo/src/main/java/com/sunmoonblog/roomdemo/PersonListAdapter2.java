package com.sunmoonblog.roomdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PersonListAdapter2 extends ListAdapter<Person, PersonListAdapter.PersonViewHolder> {

    private final LayoutInflater mLayoutInflater;

    private final Context mContext;

    public static final DiffUtil.ItemCallback<Person> DIFF_CALLBACK = new DiffUtil.ItemCallback<Person>() {
        @Override
        public boolean areItemsTheSame(@NonNull Person person, @NonNull Person t1) {
            return person.getId() == t1.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Person person, @NonNull Person t1) {
            return TextUtils.equals(person.getName(), t1.getName());
        }
    };

    protected PersonListAdapter2(Context context) {
        super(DIFF_CALLBACK);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PersonListAdapter.PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PersonListAdapter.PersonViewHolder(mLayoutInflater.inflate(R.layout.item_person, viewGroup,
                false));
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public void onBindViewHolder(@NonNull final PersonListAdapter.PersonViewHolder personViewHolder, int i) {
        personViewHolder.bind(getItem(i));
        personViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = personViewHolder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    EditTaskActivity.start(mContext, getItemId(pos));
                }
            }
        });
    }
}
