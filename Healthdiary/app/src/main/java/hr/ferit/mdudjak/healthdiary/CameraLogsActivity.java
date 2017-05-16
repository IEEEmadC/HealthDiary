package hr.ferit.mdudjak.healthdiary;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CameraLogsActivity extends AppCompatActivity implements View.OnClickListener {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    ImageButton ibAddCameraLog;
    ListView lvCameraLogs;
    String mCurrentPhotoPath;
    CameraLog mCameraLog;
    String mDate,mURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_log);
        this.setUpUI();
    }

    private void setUpUI() {
        this.ibAddCameraLog = (ImageButton) this. findViewById(R.id.bAddCameraLog);
        this.ibAddCameraLog.setOnClickListener(this);
        this.lvCameraLogs = (ListView) this.findViewById(R.id.lvCameraLogs);
        ArrayList<CameraLog> cameraLogs = DBHelper.getInstance(this).getAllCameraLogs();
        CameraLogsAdapter mCameraLogsAdapter = new CameraLogsAdapter(cameraLogs);
        this.lvCameraLogs.setAdapter(mCameraLogsAdapter);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                this.mURL=photoURI.toString();
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    @Override
    public void onClick(View v) {
        this.dispatchTakePictureIntent();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(mURL!=null && mDate!=null) {
            mCameraLog = new CameraLog(mURL, mDate);
            DBHelper.getInstance(getApplicationContext()).insertCameraLog(mCameraLog);
            ArrayList<CameraLog> cameraLogs = DBHelper.getInstance(this).getAllCameraLogs();
            CameraLogsAdapter mCameraLogsAdapter = new CameraLogsAdapter(cameraLogs);
            this.lvCameraLogs.setAdapter(mCameraLogsAdapter);
        }
        else {
            Log.e("Error",mURL +" "+ mDate);
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        mDate=timeStamp;
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        this.mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

}
