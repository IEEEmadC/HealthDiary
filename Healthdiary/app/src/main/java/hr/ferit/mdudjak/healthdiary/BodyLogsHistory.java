package hr.ferit.mdudjak.healthdiary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        BodyLogsAdapter mBodyLogsAdapter = new BodyLogsAdapter(bodyLogs);
        this.lvBodyLogsList.setAdapter(mBodyLogsAdapter);
    }
}
