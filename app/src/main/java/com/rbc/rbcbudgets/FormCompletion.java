package com.rbc.rbcbudgets;

// this interface should be implemented by a class who wants to know when a create budget section is properly filled out
// and is therefore ready to move on to the next phase
public interface FormCompletion {
    // called when the form is filled properly
    void formIsFilled();

    // called when the form is not filled properly
    void formIsNotFilled();
}
