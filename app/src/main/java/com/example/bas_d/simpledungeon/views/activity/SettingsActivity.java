package com.example.bas_d.simpledungeon.views.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

import com.example.bas_d.simpledungeon.R;
import com.example.bas_d.simpledungeon.SoundEngine;
import com.example.bas_d.simpledungeon.model.FixedValues;

public class SettingsActivity extends AppCompatActivity {

    private SeekBar soundBar, musicBar;
    private int soundsVolume, musicVolume;
    private final String soundsVolumeString = "soundsVolume", musicVolumeString = "musicVolume";
    private SoundEngine soundEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        soundEngine = new SoundEngine(this, getResources(), true);
        getPreferences();
        setupBars();
    }

    @Override
    protected void onStop() {
        super.onStop();
        soundEngine.stopSounds();
    }

    private void setupBars() {
        soundBar = findViewById(R.id.soundBar);
        soundBar.setProgress(soundsVolume);
        soundBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                soundEngine.setVolumeBackgroundMusic(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                soundEngine.playBackgroundMusic();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                setNewPrefrences(soundsVolumeString, seekBar.getProgress());
                soundEngine.resetSounds(true);
                soundEngine.resetVolumes();
            }
        });
        musicBar = findViewById(R.id.musicBar);
        musicBar.setProgress(musicVolume);
        musicBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                soundEngine.setVolumeBackgroundMusic(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                soundEngine.playBackgroundMusic();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                setNewPrefrences(musicVolumeString, seekBar.getProgress());
                soundEngine.resetSounds(true);
                soundEngine.resetVolumes();
            }
        });
    }

    private void getPreferences() {
        SharedPreferences settings = getSharedPreferences(FixedValues.SOUNDSETTINGS, 0);
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

    private void setNewPrefrences(String volumeName, int volume) {
        SharedPreferences settings = getSharedPreferences(FixedValues.SOUNDSETTINGS, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(volumeName, volume);
        editor.commit();
    }
}
