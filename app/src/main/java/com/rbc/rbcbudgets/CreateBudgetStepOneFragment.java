package com.rbc.rbcbudgets;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.datepicker.DatePickerDialogFragment;

import java.util.Calendar;
public class CreateBudgetStepOneFragment extends FormFragment implements View.OnClickListener, CalendarDatePickerDialogFragment.OnDateSetListener {

    private FormCompletion mCallBack;
    private EditText amountInput, forInput, becauseInput;
    private Button dateButton;
    private boolean amountIsFilled = false;
    private boolean dateIsFilled = true;
    private boolean forIsFilled = false;
    private boolean becauseIsFilled = false;
    private String months[];
    private int year;
    private int month ;
    private int dayOfMonth;

    public CreateBudgetStepOneFragment() {
        // Required empty public constructor
    }

    @Override
    public void clearForm() {
        amountInput.setText("");
        forInput.setText("");
        becauseInput.setText("");
        dateButton.setText(getString(R.string.step_one_choose_date));
        dateIsFilled = false;
        amountIsFilled = false;
        forIsFilled = false;
        becauseIsFilled = false;
        mCallBack.formIsNotFilled();
    }

    @Override
    public void fillForm(BudgetTarget form) {
        form.setTargetSaveGoal(Double.parseDouble(amountInput.getText().toString()));
        form.setTargetYear(this.year);
        form.setTargetMonth(this.month);
        form.setTargetDayOfMonth(this.dayOfMonth);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_budget_step_one, container, false);

        amountInput = (EditText) v.findViewById(R.id.step_one_amount_input);
        forInput = (EditText) v.findViewById(R.id.step_one_for_input);
        becauseInput = (EditText) v.findViewById(R.id.step_one_because_input);
        dateButton = (Button) v.findViewById(R.id.step_one_date_input);

        dateButton.setOnClickListener(this);

        amountInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                amountIsFilled = s.length() > 0;
                checkForm(amountIsFilled && dateIsFilled && forIsFilled && becauseIsFilled);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        forInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                forIsFilled = s.length() > 0;
                checkForm(amountIsFilled && dateIsFilled && forIsFilled && becauseIsFilled);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        becauseInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                becauseIsFilled = s.length() > 0;
                checkForm(amountIsFilled && dateIsFilled && forIsFilled && becauseIsFilled);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return v;
    }

    private void checkForm(boolean inputsFilled){
        if(inputsFilled){
            mCallBack.formIsFilled();
        } else {
            mCallBack.formIsNotFilled();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        months = getResources().getStringArray(R.array.month_array);
    }

    @Override
    public void onClick(View v) {
        final Calendar NOW = Calendar.getInstance();
        CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                .setOnDateSetListener(this)
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setPreselectedDate(NOW.get(Calendar.YEAR), NOW.get(Calendar.MONTH) - 1, NOW.get(Calendar.DAY_OF_MONTH));
        cdp.show(getActivity().getSupportFragmentManager(), "Date Tag: ");
    }

    // Date Set Methods
    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
        dateIsFilled = true;
        String dateText = months[monthOfYear] + " " + dayOfMonth + ", " + year;
        dateButton.setText(dateText);

        this.year = year;
        this.month = monthOfYear;
        this.dayOfMonth = dayOfMonth;

        checkForm(dateIsFilled && amountIsFilled && forIsFilled && becauseIsFilled);
    }

    public CreateBudgetStepOneFragment setFormCompletionCallback(FormCompletion f){
        this.mCallBack = f;
        return this;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallBack = null; // remove reference
    }
}
