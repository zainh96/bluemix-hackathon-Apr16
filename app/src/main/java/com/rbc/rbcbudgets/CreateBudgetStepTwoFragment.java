package com.rbc.rbcbudgets;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateBudgetStepTwoFragment extends FormFragment implements CategoryPickerDialog.CategoryAddListener, View.OnClickListener {
    private FormCompletion mCallBack;
    private Button add;

    public CreateBudgetStepTwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void addItem(int category, String item) {
        Log.i("category = " + category + " item = " + item, "");
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
        View v = inflater.inflate(R.layout.fragment_create_budget_step_two, container, false);

        add = (Button) v.findViewById(R.id.step_two_add);
        add.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        CategoryPickerDialog dialog = new CategoryPickerDialog();
        dialog.setListener(this);
        dialog.show(getActivity().getSupportFragmentManager(), "Category Picker");
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
