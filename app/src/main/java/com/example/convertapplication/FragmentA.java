package com.example.convertapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class FragmentA extends Fragment {

    public interface FragmentsAListener {
        void onInputASent(String input);
    }



    private EditText etCelcius;
    private FragmentsAListener listener;

    public FragmentA() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_a, container, false);

        etCelcius =  v.findViewById(R.id.et_celcius);
        v.findViewById(R.id.button_to_fahrenheit).setOnClickListener(bv -> {
            String input = etCelcius.getText().toString();

            //stuur informatie naar Fragment b

            listener.onInputASent(input);
        });

        return v;
    }

    //ontvangt data van buitenaf (bvb als er in fragment B op "ok" wordt gedrukt)
    public void updateCelcius(String input){
        etCelcius.setText(input);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        //controleerd of FragmentsAListener een deel is van de context
        //https://www.baeldung.com/java-instanceof
        if(context instanceof FragmentsAListener){
            listener = (FragmentsAListener)context;
        }
        else{
            throw new RuntimeException(
                    String.format("%s must implement FragmentAListener", context.toString())
            );
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        listener = null;
    }

}

