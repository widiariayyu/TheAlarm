package com.sempol.thealarm;

import android.app.ActivityManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class AlarmPuzzle extends AppCompatActivity {
    Button btn_confirm;

    TextView tv_pointA, tv_pointB, tv_type;
    EditText et_answer;
    Button btn_change, btn_submit;

    Random random = new Random();
    int pointA, pointB;
    int result;
    int answer, type;
    String ans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.alarm_puzzle);
        final Window win= getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
//        final MediaPlayer mediaPlayer = new MediaPlayer();
//        mediaPlayer.setDataSource(this, Settings.System.DEFAULT_ALARM_ALERT_URI);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        final Ringtone ringtone = RingtoneManager.getRingtone(this,uri);
        ringtone.play();


        tv_pointA  = findViewById(R.id.tv_pointA);
        tv_pointB  = findViewById(R.id.tv_pointB);
        tv_type    = findViewById(R.id.tv_type);
        et_answer  = findViewById(R.id.et_answer);
        btn_submit = findViewById(R.id.btn_submit);
        btn_change = findViewById(R.id.btn_change);

        setMath();
        setBtn_change();
    }



    public void setMath(){
        pointA = random.nextInt(20); // menyetting agar angka yang keluar adalah random
        pointB = random.nextInt(10);
        result = setResult(pointA,pointB ); // hasil untuk perhitungan pointA dan pointB
        tv_pointA.setText(""+pointA);
        tv_pointB.setText(""+pointB);
        setBtn_submit(result);
    }

    public int getAnswer(){
        int abc = 0;
        try {
            ans = et_answer.getText().toString();
            abc = Integer.parseInt(ans);
        }catch (NumberFormatException e){

        }
        return abc;
    }


    public int setResult(int A, int B){
        type = random.nextInt(3);
        if (type==0){
            tv_type.setText("x");
            result = A*B; // untuk kali
        }else if (type == 1){
            tv_type.setText("+");
            result = A+B; // untuk tambah
        }else if (type == 2){
            tv_type.setText("-");
            result = A-B; // untuk kurang
        }
        return result;
    }

    public Button setBtn_submit(final int res) {
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ans = et_answer.getText().toString();
                    answer = Integer.parseInt(ans); //ngeset supaya jawaban diubah ke variabel integer
                    if (answer==res){
                        Toast.makeText(AlarmPuzzle.this, "Anda Benar", Toast.LENGTH_SHORT).show();
//                        finishAffinity();
                        finish(); // sintak untuk membuat alarm selesai berbunyi jika jawaban benar
                        System.exit(0); // menutup game jika jawaban benar
                    }else {
                        Toast.makeText(AlarmPuzzle.this, "Maaf Anda Salah!!!", Toast.LENGTH_SHORT).show();
                        et_answer.setText("", null);
                        setMath();
                    }
                }catch (NumberFormatException e){
                    Toast.makeText(AlarmPuzzle.this, "Masukin Jawaban Dulu Ya!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return btn_submit;
    }

    public Button setBtn_change() {
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ans = et_answer.getText().toString();
                    answer = Integer.parseInt(ans);
                    answer =answer*-1; // menyetting agar value angka yang dimasukkan bernilai minus
                    et_answer.setText(String.valueOf(answer)); // ngeset textview supaya bisa dijawab
                }catch (NumberFormatException e){
                    Toast.makeText(AlarmPuzzle.this, "Masukin Jawaban Dulu Ya!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return btn_change;
    }


        @Override
    public void onBackPressed() {
        //this will prevent the back button from closing the activity
    }

}
