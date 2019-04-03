package com.sunmoonblog.roomdemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarFragment extends Fragment {

    private CalendarView mCalendarView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCalendarView = view.findViewById(R.id.calender);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar c = Calendar.getInstance();
                c.set(year, month, dayOfMonth);

                Date dateNow = c.getTime();
                O o = (O) getChildFragmentManager().findFragmentByTag("o");
                if (o != null) {
                    o.update(Utils.beforeDate(dateNow), Utils.afterDate(dateNow));
                }
            }
        });

        O o = (O) getChildFragmentManager().findFragmentByTag("o");
        if (o == null) {
            o = new O();

            long now = mCalendarView.getDate();
            Date dateNow = new Date(now);
            o.update(Utils.beforeDate(dateNow), Utils.afterDate(dateNow));

            getChildFragmentManager().beginTransaction()
                    .add(R.id.contentByDate, o, "o")
                    .commit();
        }
    }

    public static class O extends PersonListFragment {

        private Date mFrom;
        private Date mTo;
        private LiveData<List<Person>> mLiveData;

        public void update(Date from, Date to) {
            mFrom = from;
            mTo = to;

            if (mLiveData != null) {
                mLiveData.removeObservers(this);
            }

            if (getView() != null) {
                observe();
            }
        }

        @Override
        protected void observe() {

            Date date = new Date();
            if (mFrom == null) mFrom = Utils.beforeDate(date);
            if (mTo == null) mTo = Utils.afterDate(date);

            mLiveData = getPersonViewModel().getAllPerson(mFrom, mTo);
            mLiveData.observe(this, new Observer<List<Person>>() {
                @Override
                public void onChanged(@Nullable List<Person> people) {
                    if (people != null) {
                        getPersonListAdapter().submitList(people);
                    }
                }
            });
        }
    }

}
