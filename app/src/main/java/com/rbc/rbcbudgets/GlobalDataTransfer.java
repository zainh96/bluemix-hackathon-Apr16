package com.rbc.rbcbudgets;

import java.util.ArrayList;
import java.util.List;

// singleton class
public class GlobalDataTransfer {
    private BudgetTarget form = null; // most recent single addition
    private ArrayList<BudgetTarget> forms = new ArrayList<>(); // holds all forms
    private static GlobalDataTransfer instance = null;

    public BudgetTarget getForm() {
        return form;
    }

    public void setForm(BudgetTarget form) {
        this.form = form;
    }

    public ArrayList<BudgetTarget> getForms() {
        return forms;
    }

    public void setForms(ArrayList<BudgetTarget> forms) {
        this.forms = forms;
    }

    private GlobalDataTransfer(){}

    public static GlobalDataTransfer getInstance(){
        if(instance == null){
            instance = new GlobalDataTransfer();
        }

        return instance;
    }

    public void addToForms(BudgetTarget form){
        forms.add(form);
        this.form = form;
    }

    public void addToForms(List<BudgetTarget> forms){
        this.forms.addAll(forms);
    }

    public void deleteAll(){
        this.forms = new ArrayList<>();
    }
}
