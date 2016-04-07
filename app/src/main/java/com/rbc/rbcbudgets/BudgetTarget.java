package com.rbc.rbcbudgets;

public class BudgetTarget {

    // for step one of form..
    private double targetSaveGoal = 0;
    private int targetYear = 2016;
    private int targetMonth = 3; // 0 indexed.
    private int targetDayOfMonth = 9; // not 0 indexed.

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
}
