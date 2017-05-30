package hr.ferit.mdudjak.healthdiary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Student on 16.5.2017..
 */

public class CameraLogsAdapter extends BaseAdapter{
    List<CameraLog> mCameraLogs;

    public CameraLogsAdapter(List<CameraLog> cameraLogs) {
        mCameraLogs = cameraLogs;
    }

    @Override
    public int getCount() {
        return this.mCameraLogs.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mCameraLogs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CameraLogsViewHolder cameraLogsViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.items_camera_logs, parent, false);
            cameraLogsViewHolder = new CameraLogsViewHolder(convertView);
            convertView.setTag(cameraLogsViewHolder);
        } else {
            cameraLogsViewHolder = (CameraLogsViewHolder) convertView.getTag();
        }
        CameraLog cameraLogs = this.mCameraLogs.get(position);
        cameraLogsViewHolder.tvDate.setText(cameraLogs.getPictureDate());


        Picasso.with(parent.getContext())
                .load(cameraLogs.getPictureURL())
                .fit()
                .centerCrop()
              //  .rotate(90f)
                .into(cameraLogsViewHolder.ivImage);
        return convertView;
    }
    public void deleteAt(int position) {
        this.mCameraLogs.remove(position);
        this.notifyDataSetChanged();
    }

    static class CameraLogsViewHolder {
        TextView tvDate;
        ImageView ivImage;
        public CameraLogsViewHolder(View newsView) {
            this.tvDate = (TextView) newsView.findViewById(R.id.tvCameraLogDate);
            this.ivImage = (ImageView) newsView.findViewById(R.id.ivCameraLogImage);
        }
    }
}
