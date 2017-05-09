package hr.ferit.mdudjak.healthdiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class SymptomLogActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_AREA = 1;
    public static final int REQUEST_DESCRIPTION = 2;
    public static final String KEY_REQUEST_AREA = "area";
    public static final String KEY_REQUEST_DESCRIPTION = "description";
    ImageButton bAddArea, bAddDescription;
    Button bAddSymptom;
    EditText etPainIntensity;
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
        this.bAddDescription.setOnClickListener(this);
        this.bAddArea.setOnClickListener(this);
        this.bAddSymptom.setOnClickListener(this);
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
                if(iPainIntensity>10 || iPainIntensity<1) etPainIntensity.setError("Please enter a number from 1 to 10.");
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_AREA:
                if (resultCode == RESULT_OK) {
                    //obradi podatke
                }
                break;
            case REQUEST_DESCRIPTION:
                if (resultCode == RESULT_OK) {
                    //obradi podatke
                }
                break;
        }
    }

}
