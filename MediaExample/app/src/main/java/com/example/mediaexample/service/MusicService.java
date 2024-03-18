package com.example.mediaexample.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.mediaexample.MainActivity;
import com.example.mediaexample.R;
import com.example.mediaexample.model.Song;

import java.util.ArrayList;
import java.util.Random;

public class MusicService extends Service implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {
    private MediaPlayer player;
    private ArrayList<Song> songs;
    private int songPos;
    private final IBinder musicBind = new MusicBinder();
    private String songTitle = "";
    private static final int NOTIFY_ID = 1;
    private boolean shuffle = false;
    private Random rand;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return musicBind;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        songPos = 0;
        player = new MediaPlayer();
    }

    public void initMusicPlayer() {
        player.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setOnPreparedListener(this);
        player.setOnErrorListener(this);
        player.setOnCompletionListener(this);
    }

    public void setList(ArrayList<Song> theSongs) {
        this.songs = theSongs;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        if (player.getCurrentPosition() > 0) {
            mediaPlayer.reset();
            playNext();
        }
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
        mediaPlayer.reset();
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder nBuilder = new Notification.Builder(this);
        nBuilder.setContentIntent(pendingIntent)
                .setTicker(songTitle)
                .setSmallIcon(R.drawable.play)
                .setOngoing(true)
                .setContentTitle("playing")
                .setContentText(songTitle);
        Notification notif = nBuilder.getNotification();
        startForeground(NOTIFY_ID, notif);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        player.stop();
        player.release();
        return false;
    }

    public void playSong() {
        player.reset();
        Song playSong = this.songs.get(this.songPos);
        songTitle = playSong.getTitle();
        Long currentSongId = playSong.getId();
        Uri trackUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, currentSongId);
        try {
            player.setDataSource(getApplicationContext(), trackUri);
        } catch (Exception e) {
            Log.e("MUSIC SERVICE", "Error starting data source", e);
        }
        player.prepareAsync();
    }

    public void setSong(int songIndex) {
        songPos = songIndex;
    }

    public int getSongPos() {
        return player.getCurrentPosition();
    }

    public boolean isPlaying() {
        return player.isPlaying();
    }

    public void pausePlayer() {
        player.pause();
    }

    public void seek(int pos) {
        player.seekTo(pos);
    }

    public void go() {
        player.start();
    }

    public void playPrev() {
        songPos--;
        if (songPos < 0) songPos = songs.size() - 1;
        playSong();
    }

    public void playNext() {
        if (shuffle) {
            int newSongPos = songPos;
            while (newSongPos == songPos) {
                newSongPos = rand.nextInt(songs.size());
            }
            songPos = newSongPos;
        } else {
            songPos++;
            if (songPos >= songs.size()) songPos = 0;
        }
        playSong();
    }

    @Override
    public void onDestroy(){
        stopForeground(true);
    }

    public void setShuffle(){
        if (shuffle) shuffle = false;
        else shuffle = true;
    }

    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }

    public int getDur(){
        return player.getDuration();
    }
}
