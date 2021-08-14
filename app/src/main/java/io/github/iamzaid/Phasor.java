package io.github.iamzaid;

import android.graphics.Color;
import android.graphics.Paint;

public class Phasor {
    private Double amplitude;
    private Double phase;
    private String wave;
    private Paint paint;
    private Color color;

    public Phasor(Double amplitude, Double phase, String wave, Paint paint) {
        this.amplitude = amplitude;
        this.phase = phase;
        this.wave = wave;
        this.paint = paint;
    }

    public Double getAmplitude() {
        return amplitude;
    }

    public Double getPhase() {
        return phase;
    }

    public String getWave() {
        return wave;
    }


    public Paint getPaint() {
        return paint;
    }



}
