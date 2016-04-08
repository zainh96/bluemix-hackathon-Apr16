package com.rbc.rbcbudgets;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CreateBudgetStepThreeFragment extends FormFragment implements View.OnClickListener{
    private FormCompletion mCallBack;
    private BudgetTarget form;

    private TextView money, date, forReason, becauseReason, list;
    private Button done;
    private String months[];
    private boolean formFilled = false;

    public CreateBudgetStepThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void clearForm() {

    }

    @Override
    public void fillForm(BudgetTarget form) {

    }

    public CreateBudgetStepThreeFragment setFormCompletionCallback(FormCompletion f){
        this.mCallBack = f;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_budget_step_three, container, false);

        money = (TextView) v.findViewById(R.id.step_three_money);
        date = (TextView) v.findViewById(R.id.step_three_date);
        forReason = (TextView) v.findViewById(R.id.step_three_for);
        becauseReason = (TextView) v.findViewById(R.id.step_three_because);
        list = (TextView) v.findViewById(R.id.step_three_list);
        done = (Button) v.findViewById(R.id.step_three_done);

        done.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        mCallBack.formIsFilled();
        formFilled = true;
    }

    public void showSummary(BudgetTarget form){
        this.form = form;

        // money
        money.setText("$" + String.format("%.2f", form.getTargetSaveGoal()));

        // date
        date.setText(months[form.getTargetMonth()] + " " + form.getTargetDayOfMonth() + ", " + form.getTargetYear());

        // For reason (goal)
        forReason.setText(form.getGoal());

        // because reason
        becauseReason.setText(form.getBecause());

        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < form.getItems().size(); i++){
            if(i != 0){
                builder.append(", ");
            }

            Item item = form.getItems().get(i);

            if(i == form.getItems().size() - 1 && i != 0){
                builder.append("and ");
            }

            builder.append(item.getItem());
        }

        list.setText(builder.toString());

        Log.i("TEST", "TEST");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        months = getActivity().getResources().getStringArray(R.array.month_array);
    }

    @Override
    public boolean isFormFilled() {
        return formFilled;
    }
}
