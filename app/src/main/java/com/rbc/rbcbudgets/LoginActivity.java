package com.rbc.rbcbudgets;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

// login Activity.
public class LoginActivity extends AppCompatActivity implements ImageButton.OnTouchListener, View.OnClickListener {

    private EditText cardNumberInput = null;
    private EditText cardPasswordInput = null;

    private ImageButton moreCards = null;
    private ImageButton showPassword = null;

    private ProgressBar progressBar = null;
    private CheckBox rememberMe = null;
    private Button submit = null;

    // used to determine if submit should be enabled.
    private boolean passwordCorrectFormat = false;
    private boolean cardCorrectFormat = false;

    private static final int CARD_LENGTH = 16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardNumberInput = (EditText) findViewById(R.id.login_client_card);
        cardPasswordInput = (EditText) findViewById(R.id.login_client_card_password);

        moreCards = (ImageButton) findViewById(R.id.more_cards_button);
        showPassword = (ImageButton) findViewById(R.id.show_password_button);

        progressBar = (ProgressBar) findViewById(R.id.progress_spinner);
        rememberMe = (CheckBox) findViewById(R.id.login_remember_checkbox);
        submit = (Button) findViewById(R.id.login_submit);

        showPassword.setOnTouchListener(this);
        submit.setOnClickListener(this);
        cardNumberInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cardCorrectFormat = (s.length() == CARD_LENGTH);
            }

            @Override
            public void afterTextChanged(Editable s) {
                enableSubmit(cardCorrectFormat && passwordCorrectFormat);
            }
        });
        cardPasswordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // passwords should be at least 8 characters
                passwordCorrectFormat = (s.length() >= 8);
            }

            @Override
            public void afterTextChanged(Editable s) {
                enableSubmit(cardCorrectFormat && passwordCorrectFormat);
            }
        });
        // TODO: set new onClickListener for more cards button.
    }

    @Override
    public void onClick(View v) {
        // TODO: check credentials with db.

        // TODO: show progress bar

        // TODO: launch dashboard activity or launch initial budget creation.
        Intent intent = new Intent(this, CreateBudgetActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                cardPasswordInput.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                cardPasswordInput.setSelection(cardPasswordInput.length());
                return true;
            case MotionEvent.ACTION_UP:
                cardPasswordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                cardPasswordInput.setSelection(cardPasswordInput.length());
                return true;
        }
        return false;
    }

    public void enableSubmit(boolean shouldEnable){
        if(shouldEnable == submit.isEnabled()){
            return;
        }

        if(shouldEnable){
            submit.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        } else {
            submit.setTextColor(Color.DKGRAY);
        }

        submit.setEnabled(shouldEnable);
    }
}
