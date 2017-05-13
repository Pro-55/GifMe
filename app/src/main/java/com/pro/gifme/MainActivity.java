package com.pro.gifme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private final int CAMERA_REQUEST_CODE = 55;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
    }

    public void startCamera(View view) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        File path = getFilePath();
        Uri videoUri = Uri.fromFile(path);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
        cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        cameraIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 7);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAMERA_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Video Successfully Saved!", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Video Failed!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void displayGif(View view) {
        Toast.makeText(getApplicationContext(), "GIF Created!", Toast.LENGTH_LONG).show();
    }

    public File getFilePath() {
        File folder = new File("sdcard/GifMe");
        if(folder.exists()) {
            folder.mkdir();
        }
        File videoFile = new File(folder,"VideoGif.mp4");
        return videoFile;
    }
}
