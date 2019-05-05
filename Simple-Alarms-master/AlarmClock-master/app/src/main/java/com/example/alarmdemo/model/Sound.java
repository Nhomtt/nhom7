package com.example.alarmdemo.model;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.example.alarmdemo.R;

public class Sound {
	private final static int MAX_SOUNDS = 10;
	private Context context;
	private SoundPool sound;
	private AudioManager audioManager;
	private float lengthMusic;
	private float volume;
	private int soundID = R.raw.sound;

	public Sound(Context context) {
		this.context = context;
		this.sound = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
		this.audioManager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		lengthMusic = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		lengthMusic = lengthMusic * 1.0F
				/ audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		volume = lengthMusic / 2;
		setVolume();
		loadSound();
	}

	private void loadSound() {
		soundID = sound.load(context, R.raw.sound, 1);
	}

	public void setVolume() {
		volume = 100 * lengthMusic;
	}

	public void playSound() {
		sound.play(soundID, volume, volume, 1, 0, 1.0F);
	}

	public void playSoundLoop() {
		sound.play(soundID, volume, volume, 1, -1, 1.0F);
	}

	public void stopSound() {
		sound.stop(soundID);
		loadSound();
	}

}