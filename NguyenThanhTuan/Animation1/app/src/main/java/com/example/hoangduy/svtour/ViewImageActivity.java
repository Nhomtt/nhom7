package com.example.hoangduy.svtour;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hoangduy.svtour.model.Image;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ViewImageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        PhotoView fullScreenImage = (PhotoView) findViewById(R.id.photo_view);
        Image image = (Image) getIntent().getExtras().getSerializable("image");

        byte[] image_byte = image.getImage_byte();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image_byte, 0, image_byte.length);
        fullScreenImage.setImageBitmap(bitmap);
    }
}
