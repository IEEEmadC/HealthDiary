package hr.ferit.mdudjak.healthdiary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class BodyLogActivity extends AppCompatActivity implements View.OnClickListener{

    Button mSubmitBodyLog;
    EditText etWeight, etHeartRate, etBloodSugar, etBodyPressureUpper, etBodyPressureLower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_log);
        this.setUpUI();
    }

    private void setUpUI() {
        this.etWeight= (EditText) this.findViewById(R.id.etAddWeight);
        this.etHeartRate= (EditText) this.findViewById(R.id.etAddHeartRate);
        this.etBloodSugar= (EditText) this.findViewById(R.id.etAddBloodSugar);
        this.etBodyPressureUpper= (EditText) this.findViewById(R.id.etAddBodyPressureUpper);
        this.etBodyPressureLower = (EditText) this.findViewById(R.id.etAddBodyPressureLower);
        this.mSubmitBodyLog= (Button) this.findViewById(R.id.bAddBodyLog);
        this.mSubmitBodyLog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (etWeight.getText().toString().isEmpty() || etHeartRate.getText().toString().isEmpty() || etBloodSugar.getText().toString().isEmpty() || etBodyPressureUpper.getText().toString().isEmpty() || etBodyPressureLower.getText().toString().isEmpty()) {
            //Napraviti LOG
        } else {
            Float fWeight = Float.parseFloat(etWeight.getText().toString());
            int iHeartRate = Integer.parseInt(etHeartRate.getText().toString());
            Float fBloodSugar = Float.parseFloat(etBloodSugar.getText().toString());
            int iBodyPressureUpper = Integer.parseInt(etBodyPressureUpper.getText().toString());
            int iBodyPressureLower = Integer.parseInt(etBodyPressureLower.getText().toString());
            if (fWeight <= 0 || fWeight > 800 ) {
                etWeight.setError("Please input realistic weight.");
            } else if (iHeartRate <= 0 || iHeartRate > 250) {
                etHeartRate.setError("Please input realistic heart rate");
            } else if (fBloodSugar <= 0 || fBloodSugar > 150 ) {
                etBloodSugar.setError("Please input realistic blood sugar");
            } else if (iBodyPressureUpper <= 0 || iBodyPressureUpper > 250) {
                etBodyPressureUpper.setError("Please input realistic body pressure");
            } else if (iBodyPressureLower <= 0 || iBodyPressureLower > 250 ) {
                etBodyPressureLower.setError("Please input realistic body pressure");
            } else {
                    //Spremanje u bazu
                BodyLog bodyLog = new BodyLog(fWeight,iHeartRate,fBloodSugar,iBodyPressureUpper,iBodyPressureLower);
                DBHelper.getInstance(getApplicationContext()).insertBodyLog(bodyLog);
            }
        }
    }
    }

