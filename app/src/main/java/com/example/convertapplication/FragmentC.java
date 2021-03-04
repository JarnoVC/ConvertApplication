package com.example.convertapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class FragmentC extends Fragment {

    public interface FragmentsCListener {
        void onInputCSent(String input);
    }



    private EditText etKelvin;
    private FragmentsCListener listener;

    public FragmentC() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_c, container, false);

        etKelvin =  v.findViewById(R.id.et_kelvin);
        etKelvin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(etKelvin.hasFocus()){
                    String input = etKelvin.getText().toString();

                    //stuur informatie naar Fragment b & a

                    if(!TextUtils.isEmpty(input)){
                        listener.onInputCSent(input);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return v;
    }

    //ontvangt data van buitenaf (bvb als er in fragment B op "ok" wordt gedrukt)
    public void updateKelvin(String input){
        etKelvin.setText(input);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        //controleerd of FragmentsAListener een deel is van de context
        //https://www.baeldung.com/java-instanceof
        if(context instanceof FragmentC.FragmentsCListener){
            listener = (FragmentC.FragmentsCListener)context;
        }
        else{
            throw new RuntimeException(
                    String.format("%s must implement FragmentCListener", context.toString())
            );
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        listener = null;
    }
}