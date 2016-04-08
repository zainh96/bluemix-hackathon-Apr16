package com.rbc.rbcbudgets;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {
    private BudgetTarget form;

    private CircularProgressBar progressBar;
    private TextView progressPercentage, progressName, progressSaved, progressTarget, progressdate;

    public SummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_summary, container, false);

        progressBar = (CircularProgressBar) v.findViewById(R.id.progress_bar);
        progressPercentage = (TextView) v.findViewById(R.id.progress_percentage);
        progressName = (TextView) v.findViewById(R.id.progress_goal_name);
        progressSaved = (TextView) v.findViewById(R.id.progress_saved);
        progressTarget = (TextView) v.findViewById(R.id.progress_target);
        progressdate = (TextView) v.findViewById(R.id.progress_date);

        return v;
    }

    public void giveForm(BudgetTarget form){
        this.form = form;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        progressBar.setProgress((int) form.getPercentage() * 100);
        progressPercentage.setText(form.getPercentage() + "%");
        progressName.setText(form.getGoal());
        progressSaved.setText("$" + String.format("%.2f", form.getSaved()));
        progressTarget.setText("$" + String.format("%.2f", form.getTargetSaveGoal()));

        String months[] = getActivity().getResources().getStringArray(R.array.month_array);
        progressdate.setText(months[form.getTargetMonth()] + " " + form.getTargetDayOfMonth() + ", " + form.getTargetYear());
    }
}
