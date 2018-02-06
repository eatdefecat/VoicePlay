package com.dz.play.voiceplay.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.dz.play.voiceplay.R;
import com.dz.play.voiceplay.play.AudioPlayer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.audio_button).setOnClickListener(this);
        mEditView = (EditText) findViewById(R.id.audio_edit);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.audio_button:
                String money = mEditView.getText().toString().trim();
                AudioPlayer.getInstance().startPlay(getApplicationContext(), money);
                break;
            default:
                break;
        }
    }
}
