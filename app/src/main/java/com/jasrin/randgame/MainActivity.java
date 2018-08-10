package com.jasrin.randgame;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnPlay,btnReset;
    TextView txtHighScore,txtScore;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private String Pref_Game="com.jasrin.randgame.game";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay=findViewById(R.id.btn_play);
        btnReset=findViewById(R.id.btn_reset);

        txtScore=findViewById(R.id.txt_score);
        txtHighScore=findViewById(R.id.txt_high_score);

        preferences=getSharedPreferences(Pref_Game,MODE_PRIVATE);
        editor=preferences.edit();//for editing xml file
        int highScore=preferences.getInt("high_score",0);//key and initial value
        txtHighScore.setText("High Score: "+highScore);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random=new Random();//for generating value 0 to 1999
                int score=random.nextInt(2000);
                txtScore.setText(""+score);

                int getSaveScore=preferences.getInt("high_score",0);//for getting value from shared pref xml file
                if (score>getSaveScore)
                {
                    txtHighScore.setText("High Score: "+score);
                    editor.putInt("high_score",score);//get value
                    editor.commit();//finally save into xml file
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtScore.setText(""+0);
                txtHighScore.setText("High Score: 0");

                editor.putInt("high_score",0);
                editor.commit();
            }
        });


    }
}
