package hr.ferit.mdudjak.healthdiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class AddSymptomInfo extends AppCompatActivity implements View.OnClickListener{
    ListView listView;
    ImageButton bAddInfo;
    ImageButton ibSearchInfo;
    EditText etAddInfo;
    ArrayList<String> areas;
    ArrayList<String> descriptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent startingIntent = this.getIntent();
        if(startingIntent.hasExtra(SymptomLogActivity.KEY_REQUEST_AREA)) {
            setContentView(R.layout.activity_add_symptom_area);
            setUpAreaInputUI();
        }
        if(startingIntent.hasExtra(SymptomLogActivity.KEY_REQUEST_DESCRIPTION)){
            setContentView(R.layout.activity_add_symptom_description);
            setUpDescriptionInputUI();
        }
    }

    private void setUpAreaInputUI() {
        this.listView= (ListView) this.findViewById(R.id.areaList);
        this.etAddInfo = (EditText) this.findViewById(R.id.etAddArea);
        areas =new ArrayList<String>();
        this.areas = DBHelper.getInstance(this).getAllAreas();
        ArrayAdapter<String> areaItemsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,areas);
        this.listView.setAdapter(areaItemsAdapter);
        this.bAddInfo= (ImageButton) this.findViewById(R.id.ibAddArea);
        this.bAddInfo.setOnClickListener(this);
        this.ibSearchInfo = (ImageButton) this.findViewById(R.id.ibSearchArea);
        this.ibSearchInfo.setOnClickListener(this);
    }
    private void setUpDescriptionInputUI() {
        this.listView= (ListView) this.findViewById(R.id.descriptionList);
        this.etAddInfo= (EditText) this.findViewById(R.id.etAddDescription);
        descriptions = new ArrayList<String>();
        this.descriptions = DBHelper.getInstance(this).getAllDescriptions();
        ArrayAdapter<String> descriptionsItemsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,descriptions);
        this.listView.setAdapter(descriptionsItemsAdapter);
        this.bAddInfo= (ImageButton) this.findViewById(R.id.ibAddDescription);
        this.bAddInfo.setOnClickListener(this);
        this.ibSearchInfo = (ImageButton) this.findViewById(R.id.ibSearchDescription);
        this.ibSearchInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case (R.id.ibAddArea):
                String area = etAddInfo.getText().toString();
                areas.add(area);
                DBHelper.getInstance(getApplicationContext()).insertArea(area);
                ArrayAdapter<String> areaItemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, areas);
                this.listView.setAdapter(areaItemsAdapter);
                //Maknuti tipkovnicu
                break;

            case (R.id.ibAddDescription):
                String description = etAddInfo.getText().toString();
                descriptions.add(description);
                DBHelper.getInstance(getApplicationContext()).insertDescription(description);
                ArrayAdapter<String> descriptionsItemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, descriptions);
                this.listView.setAdapter(descriptionsItemsAdapter);
                //Maknuti tipkovnicu
                break;

            case (R.id.ibSearchArea):
                break;
            case (R.id.ibSearchDescription):
                break;
        }


    }
}
