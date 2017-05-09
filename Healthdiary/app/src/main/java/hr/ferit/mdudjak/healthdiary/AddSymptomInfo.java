package hr.ferit.mdudjak.healthdiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AddSymptomInfo extends AppCompatActivity {
    ListView listView;
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
        ArrayList<String> areas = new ArrayList<String>();
        areas.add("Head");
        areas.add("Neck");
        areas.add("Back");
        areas.add("Foot");
        ArrayAdapter<String> areaItemsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,areas);
        this.listView.setAdapter(areaItemsAdapter);
    }
    private void setUpDescriptionInputUI() {
        this.listView= (ListView) this.findViewById(R.id.descriptionList);
        ArrayList<String> descriptions = new ArrayList<String>();
        descriptions.add("Buzzing");
        descriptions.add("Pricking");
        descriptions.add("Dull");
        ArrayAdapter<String> areaItemsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,descriptions);
        this.listView.setAdapter(areaItemsAdapter);
    }
}
