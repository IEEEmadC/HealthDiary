package hr.ferit.mdudjak.healthdiary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mario on 13.5.2017..
 */

public class SymptomsAdapter extends BaseAdapter {

    private ArrayList<Symptom> mSymptoms;
    public SymptomsAdapter(ArrayList<Symptom> symptoms) { mSymptoms = symptoms; }
    @Override
    public int getCount() { return this.mSymptoms.size(); }
    @Override
    public Object getItem(int position) { return this.mSymptoms.get(position); }
    @Override
    public long getItemId(int position) { return position; }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder symptomViewHolder;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_symptom, parent, false);
            symptomViewHolder = new ViewHolder(convertView);
            convertView.setTag(symptomViewHolder);
        }
        else{
            symptomViewHolder = (ViewHolder) convertView.getTag();
        }
        Symptom symptom= this.mSymptoms.get(position);
        symptomViewHolder.tvArea.setText(symptom.getArea());
        symptomViewHolder.tvDescription.setText(symptom.getDescription());
        symptomViewHolder.tvIntensity.setText(String.valueOf(symptom.getIntensity()));
        return convertView;
    }
    public void insert(Symptom symptom) {
        this.mSymptoms.add(symptom);
        this.notifyDataSetChanged();
    }
    public static class ViewHolder {
        public TextView tvArea, tvDescription, tvIntensity;
        public ViewHolder(View bookView) {
            tvArea = (TextView) bookView.findViewById(R.id.tvSymptomArea);
            tvDescription = (TextView) bookView.findViewById(R.id.tvSymptomDescription);
            tvIntensity = (TextView) bookView.findViewById(R.id.tvSymptomIntensity);
        }
    }
}
