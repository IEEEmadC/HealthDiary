package hr.ferit.mdudjak.healthdiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class SymptomLogActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_AREA = 1;
    public static final int REQUEST_DESCRIPTION = 2;
    public static final String KEY_REQUEST_AREA = "area";
    public static final String KEY_REQUEST_DESCRIPTION = "description";
    public static final String AREA_RESULT = "area_result";
    public static final String DESCRIPTION_RESULT ="description_result";
    ImageButton bAddArea, bAddDescription;
    int i=0;
    Button bAddSymptom;
    TextView txtAreaLog, txtDescriptionLog;
    TextView txtAreaLogTitle, txtDescriptionLogTitle;
    TextView bzvz;
    EditText etPainIntensity;
    String area=null, description=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_log);
        this.setUpUI();
    }

    private void setUpUI() {
        this.bAddArea = (ImageButton) this.findViewById(R.id.bAddPainArea);
        this.bAddDescription = (ImageButton) this.findViewById(R.id.bAddPainDescription);
        this.bAddSymptom= (Button) this.findViewById(R.id.bAddSymptom);
        this.etPainIntensity= (EditText) this.findViewById(R.id.etPainIntensityLog);
        this.txtDescriptionLog= (TextView) this.findViewById(R.id.txtPainDescriptionLog);
        this.txtAreaLog= (TextView) this.findViewById(R.id.txtPainAreaLog);
        this.txtAreaLogTitle= (TextView) this.findViewById(R.id.txtPainAreaTitle);
        this.txtDescriptionLogTitle= (TextView) this.findViewById(R.id.txtPainDescriptionTitle);
        this.bAddDescription.setOnClickListener(this);
        this.bAddArea.setOnClickListener(this);
        this.bAddSymptom.setOnClickListener(this);
        this.bzvz= (TextView) this.findViewById(R.id.bzvz);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, AddSymptomInfo.class);
        switch (v.getId()) {
            case (R.id.bAddPainArea):
                intent.putExtra(KEY_REQUEST_AREA,REQUEST_AREA);
                this.startActivityForResult(intent, REQUEST_AREA);
                break;
            case (R.id.bAddPainDescription):
                intent.putExtra(KEY_REQUEST_DESCRIPTION,REQUEST_DESCRIPTION);
                this.startActivityForResult(intent, REQUEST_DESCRIPTION);
                break;
            case (R.id.bAddSymptom):
                String sPainIntensity = etPainIntensity.getText().toString();
                int iPainIntensity = Integer.parseInt(sPainIntensity);
                if(iPainIntensity>10 || iPainIntensity<1 || etPainIntensity.getText().toString().isEmpty()){
                    etPainIntensity.setError("Please enter a number from 1 to 10.");
                }
                else{
                    if((area!=null)&&(description!=null)){
                        Symptom newSymptom = new Symptom(area,description,iPainIntensity);
                        DBHelper.getInstance(getApplicationContext()).insertSymptom(newSymptom);
                        ArrayList<Symptom> symptom = new ArrayList<>();
                        symptom=DBHelper.getInstance(this).getAllSymptoms();
                        int id= symptom.get(i).getID();
                        i++;
                        String idd= String.valueOf(id);
                        this.bzvz.setText(idd);
                    }
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_AREA:
                if (resultCode == RESULT_OK) {
                     area = String.valueOf(data.getStringExtra(AREA_RESULT));
                    this.txtAreaLog.setText(area);
                    this.txtAreaLogTitle.setText("Area of pain:");
                    //Riješiti spremanje simptoma u bazu, sprema se i area i description zajedno u ovaj activity
                }
                break;
            case REQUEST_DESCRIPTION:
                if (resultCode == RESULT_OK) {
                    description = String.valueOf(data.getStringExtra(DESCRIPTION_RESULT));
                    this.txtDescriptionLog.setText(description);
                    this.txtDescriptionLogTitle.setText("Pain description:");
                    //Riješiti spremanje simptoma u bazu, sprema se i area i description zajedno u ovaj activity
                }
                break;
        }
    }

}
