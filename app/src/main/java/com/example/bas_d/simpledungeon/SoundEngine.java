package com.example.bas_d.simpledungeon;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.util.Log;

import com.example.bas_d.simpledungeon.model.FixedValues;

import java.util.ArrayList;

public class SoundEngine {

    private Resources resources;
    private Activity activity;
    private MediaPlayer hurt, backgroundMusic;
    private ArrayList<MediaPlayer> sounds;
    private ArrayList<MediaPlayer> music;
    private ArrayList<MediaPlayer> allSounds;
    private final int MAXVOLUME = 100;
    private int soundsVolume, musicVolume;
    private final String soundsVolumeString = "soundsVolume", musicVolumeString = "musicVolume";

    public SoundEngine(Activity activity, Resources resources, boolean light) {
        this.resources = resources;
        this.activity = activity;
        this.sounds = new ArrayList<>();
        this.music = new ArrayList<>();
        this.allSounds = new ArrayList<>();
        getPreferences();

        resetSounds(light);

        setVolumes();
    }

    public void playPlayerHurt() {
        if(!hurt.isPlaying()) {
            hurt.start();
            Log.d("SimpleDungeon", "playing hurt");
        }
    }

    public void playBackgroundMusic() {
        backgroundMusic.setLooping(true);
        if(!backgroundMusic.isPlaying()) {
            backgroundMusic.start();
            Log.d("SimpleDungeon", "playing backgroundmusic");
        }
    }

    public void stopSounds() {
        for(MediaPlayer sound : allSounds) {
            sound.stop();
        }
    }

    public void resetSounds(boolean light) {
        stopSounds();
        backgroundMusic = MediaPlayer.create(this.activity, R.raw.gourmet_race___kirby_super_star);
        music.add(backgroundMusic);
        if(!light) {
            hurt = MediaPlayer.create(this.activity, R.raw.hurt);
            sounds.add(hurt);
        }
        allSounds.addAll(sounds);
        allSounds.addAll(music);
    }

    private float getVolume(int currentVolume) {
        return 1 - (float)(Math.log(MAXVOLUME-currentVolume)/Math.log(MAXVOLUME));
    }

    private void getPreferences() {
        SharedPreferences settings = activity.getSharedPreferences(FixedValues.SOUNDSETTINGS, 0);
        soundsVolume = settings.getInt(soundsVolumeString, -1);
        musicVolume = settings.getInt(musicVolumeString, -1);
        if(soundsVolume == -1 || musicVolume == -1) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt(soundsVolumeString, 100);
            editor.putInt(musicVolumeString, 100);
            editor.commit();
            getPreferences();
        }
    }

    private void setVolumes() {
        float soundFloat = getVolume(soundsVolume);
        for(MediaPlayer sound : sounds) {
            sound.setVolume(soundFloat, soundFloat);
        }
        soundFloat = getVolume(musicVolume);
        for(MediaPlayer sound : music) {
            sound.setVolume(soundFloat, soundFloat);
        }
    }

    public void setVolumeBackgroundMusic(int volumeBackgroundMusic) {
        float volume = getVolume(volumeBackgroundMusic);
        backgroundMusic.setVolume(volume, volume);
    }

    public void resetVolumes() {
        getPreferences();
        setVolumes();
    }
}
