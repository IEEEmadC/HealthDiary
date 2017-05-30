package hr.ferit.mdudjak.healthdiary;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class BodyLogsHistory extends AppCompatActivity {

    ListView lvBodyLogsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_logs_history);
        this.setUpUI();
    }

    private void setUpUI() {
        this.lvBodyLogsList = (ListView) this.findViewById(R.id.lvBodyLogsHistory);
        ArrayList<BodyLog> bodyLogs = DBHelper.getInstance(this).getAllBodyLogs();
        final BodyLogsAdapter mBodyLogsAdapter = new BodyLogsAdapter(bodyLogs);
        this.lvBodyLogsList.setAdapter(mBodyLogsAdapter);
        this.lvBodyLogsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(BodyLogsHistory.this);
                dialogBuilder.setMessage("Do you want to delete body info log?");
                dialogBuilder.setCancelable(true);

                dialogBuilder.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                DBHelper.getInstance(getApplicationContext()).deleteBodyLog((BodyLog) mBodyLogsAdapter.getItem(position));
                                mBodyLogsAdapter.deleteAt(position);
                                dialog.cancel();
                            }
                        });

                dialogBuilder.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
                return true;
            }
        });
    }
}
