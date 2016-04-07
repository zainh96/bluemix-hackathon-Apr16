package com.rbc.rbcbudgets;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by platohe on 2016-04-07.
 */
public class CategoryPickerDialog extends android.support.v4.app.DialogFragment implements AdapterView.OnItemSelectedListener {
    private String category_array[];
    private String food_array[];
    private String clothing_array[];
    private String electronics_array[];
    private String entertainment_array[];

    private CategoryAddListener callBack;
    private Spinner categorySpinner, itemSpinner;
    private EditText input;
    private String item = "Fast Food";
    private int categoryIndex = 0;

    public interface CategoryAddListener{
        void addItem(int category, String item);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_category_picker, null);

        categorySpinner = (Spinner) v.findViewById(R.id.category_spinner);
        itemSpinner = (Spinner) v.findViewById(R.id.item_spinner);
        input = (EditText) v.findViewById(R.id.item_input);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(),
                R.array.step_two_categories, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                R.array.food_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemSpinner.setAdapter(adapter2);

        categorySpinner.setOnItemSelectedListener(this);
        itemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (categoryIndex){
                    case 0:
                        item = food_array[position];
                        break;
                    case 1:
                        item = clothing_array[position];
                        break;
                    case 2:
                        item = electronics_array[position];
                        break;
                    case 3:
                        item = entertainment_array[position];
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        builder.setView(v);
        builder.setTitle("Choose an Item");
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(categoryIndex == category_array.length - 1){
                    item = input.getText().toString();
                }
                callBack.addItem(categoryIndex, item);
            }
        });

        return builder.create();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // check for other.
        if(position == category_array.length - 1){
            itemSpinner.setVisibility(View.GONE);
            input.setVisibility(View.VISIBLE);
        }  else {
            itemSpinner.setVisibility(View.VISIBLE);
            input.setVisibility(View.GONE);
        }

        if(position == 0){
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                    R.array.food_array, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            itemSpinner.setAdapter(adapter2);
        } else if(position == 1){
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                    R.array.clothing_array, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            itemSpinner.setAdapter(adapter2);
        } else if(position == 2){
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                    R.array.electronics_array, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            itemSpinner.setAdapter(adapter2);
        } else if(position == 3){
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                    R.array.entertainment_array, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            itemSpinner.setAdapter(adapter2);
        }

        categoryIndex = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        category_array = getActivity().getResources().getStringArray(R.array.step_two_categories);
        food_array = getActivity().getResources().getStringArray(R.array.food_array);
        clothing_array = getActivity().getResources().getStringArray(R.array.clothing_array);
        electronics_array = getActivity().getResources().getStringArray(R.array.electronics_array);
        entertainment_array = getActivity().getResources().getStringArray(R.array.entertainment_array);
    }

    public void setListener(CategoryAddListener callBack){
        this.callBack = callBack;
    }
}
