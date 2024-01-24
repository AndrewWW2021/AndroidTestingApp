package com.example.engl_testing;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.StyledPlayerView;

public class ExoActivity extends Activity {

    ExoPlayer player;
    String video1 = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
    String video2 = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/DesigningForGoogleCast.mp4";
    String video3 = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4";

    Button Video1;
    Button Video2;
    Button Video3;
    Button PlayL;
    Button PL_clear;

    String PLstr;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exo);

        this.Video1 = findViewById(R.id.vid1);
        this.Video2 = findViewById(R.id.vid2);
        this.Video3 = findViewById(R.id.vid3);
        this.PlayL = findViewById(R.id.pl);
        this.PL_clear = findViewById(R.id.off);


        StyledPlayerView playerView = findViewById(R.id.exoPlayerView);
        StyledPlayerView playerViewS = findViewById(R.id.exoPlayerViewSound);
        playerViewS.setVisibility(View.GONE);

        ExoPlayer player = new ExoPlayer.Builder(ExoActivity.this).build();
        ExoPlayer playerS = new ExoPlayer.Builder(ExoActivity.this).build();

        playerView.setPlayer(player);
        playerViewS.setPlayer(playerS);
        //MediaItem mediaItem = MediaItem.fromUri(video);
        //MediaItem mediaItem = MediaItem.

        MediaItem firstItem = MediaItem.fromUri(video1);
        MediaItem secondItem = MediaItem.fromUri(video2);
        MediaItem thirdItem = MediaItem.fromUri(video3);

        MediaItem a_Item1 = MediaItem.fromUri("asset:///1.mp3");
        MediaItem a_Item2 = MediaItem.fromUri("asset:///2.mp3");
        MediaItem a_Item3 = MediaItem.fromUri("asset:///3.mp3");

        PLstr = "";

//        MediaItem inputMediaItem =
//                new MediaItem.Builder()
//                        .setUri(video)
//                        .setClippingConfiguration(
//                                new MediaItem.ClippingConfiguration.Builder()
//                                        .setStartPositionMs(0_000)
//                                        .setEndPositionMs(25_000)
//                                        .build())
//                        .build();
//
        //

        //player.setMediaItem(inputMediaItem);
        playerS.setRepeatMode(playerS.REPEAT_MODE_OFF);

        // первая кнопка
        Video1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.addMediaItem(firstItem);
                Toast.makeText(getBaseContext(),"1 видео добавлено в плейлист",Toast.LENGTH_LONG).show();

                //playerS.addMediaItem(a_Item1);
                //playerS.prepare();
                //playerS.setPlayWhenReady(true);

                playerS.setMediaItem(a_Item1);
                // Prepare the player.
                playerS.prepare();
                // Start the playback.
                playerS.play();

                PLstr += "1 ";
            }
        });

        Video2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.addMediaItem(secondItem);
                Toast.makeText(getBaseContext(),"2 видео добавлено в плейлист",Toast.LENGTH_LONG).show();

//                playerS.addMediaItem(a_Item2);
//                playerS.prepare();
//                playerS.setPlayWhenReady(true);

                playerS.setMediaItem(a_Item2);
                // Prepare the player.
                playerS.prepare();
                // Start the playback.
                playerS.play();
                PLstr += "2 ";

            }
        });

        Video3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.addMediaItem(thirdItem);
                Toast.makeText(getBaseContext(),"3 видео добавлено в плейлист",Toast.LENGTH_LONG).show();

//                playerS.addMediaItem(a_Item2);
//                playerS.prepare();
//                playerS.setPlayWhenReady(true);

                playerS.setMediaItem(a_Item3);
                // Prepare the player.
                playerS.prepare();
                // Start the playback.
                playerS.play();
                PLstr += "3 ";

            }
        });

        PlayL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ExoActivity.this);
                builder.setTitle("Доступные видео (просто для примера)")
                        .setMessage("1: Какой-то мультик" +
                                "\n" +"2: Какая-то лекция" +
                                "\n" +"3: Еще один мультик" +
                                "\n" +"Текущий плейлист: " +
                                "\n" + PLstr)




                        .setCancelable(true);

                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        PL_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"плейлист сброшен",Toast.LENGTH_LONG).show();
                player.clearMediaItems();
                PLstr = "";

            }
        });

        //player.addMediaItem(firstItem);
        //playerS.addMediaItem(a_Item);
        //player.addMediaItem(secondItem);
        //player.addMediaItem(a_Item);

        player.prepare();
        player.setPlayWhenReady(true);

        //playerS.prepare();
        //playerS.setPlayWhenReady(true);
        //playerS.clearMediaItems();



    }

    @Override
    protected void onStop() {
        super.onStop();
        player.setPlayWhenReady(false);
        player.release();
        player = null;
    }
}
