package com.dz.play.voiceplay.play;


import android.content.Context;
import android.media.MediaPlayer;

import java.io.File;

public class AudioPlayerRunnable implements Runnable{

    private MediaPlayer mMediaPlayer;
    private String mMoney;
    private Context mContext;
    private boolean flag;

    public AudioPlayerRunnable(Context context, String money){
        mMoney = money;
        mContext = context;
        mMediaPlayer = null;
        flag = true;
    }

    @Override
    public void run() {
        try {
            while (flag){
                if(mMediaPlayer == null){
                    File files = new AudioComposeHelper(mContext, NumberToMoneyUtils.toMoneyUnit(mMoney)).getAudioFile();
                    if(files == null){
                        return;
                    }
                    mMediaPlayer = new MediaPlayer();
                    mMediaPlayer.reset();
                    mMediaPlayer.setDataSource(files.getPath());
                    mMediaPlayer.prepare();
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer paramMediaPlayer) {
                            try {
                                paramMediaPlayer.reset();
                                paramMediaPlayer.release();
                            } catch (Throwable e) {
                                e.printStackTrace();
                            }finally {
                                flag = false;
                            }
                        }
                    });
                    mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                        @Override
                        public boolean onError(MediaPlayer paramMediaPlayer, int what, int extra) {
                            try {
                                paramMediaPlayer.reset();
                                paramMediaPlayer.release();
                            } catch (Throwable e) {
                                e.printStackTrace();
                            }finally {
                                flag = false;
                            }
                            return false;
                        }
                    });
                    mMediaPlayer.start();
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void releasePlayer() {
        try {
            if (mMediaPlayer != null) {
                mMediaPlayer.reset();
                mMediaPlayer.release();
                mMediaPlayer = null;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
