package com.example.convertapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
        implements FragmentA.FragmentsAListener, FragmentB.FragmentsBListener, FragmentC.FragmentsCListener{

    private FragmentA fragmentA;
    private FragmentB fragmentB;
    private FragmentC fragmentC;
    private double Fahrenheit;
    private double Celcius;
    private double Kelvin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentA = new FragmentA();
        fragmentB = new FragmentB();
        fragmentC = new FragmentC();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_a, fragmentA)
                .replace(R.id.layout_b, fragmentB)
                .replace(R.id.layout_c, fragmentC)
                .commit();

    }

    @Override
    public void onInputASent(String input) {
        Fahrenheit = (Double.parseDouble(input)*9/5)+32;
        fragmentB.updateFahrenheit(Double.toString(Fahrenheit));
        Kelvin = (Double.parseDouble(input)+273.15);
        fragmentC.updateKelvin(Double.toString(Kelvin));
        //(0°C × 9/5) + 32 = 32°F
    }

    @Override
    public void onInputBSent(String input) {
        Celcius = (Double.parseDouble(input)-32)*5/9;
        fragmentA.updateCelcius(Double.toString(Celcius));
        Kelvin = ((Double.parseDouble(input)-32)*5/9+273.15);
        fragmentC.updateKelvin(Double.toString(Kelvin));
    }

    @Override
    public void onInputCSent(String input) {
        Celcius = (Double.parseDouble(input)-273.15);
        fragmentA.updateCelcius(Double.toString(Celcius));
        Fahrenheit = ((Double.parseDouble(input)-273.15)*9/5+32);
        fragmentB.updateFahrenheit(Double.toString(Fahrenheit));
    }
}