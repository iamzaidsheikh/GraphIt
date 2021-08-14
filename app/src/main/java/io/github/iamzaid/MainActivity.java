package io.github.iamzaid;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import io.github.iamzaid.Views.PhasorView;

public class MainActivity extends AppCompatActivity {

    PhasorView pv;
    Phasor phasor;
    Paint red = new Paint();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pv = new PhasorView(this);red.setColor(Color.RED);
        phasor = new Phasor(200d,0.5,"Sin",red);
        //pv.run(phasor);

    }

}