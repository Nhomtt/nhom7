package com.example.hoangduy.svtour;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.hoangduy.svtour.model.Image;
import com.example.hoangduy.svtour.model.Video;

public class ViewVideoActivity extends AppCompatActivity {

    MediaController media;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_video);

        VideoView video = (VideoView) findViewById(R.id.videoView);
        Video video_url = (Video) getIntent().getExtras().getSerializable("video");

        media = new MediaController(this);
        video.setVideoURI(Uri.parse(video_url.getVideo_url()));
        video.setMediaController(media);
        media.setAnchorView(video);
        video.start();


    }
}
