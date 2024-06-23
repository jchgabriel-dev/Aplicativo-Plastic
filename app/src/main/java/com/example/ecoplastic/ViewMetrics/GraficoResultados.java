package com.example.ecoplastic.ViewMetrics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GraficoResultados extends View {
    private float mWidth;
    private float mHeight;
    private Paint mDialPaint;
    private Paint mTextPaint;
    int valorTotal;

    public GraficoResultados(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mDialPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(40f);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float medioX = mWidth/2;
        float medioY = mHeight/2;
        float cuartoX = mWidth/4;
        float cuartoY = mHeight/4;

        float altura = (medioY + cuartoY) - (medioY - cuartoY);

        String[] colores = {"#00FFE0","#D2FF00","#FFD400","#FF0000"};
        String[] niveles = {"BAJO","MODERADO","ALTO","EXTREMO"};


        mDialPaint.setColor(Color.parseColor(colores[valorTotal-1]));

        for(int x = 0; x < valorTotal; x++){

            canvas.drawRect(
                    medioX - cuartoX,
                    (medioY - cuartoY) + (altura * 75/100) - (x * altura * 25/100) ,
                    medioX + cuartoX,
                    (medioY + cuartoY) - (x * altura * 25/100),
                    mDialPaint);
        }
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText(
                niveles[valorTotal-1],
                medioX,
                (medioY - cuartoY) - (altura * 5/100) + (altura * (4 - valorTotal) * 25/100),
                mTextPaint);
    }
    public void setValue(int v){
        valorTotal = v;
    }

}