package com.rbc.rbcbudgets;

import java.util.ArrayList;

public class BudgetTarget {

    // for step one of form..
    private double targetSaveGoal = 0;
    private int targetYear = 2016;
    private int targetMonth = 3; // 0 indexed.
    private int targetDayOfMonth = 9; // not 0 indexed.
    private String goal = "";
    private String because = "";
    private ArrayList<Item> items;

    public double getTargetSaveGoal() {
        return targetSaveGoal;
    }

    public void setTargetSaveGoal(double targetSaveGoal) {
        this.targetSaveGoal = targetSaveGoal;
    }

    public int getTargetYear() {
        return targetYear;
    }

    public void setTargetYear(int targetYear) {
        this.targetYear = targetYear;
    }

    public int getTargetMonth() {
        return targetMonth;
    }

    public void setTargetMonth(int targetMonth) {
        this.targetMonth = targetMonth;
    }

    public int getTargetDayOfMonth() {
        return targetDayOfMonth;
    }

    public void setTargetDayOfMonth(int targetDayOfMonth) {
        this.targetDayOfMonth = targetDayOfMonth;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getBecause() {
        return because;
    }

    public void setBecause(String because) {
        this.because = because;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
