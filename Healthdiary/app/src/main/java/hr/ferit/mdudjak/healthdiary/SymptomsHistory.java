package hr.ferit.mdudjak.healthdiary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class SymptomsHistory extends AppCompatActivity {

    ListView lvSymtpomsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms_history);
        this.setUpUI();
    }

    private void setUpUI() {
        this.lvSymtpomsList = (ListView) this.findViewById(R.id.lvSymptomsHistory);
        ArrayList<Symptom> symptoms = DBHelper.getInstance(this).getAllSymptoms();
        SymptomsAdapter mSymptomsAdapter = new SymptomsAdapter(symptoms);
        this.lvSymtpomsList.setAdapter(mSymptomsAdapter);
    }
}
