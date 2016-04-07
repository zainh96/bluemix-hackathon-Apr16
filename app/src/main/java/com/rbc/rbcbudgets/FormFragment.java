package com.rbc.rbcbudgets;

import android.support.v4.app.Fragment;

public abstract class FormFragment extends Fragment {
    public abstract void clearForm();
    public abstract void fillForm(BudgetTarget form);
}
