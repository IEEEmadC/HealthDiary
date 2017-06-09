package hr.ferit.mdudjak.healthdiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Mario on 13.5.2017..
 */

public class BodyLogsAdapter extends BaseAdapter {

    private ArrayList<BodyLog> mBodyLogs;
    Context mContext;
    public BodyLogsAdapter(ArrayList<BodyLog> bodyLogs, Context context) { mBodyLogs = bodyLogs; mContext=context; }
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
        bodyLogsViewHolder.tvDate.setText(bodyLog.getDate());
        String pictureURL=null;
        ArrayList<CameraLog> cameraLogs = DBHelper.getInstance(mContext).getAllCameraLogs();
        for(int i=0;i<cameraLogs.size();i++){
            if(cameraLogs.get(i).getPictureDate().equals(bodyLog.getDate())){
                pictureURL=cameraLogs.get(i).getPictureURL();
                break;
            }
        }
        Picasso.with(parent.getContext())
                .load(pictureURL)
                .fit()
                .centerCrop()
                //.rotate(90f)
                .into(bodyLogsViewHolder.ivImage);
        return convertView;
    }
    public void insert(BodyLog bodyLog) {
        this.mBodyLogs.add(bodyLog);
        this.notifyDataSetChanged();
    }
    public void deleteAt(int position) {
        this.mBodyLogs.remove(position);
        this.notifyDataSetChanged();
    }
    public static class ViewHolder {
        public TextView tvWeight, tvHeartRate, tvBloodSugar, tvBodyPressure,tvDate;
        public ImageView ivImage;
        public ViewHolder(View view) {
            tvWeight = (TextView) view.findViewById(R.id.tvBodyLogWeight);
            tvHeartRate= (TextView) view.findViewById(R.id.tvBodyLogHeartRate);
            tvBloodSugar= (TextView) view.findViewById(R.id.tvBodyLogBloodSugar);
            tvBodyPressure= (TextView) view.findViewById(R.id.tvBodyLogBodyPressure);
            tvDate = (TextView) view.findViewById(R.id.txtBodyLogDate);
            ivImage= (ImageView) view.findViewById(R.id.ivCameraLogImage);
        }
    }
}
