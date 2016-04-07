package com.rbc.rbcbudgets;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateBudgetStepTwoFragment extends FormFragment {
    private FormCompletion mCallBack;

    public CreateBudgetStepTwoFragment() {
        // Required empty public constructor
    }

    private void checkForm(boolean inputsFilled){
        if(inputsFilled){
            mCallBack.formIsFilled();
        } else {
            mCallBack.formIsNotFilled();
        }
    }

    public CreateBudgetStepTwoFragment setFormCompletionCallback(FormCompletion f){
        this.mCallBack = f;
        return this;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallBack = null; // remove reference
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_budget_step_two, container, false);
    }

    @Override
    public void clearForm() {
        // TODO
    }

    @Override
    public void fillForm(BudgetTarget form) {
        // TODO
    }
}
