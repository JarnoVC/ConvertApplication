package com.example.convertapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
        implements FragmentA.FragmentsAListener, FragmentB.FragmentsBListener{

    private FragmentA fragmentA;
    private FragmentB fragmentB;
    private int Fahrenheit;
    private int Celcius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentA = new FragmentA();
        fragmentB = new FragmentB();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_a, fragmentA)
                .replace(R.id.layout_b, fragmentB)
                .commit();

    }

    @Override
    public void onInputASent(String input) {
        Fahrenheit = Integer.parseInt(input)+32;
        fragmentB.updateFahrenheit(Integer.toString(Fahrenheit)+"  fahrenheit");
        //(0°C × 9/5) + 32 = 32°F
    }

    @Override
    public void onInputBSent(String input) {
        Celcius = Integer.parseInt(input)-32;
        fragmentA.updateCelcius(Integer.toString(Celcius)+ " celcius");
    }
}