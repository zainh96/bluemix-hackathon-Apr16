package com.rbc.rbcbudgets;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateBudgetStepTwoFragment extends FormFragment implements CategoryPickerDialog.CategoryAddListener, View.OnClickListener {
    private FormCompletion mCallBack;
    private Button add;
    private LinearLayout mContainer;
    private ArrayList<Item> items = new ArrayList<>();

    private int images[] = {
            R.drawable.hamburger,
            R.drawable.clothes,
            R.drawable.electronics,
            R.drawable.entertainment,
            R.drawable.other
    };

    public CreateBudgetStepTwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void addItem(int category, String item) {
        Log.i("category = " + category + " item = " + item, "gjghg");
        View v = getActivity().getLayoutInflater().inflate(R.layout.item_view, mContainer, false);

        TextView t = (TextView) v.findViewById(R.id.item_view_item);
        t.setText(item);

        ImageView image = (ImageView) v.findViewById(R.id.item_view_image);
        image.setImageDrawable(getActivity().getResources().getDrawable(images[category], null));

        items.add(new Item(category, item));
        checkForm(true);
        mContainer.addView(v);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_budget_step_two, container, false);

        add = (Button) v.findViewById(R.id.step_two_add);
        add.setOnClickListener(this);

        mContainer = (LinearLayout) v.findViewById(R.id.itemsContainer);

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
        mContainer.removeAllViews();
        items = new ArrayList<>();
        checkForm(false);
    }

    @Override
    public void fillForm(BudgetTarget form) {
        form.setItems(items);
    }
}
