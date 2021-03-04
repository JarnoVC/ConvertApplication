package com.example.convertapplication;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;


public class FragmentB extends Fragment {

    public interface FragmentsBListener {
        void onInputBSent(String input);
    }



    private EditText etFahrenheit;
    private FragmentsBListener listener;

    public FragmentB() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_b, container, false);

        etFahrenheit =  v.findViewById(R.id.et_fahrenheit);
        etFahrenheit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(etFahrenheit.hasFocus()){
                    String input = etFahrenheit.getText().toString();

                    //stuur informatie naar Fragment a & c

                    if(!TextUtils.isEmpty(input)){
                        listener.onInputBSent(input);
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
    public void updateFahrenheit(String input){
        etFahrenheit.setText(input);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        //controleerd of FragmentsAListener een deel is van de context
        //https://www.baeldung.com/java-instanceof
        if(context instanceof FragmentsBListener){
            listener = (FragmentsBListener)context;
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

