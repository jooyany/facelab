package ch.zhaw.facerecognition.Activities;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import ch.zhaw.facerecognition.R;
import ch.zhaw.facerecognitionlibrary.Helpers.FileHelper;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.helpers.Constants;
import com.darsh.multipleimageselect.models.Image;

import java.util.ArrayList;

import ch.zhaw.facerecognitionlibrary.Helpers.FileHelper;

public class OpenGalleryActivity extends AppCompatActivity {
    private String name;
    public static final String IMAGES = "images";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_gallery);

        Intent intent = getIntent();
        name = intent.getStringExtra("Name");

        Intent newIntent = new Intent(this, AlbumSelectActivity.class);
        newIntent.putExtra("Name", name);

        startActivityForResult(newIntent, Constants.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            //The array list has the image paths of the selected images
            ArrayList<Image> images = data.getParcelableArrayListExtra(Constants.INTENT_EXTRA_IMAGES);
            Log.d("STATE", images.get(0).path.toString());

            Intent launchResult = new Intent(this, PictureActivity.class);

            launchResult.putExtra("Name", name);

            launchResult.putExtra(IMAGES, images);
            startActivity(launchResult);
        }
    }
}