package com.rbc.rbcbudgets;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class DashBoardActivity extends AppCompatActivity {

    private FloatingActionButton add;
    private List<BudgetTarget> forms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        add = (FloatingActionButton) findViewById(R.id.fab);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoardActivity.this, CreateBudgetActivity.class);
                startActivity(intent);
            }
        });

        // get forms
        forms = GlobalDataTransfer.getInstance().getForms();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // make fragments
        for(BudgetTarget form : forms){
            SummaryFragment fragment = new SummaryFragment();
            fragment.giveForm(form);
            transaction.add(R.id.fragmentContainer, fragment);
        }

        transaction.commit();
    }
}
