package com.rbc.rbcbudgets;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.send_notification:
                sendNotification();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sendNotification(){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.rbc_login)
                        .setContentTitle("RBC can help you with your budgets")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("We've noticed you were having trouble with one of your budgets. You can talk to an RBC representative at a local branch"))
                        .setContentText("We've noticed you were having trouble with one of your budgets. You can talk to an RBC representative at a local branch");
        Intent result = new Intent(this, LoginActivity.class);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        result,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(1, mBuilder.build());
    }
}
