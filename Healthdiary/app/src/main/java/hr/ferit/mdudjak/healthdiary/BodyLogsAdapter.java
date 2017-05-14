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

public class BodyLogsAdapter extends BaseAdapter {

    private ArrayList<BodyLog> mBodyLogs;
    public BodyLogsAdapter(ArrayList<BodyLog> bodyLogs) { mBodyLogs = bodyLogs; }
    @Override
    public int getCount() { return this.mBodyLogs.size(); }
    @Override
    public Object getItem(int position) { return this.mBodyLogs.get(position); }
    @Override
    public long getItemId(int position) { return position; }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BodyLogsAdapter.ViewHolder bodyLogsViewHolder;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_body_logs, parent, false);
            bodyLogsViewHolder = new BodyLogsAdapter.ViewHolder(convertView);
            convertView.setTag(bodyLogsViewHolder);
        }
        else{
            bodyLogsViewHolder = (BodyLogsAdapter.ViewHolder) convertView.getTag();
        }
        BodyLog bodyLog= this.mBodyLogs.get(position);
        bodyLogsViewHolder.tvWeight.setText(bodyLog.getWeight().toString()+" kg");
        bodyLogsViewHolder.tvHeartRate.setText(String.valueOf(bodyLog.getHeartRate() + " rpm"));
        bodyLogsViewHolder.tvBloodSugar.setText(bodyLog.getBloodSugar().toString() + " mmol/L");
        String sBodyPressure=String.valueOf(bodyLog.getUpperPressure())+"/"+String.valueOf(bodyLog.getLowerPressure());
        bodyLogsViewHolder.tvBodyPressure.setText(sBodyPressure + " mm/Hg");
        return convertView;
    }
    public void insert(BodyLog bodyLog) {
        this.mBodyLogs.add(bodyLog);
        this.notifyDataSetChanged();
    }
    public static class ViewHolder {
        public TextView tvWeight, tvHeartRate, tvBloodSugar, tvBodyPressure;
        public ViewHolder(View view) {
            tvWeight = (TextView) view.findViewById(R.id.tvBodyLogWeight);
            tvHeartRate= (TextView) view.findViewById(R.id.tvBodyLogHeartRate);
            tvBloodSugar= (TextView) view.findViewById(R.id.tvBodyLogBloodSugar);
            tvBodyPressure= (TextView) view.findViewById(R.id.tvBodyLogBodyPressure);
        }
    }
}
