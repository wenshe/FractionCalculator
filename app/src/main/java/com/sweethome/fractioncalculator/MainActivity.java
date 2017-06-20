package com.sweethome.fractioncalculator;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    // Remove the below line after defining your own ad unit ID.
    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";
    //
    private Calculator _calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
        Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show();


        //
        _calculator = new Calculator();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //View.OnClickListener
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn0:
                _calculator.NumPad(0);
                break;
            case R.id.btn1:
                _calculator.NumPad(1);
                break;
            case R.id.btn2:
                _calculator.NumPad(2);
                break;
            case R.id.btn3:
                _calculator.NumPad(3);
                break;
            case R.id.btn4:
                _calculator.NumPad(4);
                break;
            case R.id.btn5:
                _calculator.NumPad(5);
                break;
            case R.id.btn6:
                _calculator.NumPad(6);
                break;
            case R.id.btn7:
                _calculator.NumPad(7);
                break;
            case R.id.btn8:
                _calculator.NumPad(8);
                break;
            case R.id.btn9:
                _calculator.NumPad(9);
                break;
            case R.id.btnSlash:
                _calculator.Slash();
                break;
            case R.id.btnPlus:
                _calculator.Plus();
                break;
            case R.id.btnMinus:
                _calculator.Minus();
                break;
            case R.id.btnMultiplication:
                _calculator.Multiplication();
                break;
            case R.id.btnDivision:
                _calculator.Division();
                break;
            case R.id.btnClear:
                _calculator.Clear();;
                break;
            case R.id.btnClearError:
                _calculator.ClearError();
                break;
            case R.id.btnBackSp:
                _calculator.BackSp();
                break;
            case R.id.btnEqual:
                _calculator.Equal();
                break;
        }
        UpdateDisplayPanel();
    }
    private void UpdateDisplayPanel()
    {
        ImageView imageView1=(ImageView)findViewById(R.id.imageView1);

        //Create a new image bitmap and attach a brand new canvas to it
        Bitmap tempBitmap = Bitmap.createBitmap(imageView1.getWidth(), imageView1.getHeight(), Bitmap.Config.RGB_565);
        Canvas tempCanvas = new Canvas(tempBitmap);

        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(128);
        textPaint.setColor(Color.WHITE);

        int inputIndex=_calculator.getInputIndex();
        int v0=_calculator.getInputValue(0);
        int v1=_calculator.getInputValue(1);
        String str=new String();
        if (inputIndex==-1)
        {
            if (v1==1)
                str=String.format("%s",v0);
            else
                str=String.format("(%s/%s)",v0,v1);
        }else if (inputIndex==0)
        {
            str=String.format("%s",v0);

        }else if  (inputIndex==1)
        {
            if (v1>0)
                str=String.format("(%s/%s",v0,v1);
            else
                str=String.format("(%s/",v0);
        }

        Fraction result=_calculator.getResult();
        String str2=new String();
        if (result.getDenominator()==1)
            str2=String.format("%s",result.getNumerator());
        else
            str2=String.format("(%s/%s)",result.getNumerator(),result.getDenominator());


        switch (_calculator.getCalcOperator())
        {
            case None:
                //str=str2;
                break;
            case Plus:
                str=str2+"+"+str;
                break;
            case Minus:
                str=str2+"-"+str;
                break;
            case Multi:
                str=str2+"×"+str;
                break;
            case Div:
                str=str2+"÷"+str;
                break;
        }

        tempCanvas.drawText(str,8,128,textPaint);


        //Attach the canvas to the ImageView
        imageView1.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));

    }
}
