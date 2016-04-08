package com.rbc.rbcbudgets;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class CreateBudgetActivity extends AppCompatActivity implements ImageButton.OnClickListener, FormCompletion {

    private ViewPager viewer;
    private ImageButton next, prev, clear;
    private PagerAdapter adapter;
    protected List<FormFragment> fragments = new ArrayList<>();
    private BudgetTarget targetForm = new BudgetTarget();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_budget);

        viewer = (ViewPager) findViewById(R.id.create_page_viewer);
        next = (ImageButton) findViewById(R.id.create_next);
        prev = (ImageButton) findViewById(R.id.create_prev);
        clear = (ImageButton) findViewById(R.id.create_clear);

        next.setOnClickListener(this);
        prev.setOnClickListener(this);
        clear.setOnClickListener(this);

        // create fragments
        fragments.add(new CreateBudgetStepOneFragment().setFormCompletionCallback(this));
        fragments.add(new CreateBudgetStepTwoFragment().setFormCompletionCallback(this));
        fragments.add(new CreateBudgetStepThreeFragment().setFormCompletionCallback(this));

        adapter = new CreateBudgetPagerAdapter(getSupportFragmentManager());
        viewer.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        final int currentFragment = viewer.getCurrentItem();

        if (id == R.id.create_prev) {
            viewer.setCurrentItem(currentFragment - 1);
            next.setVisibility(View.VISIBLE);
        } else if (id == R.id.create_next) {
            // save form
            fragments.get(currentFragment).fillForm(this.targetForm);
            if(currentFragment == fragments.size() - 1){
                // save to DB, goto details.
                Intent intent = new Intent(this, DashBoardActivity.class);
                GlobalDataTransfer.getInstance().addToForms(this.targetForm);
                startActivity(intent);
            } else {
                // goto next form item
                viewer.setCurrentItem(currentFragment + 1, true);

                if(!fragments.get(currentFragment + 1).isFormFilled()) {
                    next.setVisibility(View.INVISIBLE);
                }

                if(currentFragment + 1 == fragments.size() - 1){
                    ((CreateBudgetStepThreeFragment) fragments.get(currentFragment + 1)).showSummary(targetForm);
                }
            }
        } else if(id == R.id.create_clear){
            fragments.get(currentFragment).clearForm();
        }

        checkButtonConditions();
    }

    // toggles prev and next button enable states
    private void checkButtonConditions(){
        if(viewer.getCurrentItem() == 0){
            prev.setVisibility(View.INVISIBLE);
        } else {
            prev.setVisibility(View.VISIBLE);
        }
    }

    // FormCompletion Methods
    @Override
    public void formIsFilled() {
        next.setVisibility(View.VISIBLE);
    }

    @Override
    public void formIsNotFilled() {
        next.setVisibility(View.INVISIBLE);
    }

    private class CreateBudgetPagerAdapter extends FragmentStatePagerAdapter {
        public CreateBudgetPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
