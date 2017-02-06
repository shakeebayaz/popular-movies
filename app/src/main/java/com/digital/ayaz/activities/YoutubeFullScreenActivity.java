package com.digital.ayaz.activities;

import android.content.Intent;
import android.os.Bundle;

import com.digital.ayaz.BuildConfig;
import com.digital.ayaz.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by ats on 02/01/17.
 */
public class YoutubeFullScreenActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private static final int RECOVERY_REQUEST = 1;


    private YouTubePlayer youTubePlayer;
    private YouTubePlayerView youTubePlayerView;
    private String mPath;
    public static final String VIDEO_ID="video_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtubefull_screen);
        mPath = getIntent().getStringExtra(VIDEO_ID);
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubeplayerview);
        youTubePlayerView.initialize(BuildConfig.YouTubeKey, this);


    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if (!wasRestored) {
            this.youTubePlayer=youTubePlayer;
            youTubePlayer.loadVideo(mPath);
            //youTubePlayer.cueVideo(mPath); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
            youTubePlayer.play();
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            //String error = String.format(getString(R.string.player_error), youTubeInitializationResult.toString());
            //Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(BuildConfig.YouTubeKey, this);
        }

    }
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return youTubePlayerView;
    }
}